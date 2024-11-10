package tovar.infrastructure.event;

import io.vertx.core.Vertx;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import tovar.application.handler.CloudEventHandler;
import tovar.domain.model.event.DomainEvent;
import tovar.domain.service.ICloudStrategy;

@ApplicationScoped
public class CloudEventHandlerImpl extends CloudEventHandler {

  @Inject
  ICloudStrategy cloudService;

  @Override
  protected ICloudStrategy getCloudService() {
    return cloudService;
  }

  @Override
  public void handleEvent(DomainEvent event) {
    Vertx.currentContext().runOnContext(v -> {
      super.handleEvent(event);
    });
  }
}
