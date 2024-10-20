package tovar.infrastructure.adapter.rest;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import tovar.domain.model.base.User;
import tovar.infrastructure.adapter.persistence.BaseServiceImpl;
import tovar.infrastructure.adapter.persistence.UserService;

import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@Path("/users")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class UserController extends BaseController<User, UUID> {

  @Inject
  private UserService userService;

  @Override
  protected BaseServiceImpl<User, ?, UUID> getService() {
    return userService;
  }
}
