package tovar.infrastructure.persistent.repositories;

import java.util.UUID;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import tovar.domain.model.base.User;
import tovar.infrastructure.persistent.entities.TenantEntity;
import tovar.infrastructure.persistent.entities.UserEntity;

@ApplicationScoped
public class UserRepositoryImpl extends BaseRepositoryImpl<User, UserEntity, UUID> {

  protected UserRepositoryImpl() {
    super(UserEntity.class);
  }

  @Override
  protected User toDTO(UserEntity entity) {
    return User.builder().name(entity.getName()).email(entity.getEmail()).tenantId(entity.getTenant().getId())
        .id(entity.getId())
        .build();
  }

  @Override
  protected Uni<UserEntity> toEntity(User dto) {
    return findById(dto.getId()).onItem().ifNull().continueWith(UserEntity.builder().build()).map(entity -> {
      entity.setTenant(TenantEntity.builder().id(dto.getTenantId()).build());
      entity.setName(dto.getName());
      entity.setEmail(dto.getEmail());
      entity.setId(dto.getId());
      return entity;
    });
  }

}
