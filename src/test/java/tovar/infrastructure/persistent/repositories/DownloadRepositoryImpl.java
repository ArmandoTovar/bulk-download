package tovar.infrastructure.persistent.repositories;

import java.util.UUID;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import tovar.domain.model.download.Download;
import tovar.infrastructure.persistent.entities.DownloadEntity;
import tovar.infrastructure.persistent.entities.ReportEntity;

@ApplicationScoped
@WithSession
public class DownloadRepositoryImpl extends BaseRepositoryImpl<Download, DownloadEntity, UUID> {

  protected DownloadRepositoryImpl() {
    super(DownloadEntity.class);
  }

  @Override
  protected Download toDTO(DownloadEntity entity) {
    return Download.builder()
        .reportId((entity.getReport().getId()))
        .retryCount(entity.getRetryCount())
        .periodicity(entity.getPeriodicity())
        .status(entity.getStatus())
        .query(entity.getQuery())
        .id(entity.getId())
        .build();
  }

  @Override
  protected Uni<DownloadEntity> toEntity(Download dto) {
    return findById(dto.getId()).onItem().ifNull().continueWith(DownloadEntity.builder().build()).map(entity -> {
      entity.setReport(ReportEntity.builder().id(dto.getReportId()).build());
      entity.setRetryCount(dto.getRetryCount());
      entity.setPeriodicity(dto.getPeriodicity());
      entity.setStatus(dto.getStatus());
      entity.setQuery(dto.getQuery());
      entity.setId(dto.getId());
      return entity;
    });
  }
}
