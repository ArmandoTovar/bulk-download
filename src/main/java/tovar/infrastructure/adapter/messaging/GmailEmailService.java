package tovar.infrastructure.adapter.messaging;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import tovar.application.service.email.AbstractEmailService;
import tovar.domain.model.email.EmailData;

@ApplicationScoped
public class GmailEmailService extends AbstractEmailService {
  @Inject
  Mailer mailer;

  @Override
  protected void buildMessage(EmailData emailData) {
    String cuerpo = String.format("Hola,\n\n%s\n\nSaludos.", emailData.getBody());
    emailData.setBody(cuerpo);
  }

  @Override
  protected void send(EmailData emailData) {
    try {
      mailer.send(Mail.withHtml(emailData.getTo(), emailData.getSubject(), emailData.getBody()));
    } catch (Exception e) {
      throw new RuntimeException("Error enviando el correo con Gmail: " + e.getMessage(), e);
    }
  }

}
