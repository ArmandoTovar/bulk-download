package tovar.infrastructure.adapter.persistence;

import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import tovar.domain.model.base.User;
import tovar.infrastructure.persistent.entities.UserEntity;
import tovar.infrastructure.persistent.repositories.BaseRepositoryImpl;
import tovar.infrastructure.persistent.repositories.UserRepositoryImpl;

@ApplicationScoped
public class UserService extends BaseServiceImpl<User, UserEntity, UUID> {

  @Inject
  private UserRepositoryImpl userRepository;

  @Override
  protected BaseRepositoryImpl<User, UserEntity, UUID> getRepository() {
    return userRepository;
  }
}
