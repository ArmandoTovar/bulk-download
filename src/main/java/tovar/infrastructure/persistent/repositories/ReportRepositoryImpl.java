package tovar.infrastructure.persistent.repositories;

import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import tovar.infrastructure.persistent.entities.ReportEntity;
import tovar.infrastructure.persistent.entities.TenantEntity;
import tovar.infrastructure.persistent.entities.UserEntity;
import tovar.domain.model.report.Report;

@ApplicationScoped
public class ReportRepositoryImpl extends BaseRepositoryImpl<Report, ReportEntity, UUID> {
  protected ReportRepositoryImpl() {
    super(ReportEntity.class);
  }

  @Override
  protected Report toDTO(ReportEntity entity) {
    return Report.builder().id(entity.getId()).name(entity.getName()).userId(entity.getUser().getId())
        .tenantId(entity.getTenant().getId()).build();
  }

  @Override
  protected ReportEntity toEntity(Report dto) {
    return ReportEntity.builder()
        .name(dto.getName())
        .tenant(TenantEntity.builder().id(dto.getTenantId()).build())
        .user(UserEntity.builder().id(dto.getUserId()).build())
        .reportState(dto.getReportState())
        .frecuencyType(dto.getFrecuencyType())
        .reportFormat(dto.getReportFormat())
        .reportConfiguration(dto.getReportConfiguration())
        .id(dto.getId()).build();
  }

}
