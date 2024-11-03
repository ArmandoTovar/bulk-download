package tovar.infrastructure.persistent.repositories;

import java.util.UUID;

import io.agroal.api.AgroalDataSource;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import tovar.infrastructure.persistent.entities.ReportEntity;
import tovar.infrastructure.persistent.entities.TenantEntity;
import tovar.infrastructure.persistent.entities.UserEntity;
import tovar.domain.model.report.Report;

@ApplicationScoped
public class ReportRepositoryImpl extends BaseRepositoryImpl<Report, ReportEntity, UUID> {
  @Inject
  AgroalDataSource dataSource;

  protected ReportRepositoryImpl() {
    super(ReportEntity.class);
  }

  @Override
  protected Report toDTO(ReportEntity entity) {
    return Report.builder()
        .id(entity.getId())
        .name(entity.getName())
        .lastDownload(entity.getLastDownload())
        .userId(entity.getUser().getId())
        .reportConfiguration(entity.getReportConfiguration())
        .executed(entity.getExecuted())
        .reportFormat(entity.getReportFormat())
        .reportState(entity.getReportState())
        .frecuencyType(entity.getFrecuencyType())
        .tenantId(entity.getTenant().getId()).build();
  }

  @Override
  protected Uni<ReportEntity> toEntity(Report dto) {
    return findById(dto.getId()).onItem().ifNull().continueWith(ReportEntity.builder().build()).map(entity -> {
      entity.setTenant(TenantEntity.builder().id(dto.getTenantId()).build());
      entity.setName(dto.getName());
      entity.setLastDownload(dto.getLastDownload());
      entity.setUser(UserEntity.builder().id(dto.getUserId()).build());
      entity.setReportState(dto.getReportState());
      entity.setFrecuencyType(dto.getFrecuencyType());
      entity.setReportFormat(dto.getReportFormat());
      entity.setReportConfiguration(dto.getReportConfiguration());
      entity.setExecuted(dto.getExecuted());
      entity.setId(dto.getId());
      return entity;
    });
  }

}
