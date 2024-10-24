package tovar.domain.repository;

import java.util.List;
import java.util.Optional;

import io.smallrye.mutiny.Uni;
import tovar.domain.model.base.BaseEntity;

public interface IGenericRepository<T extends BaseEntity<K>, K> {

  Uni<Optional<T>> getById(K id);

  Uni<List<T>> getAll();

  Uni<T> save(T report);

  Uni<T> update(T report);

  Uni<Void> remove(K id);

  Uni<Void> removeAll(List<T> entities);
}
