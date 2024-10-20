package tovar.infrastructure.persistent.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import tovar.domain.model.base.Tenant;
import tovar.infrastructure.persistent.entities.TenantEntity;

@ApplicationScoped
public class TenantRepositoryImpl extends BaseRepositoryImpl<Tenant, TenantEntity, Long> {
  protected TenantRepositoryImpl() {
    super(TenantEntity.class);
  }

  @Override
  protected Tenant toDTO(TenantEntity entity) {
    return Tenant.builder().id(entity.getId()).name(entity.getName()).build();
  }

  @Override
  protected TenantEntity toEntity(Tenant dto) {
    return TenantEntity.builder().id(dto.getId()).name(dto.getName()).build();
  }

}
