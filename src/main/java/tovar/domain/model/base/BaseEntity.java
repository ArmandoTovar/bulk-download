package tovar.domain.model.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseEntity<K> {
  protected K id;

}
