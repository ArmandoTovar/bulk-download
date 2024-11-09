package tovar.infrastructure.adapter.messaging;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.reactive.ReactiveMailer;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import tovar.application.service.email.AbstractEmailService;
import tovar.domain.model.email.EmailData;

@ApplicationScoped
public class GmailEmailService extends AbstractEmailService {
  @Inject
  ReactiveMailer reactiveMailer;

  @Override
  protected void buildMessage(EmailData emailData) {
    String cuerpo = String.format("Hola,\n\n%s\n\nSaludos.", emailData.getBody());
    emailData.setBody(cuerpo);
  }

  @Override
  public Uni<Void> sendProcess(EmailData emailData) {
    return reactiveMailer.send(Mail.withHtml(emailData.getTo(), emailData.getSubject(), emailData.getBody()));
  }

}
