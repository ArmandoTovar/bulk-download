package tovar.infrastructure.adapter.rest;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import tovar.domain.model.base.Tenant;
import tovar.infrastructure.adapter.persistence.BaseServiceImpl;
import tovar.infrastructure.adapter.persistence.TenantService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@Path("/tenants")
@Produces(MediaType.APPLICATION_JSON)
@ValidateModel
@ApplicationScoped
public class TenantController extends BaseController<Tenant, Long> {

  @Inject
  private TenantService tenantService;

  @Override
  protected BaseServiceImpl<Tenant, ?, Long> getService() {
    return tenantService;
  }
}
