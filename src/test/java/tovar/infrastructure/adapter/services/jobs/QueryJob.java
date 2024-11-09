package tovar.infrastructure.adapter.services.jobs;

import java.io.File;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import io.vertx.core.Vertx;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import tovar.domain.model.download.Download;
import tovar.domain.model.download.DownloadStatus;
import tovar.domain.model.event.GeneratedReportEvent;
import tovar.infrastructure.adapter.services.ReportServiceImpl;
import tovar.infrastructure.persistent.repositories.DownloadRepositoryImpl;

@ApplicationScoped
@WithTransaction
public class QueryJob implements Job {

  @Inject
  private ReportServiceImpl reportService;

  @Inject
  private DownloadRepositoryImpl downloadRepository;

  @Inject
  @Channel("generated-report")
  Emitter<GeneratedReportEvent> quoteRequestEmitter;

  @Inject
  @Channel("generating-report")
  Emitter<String> quoteGeneratingEmitter;

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    String downloadId = context.getJobDetail().getJobDataMap().getString("downloadId");
    quoteGeneratingEmitter.send(downloadId);
  }

  @Incoming("generating-report-in")
  public void test(String downloadId) {

    Vertx.currentContext().runOnContext(v -> {
      downloadRepository.getById(UUID.fromString(downloadId))
          .onItem().transformToUni(opt -> {
            if (opt.isEmpty()) {
              return Uni.createFrom().voidItem();
            }
            Download download = opt.get();
            download.setStatus(DownloadStatus.PROCESSING);
            return downloadRepository.update(download)
                .onItem().transformToUni(updated -> {
                  return reportService.generateReportReactive(download.getReportId())
                      .onItem().invoke(file -> {
                        download.setStatus(DownloadStatus.COMPLETED);
                        download.setRetryCount(0);
                        quoteRequestEmitter.send(
                            new GeneratedReportEvent(download.getReportId(), "generated", file.getPath()));
                      })
                      .onFailure(Exception.class).invoke(error -> {
                        error.printStackTrace();
                        download.setStatus(DownloadStatus.FAILED);
                        download.setRetryCount(download.getRetryCount() + 1);
                      })
                      .onItemOrFailure().transformToUni((ignored, throwable) -> {
                        return downloadRepository.update(download)
                            .replaceWith(Uni.createFrom().voidItem());
                      });
                });
          }).subscribe().with(
              success -> System.out.println("Processing completed successfully."),
              failure -> System.err.println("Failed to update or process download: " + failure));
    });
  }
}
