package tovar.infrastructure.adapter.rest;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {
  @Override
  public Response toResponse(IllegalArgumentException exception) {
    return Response.status(Response.Status.BAD_REQUEST)
        .entity(new ValidationErrorResponse(Response.Status.BAD_REQUEST.getStatusCode(),
            Arrays.asList(exception.getMessage()),
            UUID.randomUUID().toString()))
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
