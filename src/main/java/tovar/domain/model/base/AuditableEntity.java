package tovar.domain.model.base;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class AuditableEntity<K> extends BaseEntity<K> {
  protected LocalDateTime createDate;
  protected LocalDateTime updateDate;
  protected String createBy;
  protected String updateBy;
}
