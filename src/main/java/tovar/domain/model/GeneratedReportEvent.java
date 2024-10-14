package tovar.domain.model;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneratedReportEvent extends DomainEvent {
  private UUID reportId;

  private UUID downloadId;

  public GeneratedReportEvent(UUID reportId, UUID downloadId) {
    super();
    this.reportId = reportId;
    this.downloadId = downloadId;
  }

}
