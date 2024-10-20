package tovar.infrastructure.persistent.repositories;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import tovar.domain.model.base.BaseEntity;
import tovar.domain.repository.IGenericRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class BaseRepositoryImpl<T extends BaseEntity<K>, E, K>
    implements IGenericRepository<T, K>, PanacheRepositoryBase<E, K> {

  private final Class<E> entityClass;

  protected BaseRepositoryImpl(Class<E> entityClass) {
    this.entityClass = entityClass;
  }

  protected abstract T toDTO(E entity);

  protected abstract E toEntity(T dto);

  public Uni<Optional<T>> getById(K id) {
    return Panache.withTransaction(() -> findById(id))
        .map(entity -> Optional.ofNullable(entity).map(this::toDTO));
  }

  public Uni<List<T>> getAll() {
    return listAll()
        .map(entities -> entities.stream()
            .map(this::toDTO)
            .collect(Collectors.toList()));
  }

  public Uni<T> save(T dto) {
    E entity = toEntity(dto);
    return Panache.withTransaction(() -> persist(entity))
        .map(v -> toDTO(entity));
  }

  public Uni<T> update(T dto) {
    return save(dto);
  }

  public Uni<Void> removeAll(List<T> dtos) {
    return Panache.withTransaction(() -> Uni.combine().all().unis(
        dtos.stream()
            .map(dto -> deleteById(dto.getId()))
            .collect(Collectors.toList()))
        .discardItems());
  }

  @Override
  public Uni<Void> remove(K id) {
    return Panache.withTransaction(() -> deleteById(id))
        .replaceWithVoid();
  }
}
