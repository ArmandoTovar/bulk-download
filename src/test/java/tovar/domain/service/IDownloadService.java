package tovar.domain.service;

import java.util.UUID;

import io.smallrye.mutiny.Uni;
import tovar.domain.model.download.Download;

public interface IDownloadService extends IGenericCrudService<Download, UUID> {

  Uni<Void> executeTaskNow(UUID downloadId);

  Uni<Void> retryDownload(UUID downloadId);
}
