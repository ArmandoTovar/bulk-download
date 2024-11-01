package tovar.application.service.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.smallrye.mutiny.Uni;
import tovar.domain.model.email.EmailData;
import tovar.domain.service.IEmailStrategy;

public abstract class AbstractEmailService implements IEmailStrategy {

  public static final Logger log = LoggerFactory.getLogger(AbstractEmailService.class);

  public void sentEmailWithTemplate(EmailData emailData) {
    checkDestination(emailData.getTo());
    buildMessage(emailData);
    send(emailData);
    tracer(emailData);
  }

  protected abstract void buildMessage(EmailData emailData);

  protected void checkDestination(String target) {
    if (target == null || !target.contains("@"))
      throw new IllegalArgumentException("Invalid email address");
  }

  public abstract Uni<Void> send(EmailData emailData);

  protected void tracer(EmailData emailData) {
    log.info(String.format("Email sended to %s", emailData.getTo()));
  }
}
