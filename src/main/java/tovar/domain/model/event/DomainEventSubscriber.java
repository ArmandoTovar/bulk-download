package tovar.domain.model.event;

public interface DomainEventSubscriber {
  void handleEvent(DomainEvent event);
}
