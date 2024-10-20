package tovar.infrastructure.persistent.repositories;

import java.util.UUID;

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
    return User.builder().name(entity.getName()).email(entity.getEmail()).tenant(entity.getTenant()).id(entity.getId())
        .build();
  }

  @Override
  protected UserEntity toEntity(User dto) {
    return UserEntity.builder().name(dto.getName()).email(dto.getEmail())
        .tenant(TenantEntity.builder().name(dto.getTenant().getName()).id(dto.getTenant().getId()).build())
        .id(dto.getId())
        .build();
  }

}
