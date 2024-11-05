package tovar.application.service;

import java.util.List;
import java.util.UUID;

import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;
import tovar.domain.model.download.Download;
import tovar.domain.model.download.DownloadStatus;
import tovar.domain.model.report.Report;
import tovar.domain.model.validator.ValidationException;
import tovar.domain.repository.IGenericRepository;
import tovar.domain.service.IDownloadService;
import tovar.domain.service.ISchedulerService;

@AllArgsConstructor
public abstract class DownloadService extends GenericCrudService<Download, UUID> implements IDownloadService {
  protected abstract IGenericRepository<Download, UUID> getRepository();

  protected abstract ISchedulerService getSchedulerService();

  protected abstract IGenericRepository<Report, UUID> getReportRepository();

  @Override
  public Uni<Void> executeTaskNow(UUID downloadId) {

    return getRepository().getById(downloadId).flatMap(optionalDownload -> {
      if (optionalDownload.isEmpty())
        return Uni.createFrom().failure(new ValidationException(
            List.of(String.format("Download ID %s doesn't exist", downloadId)),
            UUID.randomUUID().toString()));
      return getSchedulerService().triggerJobNow(optionalDownload.get());
    });
  }

  @Override
  public Uni<Void> retryDownload(UUID downloadId) {
    return getRepository().getById(downloadId).flatMap(optionalDownload -> {
      if (optionalDownload.isEmpty())
        return Uni.createFrom().failure(new ValidationException(
            List.of(String.format("Download ID %s doesn't exist", downloadId)),
            UUID.randomUUID().toString()));
      Download download = optionalDownload.get();
      download.setRetryCount(download.getRetryCount() + 1);
      download.setStatus(DownloadStatus.PENDING);
      return getRepository().update(download);
    }).flatMap(newDownload -> {
      return getSchedulerService().schedulerJob(newDownload);
    });

  }

  @Override
  public Uni<Download> create(Download download) {
    download.setStatus(DownloadStatus.PENDING);
    return getReportRepository().getById(download.getReportId())
        .onItem().transformToUni(optionalReport -> {
          if (optionalReport.isEmpty()) {
            return Uni.createFrom().failure(new ValidationException(
                List.of(String.format("Report ID %s doesn't exist", download.getReportId())),
                UUID.randomUUID().toString()));
          }
          return super.create(download);
        }).onItem().invoke(newDownload -> {
          getSchedulerService().schedulerJob(newDownload).subscribe().with(
              unused -> System.out.println("Secondary operation completed successfully."),
              failure -> System.err.println("Failed to complete secondary operation: " + failure));
        });
  }

}
