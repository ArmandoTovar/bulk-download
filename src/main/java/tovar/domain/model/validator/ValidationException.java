package tovar.domain.model.validator;

import java.util.List;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {
  private final List<String> errorMessages;
  private final String transactionId;

  public ValidationException(List<String> errorMessages, String transactionId) {
    super("Validation failed");
    this.errorMessages = errorMessages;
    this.transactionId = transactionId;
  }

}
