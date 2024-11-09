package tovar.infrastructure.adapter.services;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import tovar.application.service.TenantCrudService;
import tovar.domain.model.base.Tenant;
import tovar.infrastructure.persistent.entities.TenantEntity;
import tovar.infrastructure.persistent.repositories.BaseRepositoryImpl;
import tovar.infrastructure.persistent.repositories.TenantRepositoryImpl;

@ApplicationScoped
@WithSession
public class TenantServiceImpl extends TenantCrudService {

  @Inject
  private TenantRepositoryImpl tenantRepository;

  @Override
  protected BaseRepositoryImpl<Tenant, TenantEntity, Long> getRepository() {
    return tenantRepository;
  }

}
