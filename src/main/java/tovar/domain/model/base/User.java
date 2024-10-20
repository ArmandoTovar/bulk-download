package tovar.domain.model.base;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class User extends BaseEntity<UUID> {
  private String name;
  private String email;
  private Tenant tenant;
}
