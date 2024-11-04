package tovar.infrastructure.adapter.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

  public static final Logger log = LoggerFactory.getLogger(GenericErrorResponse.class);

  @Override
  public Response toResponse(Throwable exception) {
    log.error(exception.getMessage());
    return Response.status(Response.Status.BAD_REQUEST)
        .entity(new GenericErrorResponse(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
            List.of("Error: Internal Server Error")))
        .type(MediaType.APPLICATION_JSON).build();
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  static class GenericErrorResponse {
    private Integer statusCode;
    private List<String> errorMessages;

  }
}
