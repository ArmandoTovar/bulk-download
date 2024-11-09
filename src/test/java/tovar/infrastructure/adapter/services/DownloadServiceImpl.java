package tovar.infrastructure.adapter.services;

import java.util.UUID;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import tovar.application.service.DownloadService;
import tovar.domain.model.download.Download;
import tovar.domain.model.report.Report;
import tovar.domain.repository.IGenericRepository;
import tovar.domain.service.ISchedulerService;
import tovar.infrastructure.persistent.repositories.DownloadRepositoryImpl;
import tovar.infrastructure.persistent.repositories.ReportRepositoryImpl;

@ApplicationScoped
@WithSession
public class DownloadServiceImpl extends DownloadService {
  @Inject
  private DownloadRepositoryImpl downloadRepository;

  @Inject
  private ReportRepositoryImpl reportRepository;
  @Inject
  private ISchedulerService quartzServiceImpl;

  @Override
  protected IGenericRepository<Download, UUID> getRepository() {
    return downloadRepository;
  }

  @Override
  protected ISchedulerService getSchedulerService() {
    return quartzServiceImpl;
  }

  @Override
  protected IGenericRepository<Report, UUID> getReportRepository() {
    return reportRepository;
  }

}
