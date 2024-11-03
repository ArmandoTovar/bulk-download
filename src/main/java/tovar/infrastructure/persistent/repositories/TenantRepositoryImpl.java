package tovar.infrastructure.persistent.repositories;

import io.smallrye.mutiny.Uni;
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
  protected Uni<TenantEntity> toEntity(Tenant dto) {
    return findById(dto.getId()).onItem().ifNull().continueWith(TenantEntity.builder().build()).map(entity -> {
      entity.setName(dto.getName());
      entity.setId(dto.getId());
      return entity;
    });
  }

}
