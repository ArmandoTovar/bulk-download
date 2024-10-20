package tovar.domain.service;

import java.util.concurrent.CompletionStage;

import tovar.domain.model.email.EmailData;

public interface IEmailStrategy {
  CompletionStage<Void> send(EmailData data);
}
