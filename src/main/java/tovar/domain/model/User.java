package tovar.domain.model;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
  private UUID id;
  private String name;
  private String email;
  private Tenant tenant;

}
