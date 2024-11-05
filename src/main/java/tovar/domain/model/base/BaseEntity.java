package tovar.domain.model.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import tovar.domain.model.validator.RestrictedTable;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@RestrictedTable
public class BaseEntity<K> {
  protected K id;

}
