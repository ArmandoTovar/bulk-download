package tovar.domain.model.event;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationEvent extends DomainEvent {
  private UUID user_id;
  private String subject;
  private String message;

  public NotificationEvent(UUID user_id, String subject, String message) {
    super();
    this.user_id = user_id;
    this.subject = subject;
    this.message = message;
  }

}
