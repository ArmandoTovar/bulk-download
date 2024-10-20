package tovar.domain.model.base;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class Tenant extends BaseEntity<Long> {
  private String name;

}
