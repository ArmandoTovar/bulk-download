package tovar.application.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tovar.domain.model.event.DomainEvent;
import tovar.domain.model.event.DomainEventSubscriber;
import tovar.domain.model.event.ErrorReportEvent;
import tovar.domain.model.event.GeneratedReportEvent;

public class LoggerEventHandler implements DomainEventSubscriber {
  public static final Logger log = LoggerFactory.getLogger(LoggerEventHandler.class);

  @Override
  public void handleEvent(DomainEvent event) {
    if (event instanceof GeneratedReportEvent) {
      GeneratedReportEvent reportEvent = (GeneratedReportEvent) event;
      log.info("Reporte generado con id:", reportEvent.getReportId());

    } else {
      ErrorReportEvent reportEvent = (ErrorReportEvent) event;
      log.info("Reporte generado con id:", reportEvent.getReportId());

    }
  }
}
