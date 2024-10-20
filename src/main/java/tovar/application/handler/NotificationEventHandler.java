package tovar.application.handler;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tovar.domain.model.base.Report;
import tovar.domain.model.event.DomainEvent;
import tovar.domain.model.event.DomainEventSubscriber;
import tovar.domain.model.event.ErrorReportEvent;
import tovar.domain.model.event.GeneratedReportEvent;
import tovar.domain.repository.ReportRepository;

public class NotificationEventHandler implements DomainEventSubscriber {
  public static final Logger log = LoggerFactory.getLogger(LoggerEventHandler.class);

  private final EmailService emailService;
  private final ReportRepository reportRepository;

  public NotificationEventHandler(EmailService emailService, ReportRepository reportRepository) {
    this.emailService = emailService;
    this.reportRepository = reportRepository;
  }

  @Override
  public void handleEvent(DomainEvent event) {
    if (event instanceof GeneratedReportEvent) {
      GeneratedReportEvent reportEvent = (GeneratedReportEvent) event;
      emailService.send(
          getUserEmail(reportEvent.getReportId()),
          "Generated Report",
          "Your report have been generated and It's ready for download");
    }
  }

  private String getUserEmail(UUID reportId) {
    Report report = reportRepository.findById(reportId);
    return report.getUser().getEmail();
  }
}
