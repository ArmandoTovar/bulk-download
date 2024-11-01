package tovar.domain.model.email;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailData {

  private String from;
  private String to;
  private String body;
  private String subject;
  private String attached;
}
