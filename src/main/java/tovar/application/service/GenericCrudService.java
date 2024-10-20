package tovar.application.service;

import java.util.List;

import lombok.AllArgsConstructor;
import tovar.domain.model.base.BaseEntity;
import tovar.domain.repository.IGenericRepository;
import tovar.domain.service.IGenericCrudService;

@AllArgsConstructor
public class GenericCrudService<T extends BaseEntity<K>, K> implements IGenericCrudService<T, K> {
  private final IGenericRepository<T, K> genericRepository;

  @Override
  public T create(T entity) {
    return genericRepository.save(entity);
  }

  @Override
  public boolean delete(K id) {
    genericRepository.delete(id);
    return true;
  }

  @Override
  public boolean deleteAll(List<T> entities) {
    genericRepository.deleteAll(entities);
    return true;
  }

  @Override
  public T getById(K id) {
    return genericRepository.findById(id).orElseThrow();
  }

  @Override
  public T update(T entity) {
    return genericRepository.update(entity);
  }

}
