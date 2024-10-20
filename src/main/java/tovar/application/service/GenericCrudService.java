package tovar.application.service;

import java.util.List;
import java.util.Optional;

import io.smallrye.mutiny.Uni;
import tovar.domain.model.base.BaseEntity;
import tovar.domain.repository.IGenericRepository;
import tovar.domain.service.IGenericCrudService;

public abstract class GenericCrudService<T extends BaseEntity<K>, K> implements IGenericCrudService<T, K> {

  protected abstract IGenericRepository<T, K> getRepository();

  @Override
  public Uni<T> create(T entity) {
    return getRepository().save(entity);
  }

  @Override
  public Uni<Void> delete(K id) {
    return getRepository().remove(id);

  }

  @Override
  public Uni<Void> deleteAll(List<T> entities) {
    return getRepository().removeAll(entities);
  }

  @Override
  public Uni<Optional<T>> getById(K id) {
    return getRepository().getById(id);
  }

  @Override
  public Uni<T> update(T entity) {
    return getRepository().update(entity);
  }

  @Override
  public Uni<List<T>> getAll() {
    return getRepository().getAll();
  }

}
