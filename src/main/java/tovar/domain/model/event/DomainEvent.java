package tovar.domain.model.event;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public abstract class DomainEvent {
  protected LocalDateTime dateOfOccurrence;

  public DomainEvent() {
    dateOfOccurrence = LocalDateTime.now();
  }
}
