package tovar.infrastructure.adapter.persistence;

import java.util.List;
import java.util.Optional;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import tovar.application.service.GenericCrudService;
import tovar.domain.model.base.BaseEntity;
import tovar.infrastructure.persistent.repositories.BaseRepositoryImpl;

@ApplicationScoped
@WithSession
public abstract class BaseServiceImpl<D extends BaseEntity<K>, E, K> extends GenericCrudService<D, K> {

  protected abstract BaseRepositoryImpl<D, E, K> getRepository();

  public Uni<Optional<D>> getById(K id) {
    return getRepository().getById(id);
  }

  public Uni<List<D>> getAll() {
    return getRepository().getAll();
  }

  public Uni<D> save(D dto) {
    return getRepository().save(dto);
  }

  @Override
  public Uni<D> update(D dto) {
    return getRepository().update(dto);
  }

  @Override
  public Uni<Void> delete(K id) {
    return getRepository().remove(id);
  }

  @Override
  public Uni<Void> deleteAll(List<D> dtos) {
    return getRepository().removeAll(dtos);
  }

  @Override
  public Uni<D> create(D entity) {
    return getRepository().save(entity);
  }
}
