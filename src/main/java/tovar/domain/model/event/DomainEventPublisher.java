package tovar.domain.model.event;

public interface DomainEventPublisher {
  void publish(DomainEvent event);
}
