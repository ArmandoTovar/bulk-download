package tovar.infrastructure.adapter.services;

import java.util.UUID;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import tovar.application.service.UserCrudService;
import tovar.domain.model.base.Tenant;
import tovar.domain.model.base.User;
import tovar.domain.repository.IGenericRepository;
import tovar.infrastructure.persistent.entities.UserEntity;
import tovar.infrastructure.persistent.repositories.BaseRepositoryImpl;
import tovar.infrastructure.persistent.repositories.TenantRepositoryImpl;
import tovar.infrastructure.persistent.repositories.UserRepositoryImpl;

@ApplicationScoped
@WithSession
public class UserServiceImpl extends UserCrudService {

  @Inject
  private TenantRepositoryImpl tenantRepository;

  @Inject
  private UserRepositoryImpl userRepository;

  @Override
  protected BaseRepositoryImpl<User, UserEntity, UUID> getRepository() {
    return userRepository;
  }

  @Override
  protected IGenericRepository<Tenant, Long> getTenantRepository() {
    return tenantRepository;
  }

}
