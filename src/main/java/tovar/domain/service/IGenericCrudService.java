package tovar.domain.service;

import java.util.List;
import java.util.Optional;

import io.smallrye.mutiny.Uni;
import tovar.domain.model.base.BaseEntity;

public interface IGenericCrudService<T extends BaseEntity<K>, K> {
  Uni<Optional<T>> getById(K id);

  Uni<List<T>> getAll();

  Uni<T> create(T entity);

  Uni<T> update(T entity);

  Uni<Void> delete(K id);

  Uni<Void> deleteAll(List<T> entities);
}
