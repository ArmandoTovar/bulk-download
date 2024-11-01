package tovar.infrastructure.adapter.services.jobs;

import java.io.File;
import java.util.Optional;
import java.util.UUID;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import tovar.domain.model.download.Download;
import tovar.domain.model.download.DownloadStatus;
import tovar.infrastructure.adapter.services.ReportServiceImpl;
import tovar.infrastructure.persistent.repositories.DownloadRepositoryImpl;

@ApplicationScoped
@WithTransaction
public class QueryJob implements Job {

  @Inject
  private ReportServiceImpl reportService;

  @Inject
  private DownloadRepositoryImpl downloadRepository;

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    String downloadId = context.getJobDetail().getJobDataMap().getString("downloadId");
    Optional<Download> optDownload = downloadRepository.getByIdNonReactive(UUID.fromString(downloadId));
    if (optDownload.isEmpty())
      return;
    var download = optDownload.get();
    download.setStatus(DownloadStatus.PROCESSING);
    downloadRepository.updateNonReactive(download);
    try {

      File f = reportService.generateReport(download.getReportId());
      download.setStatus(DownloadStatus.COMPLETED);
      download.setRetryCount(0);
    } catch (Exception e) {
      e.printStackTrace();
      ;
      download.setStatus(DownloadStatus.FAILED);
      download.setRetryCount(download.getRetryCount() + 1);
    } finally {
      downloadRepository.updateNonReactive(download);

    }
  }
}
