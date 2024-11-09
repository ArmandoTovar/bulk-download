package tovar.domain.service;

import io.smallrye.mutiny.Uni;
import tovar.domain.model.email.EmailData;

public interface IEmailStrategy {
  Uni<Void> send(EmailData data);
}
