package tovar.infrastructure.event;

import java.util.ArrayList;
import java.util.List;

import tovar.domain.model.event.DomainEvent;
import tovar.domain.model.event.DomainEventPublisher;
import tovar.domain.model.event.DomainEventSubscriber;

public class SimpleDomainEventPublisher implements DomainEventPublisher {
  private final List<DomainEventSubscriber> subscribers = new ArrayList<>();

  public void subscribe(DomainEventSubscriber subscriber) {
    subscribers.add(subscriber);
  }

  @Override
  public void publish(DomainEvent event) {
    for (DomainEventSubscriber subscriber : subscribers) {
      subscriber.handleEvent(event);
    }
  }
}
