package tovar.application.service;

import lombok.AllArgsConstructor;
import tovar.domain.model.base.Tenant;
import tovar.domain.repository.IGenericRepository;
import tovar.domain.repository.TenantRepository;

@AllArgsConstructor
public class TenantCrudService extends GenericCrudService<Tenant, Long> {

  private TenantRepository repository;

  @Override
  protected IGenericRepository<Tenant, Long> getRepository() {
    return repository;
  }

}
