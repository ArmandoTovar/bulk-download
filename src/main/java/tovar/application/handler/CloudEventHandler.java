package tovar.application.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.Vertx;
import tovar.domain.model.event.DomainEvent;
import tovar.domain.model.event.DomainEventSubscriber;
import tovar.domain.model.event.GeneratedReportEvent;
import tovar.domain.service.ICloudStrategy;

public abstract class CloudEventHandler implements DomainEventSubscriber {
  public static final Logger log = LoggerFactory.getLogger(LoggerEventHandler.class);

  protected abstract ICloudStrategy getCloudService();

  @Override
  public void handleEvent(DomainEvent event) {

    if (event instanceof GeneratedReportEvent) {

      GeneratedReportEvent reportEvent = (GeneratedReportEvent) event;
      Vertx.currentContext().runOnContext(v -> {

        getCloudService().upload(reportEvent.getPathFile())
            .subscribe().with(
                success -> {
                },
                error -> log.error("Failed to upload file: " + error.getMessage()));
      });
    }
  }

}
