package tovar.infrastructure.event;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import tovar.application.handler.NotificationEventHandler;
import tovar.application.handler.CloudEventHandler;
import tovar.domain.model.event.GeneratedReportEvent;

@ApplicationScoped
public class KafkaEventConsumer {

  @Inject
  NotificationEventHandler notificationEventHandler;

  @Inject
  CloudEventHandler cloudEventHandler;

  @Incoming("generated-report-in")
  public void process(GeneratedReportEvent event) {
    notificationEventHandler.handleEvent(event);
    cloudEventHandler.handleEvent(event);
  }

}
