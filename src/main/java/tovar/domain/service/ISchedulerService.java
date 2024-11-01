package tovar.domain.service;

import java.util.UUID;

import io.smallrye.mutiny.Uni;
import tovar.domain.model.download.Download;

public interface ISchedulerService {
  Uni<Void> schedulerJob(Download downlod);

  Uni<Void> triggerJobNow(Download download);

  Uni<Void> deleteJob(UUID taskId);
}
