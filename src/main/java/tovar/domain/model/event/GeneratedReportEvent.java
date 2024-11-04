package tovar.domain.model.event;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneratedReportEvent extends DomainEvent {
  private UUID reportId;
  private String message;
  private String pathFile;

}
