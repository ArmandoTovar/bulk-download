package tovar.infrastructure.adapter.rest;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import tovar.domain.model.base.Tenant;
import tovar.domain.service.IGenericCrudService;
import tovar.infrastructure.adapter.services.TenantServiceImpl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@Path("/tenants")
@Produces(MediaType.APPLICATION_JSON)
@ValidateModel
@ApplicationScoped
public class TenantController extends BaseController<Tenant, Long> {

  @Inject
  private TenantServiceImpl tenantService;

  @Override
  protected IGenericCrudService<Tenant, Long> getService() {
    return tenantService;
  }

}
