package tovar.infrastructure.adapter.rest;

import java.util.List;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tovar.domain.model.validator.ValidationException;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {
  @Override
  public Response toResponse(ValidationException exception) {
    return Response.status(Response.Status.BAD_REQUEST)
        .entity(new ValidationErrorResponse(Response.Status.BAD_REQUEST.getStatusCode(), exception.getErrorMessages(),
            exception.getTransactionId()))
        .type(MediaType.APPLICATION_JSON).build();
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  static class ValidationErrorResponse {
    private Integer statusCode;
    private List<String> errorMessages;
    private String transactionId;

  }
}
