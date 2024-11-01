package tovar.infrastructure.event;

import java.util.UUID;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import tovar.application.handler.NotificationEventHandler;
import tovar.domain.model.event.GeneratedReportEvent;

@ApplicationScoped
public class KafkaEventConsumer {

  @Inject
  NotificationEventHandler notificationEventHandler;

  @Incoming("generated-report-in")
  public void process(String reportId) {
    UUID reportUuid = UUID.fromString(reportId);
    GeneratedReportEvent event = new GeneratedReportEvent(reportUuid, "generated");
    System.out.println("genere event");
    notificationEventHandler.handleEvent(event);
  }

}
