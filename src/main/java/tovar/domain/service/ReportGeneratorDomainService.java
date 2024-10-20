package tovar.domain.service;

import tovar.domain.model.base.Report;
import tovar.domain.model.download.Download;
import tovar.domain.model.event.DomainEventPublisher;
import tovar.domain.model.event.GeneratedReportEvent;

public class ReportGeneratorDomainService {
  private final DomainEventPublisher eventPublisher;

  public ReportGeneratorDomainService(DomainEventPublisher eventPublisher) {
    this.eventPublisher = eventPublisher;
  }

  public Download generate(Report report) {
    Download download = Download.builder().build();

    eventPublisher.publish(new GeneratedReportEvent(download.getId(), "Report Generated"));
    return download;
  }
}
