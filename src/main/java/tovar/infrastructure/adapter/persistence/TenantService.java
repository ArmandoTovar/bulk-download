package tovar.infrastructure.adapter.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import tovar.domain.model.base.Tenant;
import tovar.infrastructure.persistent.entities.TenantEntity;
import tovar.infrastructure.persistent.repositories.BaseRepositoryImpl;
import tovar.infrastructure.persistent.repositories.TenantRepositoryImpl;

@ApplicationScoped
public class TenantService extends BaseServiceImpl<Tenant, TenantEntity, Long> {

  @Inject
  private TenantRepositoryImpl tenantRepository;

  @Override
  protected BaseRepositoryImpl<Tenant, TenantEntity, Long> getRepository() {
    return tenantRepository;
  }

}
