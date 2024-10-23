package tovar.application.service;

import java.util.List;
import java.util.UUID;

import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;
import tovar.domain.model.base.Tenant;
import tovar.domain.model.base.User;
import tovar.domain.model.validator.ValidationException;
import tovar.domain.repository.IGenericRepository;

@AllArgsConstructor
public abstract class UserCrudService extends GenericCrudService<User, UUID> {

  protected abstract IGenericRepository<Tenant, Long> getTenantRepository();

  @Override
  public Uni<User> create(User entity) {
    return getTenantRepository().getById(entity.getTenant().getId())
        .onItem().transformToUni(optionalTenant -> {
          if (optionalTenant.isEmpty()) {
            return Uni.createFrom().failure(new ValidationException(
                List.of(String.format("Tenant ID %s doesn't exist", entity.getTenant().getId())),
                UUID.randomUUID().toString()));
          }
          return super.create(entity);
        });
  }

}
