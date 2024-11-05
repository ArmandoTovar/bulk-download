package tovar.infrastructure.adapter.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import tovar.domain.model.report.Report;
import tovar.domain.service.IGenericCrudService;
import tovar.infrastructure.adapter.services.ReportColumnFilterService;
import tovar.infrastructure.adapter.services.ReportServiceImpl;

import java.util.Map;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@Path("/reports")
@Produces(MediaType.APPLICATION_JSON)
@ValidateModel
@ApplicationScoped
public class ReportController extends BaseController<Report, UUID> {

  @Inject
  private ReportServiceImpl reportService;

  @Override
  protected IGenericCrudService<Report, UUID> getService() {
    return reportService;
  }

  @Inject
  ReportColumnFilterService reportColumnFilterService;

  @GET
  @Path("/allowed-filters")
  @Produces(MediaType.APPLICATION_JSON)
  public Map<String, Object> getAllowedFilters(@QueryParam("tableName") String tableName) {
    return reportColumnFilterService.getColumnFilters(tableName);
  }
}
