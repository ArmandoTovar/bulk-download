package tovar.application.handler;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.smallrye.mutiny.Uni;
import tovar.domain.model.email.EmailData;
import tovar.domain.model.event.DomainEvent;
import tovar.domain.model.event.DomainEventSubscriber;
import tovar.domain.model.event.GeneratedReportEvent;
import tovar.domain.model.report.Report;
import tovar.domain.repository.IGenericRepository;
import tovar.domain.service.IEmailStrategy;

public abstract class NotificationEventHandler implements DomainEventSubscriber {
  public static final Logger log = LoggerFactory.getLogger(LoggerEventHandler.class);

  protected abstract IGenericRepository<Report, UUID> getReportRepository();

  protected abstract IEmailStrategy getEmailService();

  @Override
  public void handleEvent(DomainEvent event) {
    if (event instanceof GeneratedReportEvent) {
      GeneratedReportEvent reportEvent = (GeneratedReportEvent) event;
      getUserEmail(reportEvent.getReportId())
          .onItem().transformToUni(userEmail -> getEmailService().send(
              EmailData.builder()
                  .to(userEmail)
                  .subject("Generated Report")
                  .body("Your report has been generated and it's ready for download")
                  .build()))
          .subscribe().with(
              success -> {
              },
              error -> log.error("Failed to send email: " + error.getMessage()));
    }
  }

  private Uni<String> getUserEmail(UUID reportId) {
    return getReportRepository().getById(reportId)
        .map(report -> {
          if (report.isPresent() && report.get().getUserId() != null) {
            return "atovar@shelf-e.com";
          }
          throw new IllegalArgumentException(
              String.format("Report number %s doesn't have a user with an assosiated email", reportId));
        });
  }
}
