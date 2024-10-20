package tovar.domain.service;

import java.util.List;

import tovar.domain.model.base.BaseEntity;

public interface IGenericCrudService<T extends BaseEntity<K>, K> {
  T getById(K id);

  T create(T entity);

  T update(T entity);

  boolean delete(K id);

  boolean deleteAll(List<T> entities);
}
