package tovar.infrastructure.adapter.services;

import java.util.UUID;

import org.quartz.*;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import tovar.domain.model.download.Download;
import tovar.domain.service.ISchedulerService;
import tovar.infrastructure.adapter.services.jobs.QueryJob;

@ApplicationScoped
public class QuartzSchedulerService implements ISchedulerService {
  @Inject
  Scheduler quartz;

  @Override
  public Uni<Void> schedulerJob(Download download) {
    return Uni.createFrom().voidItem().onItem().invoke(() -> {
      JobDetail jobDetail = JobBuilder.newJob(QueryJob.class).withIdentity(download.getId().toString())
          .usingJobData("downloadId", download.getId().toString()).build();
      Trigger trigger = TriggerBuilder.newTrigger().withIdentity(download.getId().toString())
          .withSchedule(CronScheduleBuilder.cronSchedule(download.getPeriodicity())).build();
      try {
        quartz.scheduleJob(jobDetail, trigger);
      } catch (SchedulerException e) {
        throw new RuntimeException("Failed ot schedule job", e);
      }
    }).onFailure(SchedulerException.class).invoke(err -> {
      System.err.println("SchedulerException occurred while scheduling job for download ID: " + download.getId());
      err.printStackTrace();
    });
  }

  @Override
  public Uni<Void> triggerJobNow(Download download) {
    JobKey jobKey = new JobKey(download.getId().toString());

    return Uni.createFrom().voidItem()
        .onItem().transformToUni(ignored -> {
          try {
            if (!quartz.checkExists(jobKey)) {
              return schedulerJob(download)
                  .onItem().transformToUni(scheduled -> {
                    try {
                      quartz.triggerJob(jobKey);
                    } catch (SchedulerException e) {
                      e.printStackTrace();
                    }
                    return Uni.createFrom().voidItem();
                  });
            } else {
              quartz.triggerJob(jobKey);
              return Uni.createFrom().voidItem();
            }
          } catch (SchedulerException e) {
            return Uni.createFrom().failure(new RuntimeException("Failed to trigger Job", e));
          }
        })
        .onFailure(SchedulerException.class).invoke(err -> {
          System.err.println("SchedulerException occurred while triggering job for download id: " + download.getId());
          err.printStackTrace();
        });
  }

  @Override
  public Uni<Void> deleteJob(UUID taskId) {
    return Uni.createFrom().voidItem()
        .onItem().invoke(() -> {
          JobKey jobKey = new JobKey(taskId.toString());
          try {
            quartz.deleteJob(jobKey);
          } catch (SchedulerException e) {
            throw new RuntimeException("Failed to delete job", e);
          }
        })
        .onFailure(SchedulerException.class).invoke(err -> {
          System.err.println("SchedulerException occurred while deleting job with task ID: " + taskId);
          err.printStackTrace();
        });
  }
}
