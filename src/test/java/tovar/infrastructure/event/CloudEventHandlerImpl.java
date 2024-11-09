package tovar.infrastructure.event;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import tovar.application.handler.CloudEventHandler;
import tovar.domain.service.ICloudStrategy;

@ApplicationScoped
public class CloudEventHandlerImpl extends CloudEventHandler {

  @Inject
  ICloudStrategy cloudService;

  @Override
  protected ICloudStrategy getCloudService() {
    return cloudService;
  }
}
