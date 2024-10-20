package tovar.application.handler;

import java.util.UUID;
import java.util.concurrent.CompletionStage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.smallrye.mutiny.Uni;
import tovar.domain.model.email.EmailData;
import tovar.domain.model.event.DomainEvent;
import tovar.domain.model.event.DomainEventSubscriber;
import tovar.domain.model.event.GeneratedReportEvent;
import tovar.domain.repository.ReportRepository;
import tovar.domain.service.IEmailStrategy;

public class NotificationEventHandler implements DomainEventSubscriber {
  public static final Logger log = LoggerFactory.getLogger(LoggerEventHandler.class);
  private final IEmailStrategy emailService;
  private final ReportRepository reportRepository;

  public NotificationEventHandler(IEmailStrategy emailService, ReportRepository reportRepository) {
    this.emailService = emailService;
    this.reportRepository = reportRepository;
  }

  @Override
  public void handleEvent(DomainEvent event) {
    if (event instanceof GeneratedReportEvent) {
      GeneratedReportEvent reportEvent = (GeneratedReportEvent) event;
      getUserEmail(reportEvent.getReportId())
          .map(userEmail -> emailService.send(
              EmailData.builder()
                  .to(userEmail)
                  .subject("Generated Report")
                  .body("Your report has been generated and It's ready for download")
                  .build()))
          .onFailure().invoke(error -> log.error("Failed to send email: " + error.getMessage()));
    }
  }

  private Uni<String> getUserEmail(UUID reportId) {
    return reportRepository.getById(reportId)
        .map(report -> {
          if (report.isPresent() && report.get().getUser() != null) {
            return report.get().getUser().getEmail();
          }
          throw new IllegalArgumentException(
              String.format("Report number %s doesn't have a user with an assosiated email", reportId));
        });
  }
}
