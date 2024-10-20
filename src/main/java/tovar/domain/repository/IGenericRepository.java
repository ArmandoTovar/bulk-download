package tovar.domain.repository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import tovar.domain.model.base.BaseEntity;

public interface IGenericRepository<T extends BaseEntity<K>, K> {

  Optional<T> findById(K id);

  Iterator<T> findAll();

  T save(T report);

  T update(T report);

  void delete(K id);

  void deleteAll(List<T> entities);
}
