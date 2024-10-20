package tovar.domain.model.event;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProcessLogEvent extends DomainEvent {

  private UUID processId;
  private String step;
  private String message;
  private LogLevel logLevel;

  public ProcessLogEvent(UUID processId, String step, String message, LogLevel logLevel) {
    super();
    this.processId = processId;
    this.step = step;
    this.message = message;
    this.logLevel = logLevel;
  }

}
