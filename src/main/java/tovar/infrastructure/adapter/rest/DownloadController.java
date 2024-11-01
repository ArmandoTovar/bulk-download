package tovar.infrastructure.adapter.rest;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import tovar.domain.model.download.Download;
import tovar.domain.service.IGenericCrudService;
import tovar.infrastructure.adapter.services.DownloadServiceImpl;
import java.util.UUID;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@Path("/downloads")
@Produces(MediaType.APPLICATION_JSON)
@ValidateModel
@ApplicationScoped
public class DownloadController extends BaseController<Download, UUID> {

  @Inject
  private DownloadServiceImpl downloadService;

  @Override
  protected IGenericCrudService<Download, UUID> getService() {
    return downloadService;
  }

  @Path("/{id}/execute")
  @Produces(MediaType.APPLICATION_JSON)
  public Uni<Void> executeTaskNow(@PathParam("id") UUID id) {
    return downloadService.executeTaskNow(id);
  }

  @POST
  @Path("/{id}/retry")
  @Produces(MediaType.APPLICATION_JSON)
  public Uni<Void> retryTask(@PathParam("id") UUID id) {
    return downloadService.retryDownload(id);
  }

}
