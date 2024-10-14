package tovar.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tenant {
  private Long id;
  private String name;

}
