package tovar.domain.model.validator;

import java.util.List;

public interface Validator<T> {
  List<String> validate(T entity);
}
