package tovar.infrastructure.event;

import java.util.UUID;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import tovar.application.handler.NotificationEventHandler;
import tovar.domain.model.report.Report;
import tovar.domain.repository.IGenericRepository;
import tovar.domain.service.IEmailStrategy;
import tovar.infrastructure.persistent.repositories.ReportRepositoryImpl;

@ApplicationScoped
@WithSession
public class NotificationEventHandlerImpl extends NotificationEventHandler {

  @Inject
  IEmailStrategy emailService;

  @Inject
  private ReportRepositoryImpl reportRepository;

  @Override
  protected IGenericRepository<Report, UUID> getReportRepository() {
    return reportRepository;
  }

  @Override
  protected IEmailStrategy getEmailService() {
    return emailService;
  }
}
