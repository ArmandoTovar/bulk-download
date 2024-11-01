package tovar.infrastructure.persistent.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.agroal.api.AgroalDataSource;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import tovar.infrastructure.persistent.entities.ReportEntity;
import tovar.infrastructure.persistent.entities.TenantEntity;
import tovar.infrastructure.persistent.entities.UserEntity;
import tovar.domain.model.report.FrecuencyType;
import tovar.domain.model.report.Report;
import tovar.domain.model.report.ReportConfiguration;
import tovar.domain.model.report.ReportFormat;
import tovar.domain.model.report.ReportState;

@ApplicationScoped
public class ReportRepositoryImpl extends BaseRepositoryImpl<Report, ReportEntity, UUID> {
  @Inject
  AgroalDataSource dataSource;

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

  public Optional<Report> getReportByIdNonReactive(UUID id) {
    try (Connection conn = dataSource.getConnection()) {
      PreparedStatement ps = conn.prepareStatement(
          "SELECT id, name, reportformat, frecuencytype, reportstate, reportconfiguration" +
              " FROM report WHERE id = ?");
      ps.setObject(1, id);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        Report report = new Report();
        report.setId((UUID) rs.getObject("id"));
        report.setName(rs.getString("name"));
        report.setReportFormat(ReportFormat.valueOf(rs.getString("reportformat")));
        report.setFrecuencyType(FrecuencyType.valueOf(rs.getString("frecuencytype")));
        report.setReportState(ReportState.values()[rs.getInt("reportstate")]);
        String reportConfigJson = rs.getString("reportconfiguration");
        ReportConfiguration reportConfiguration = parseReportConfiguration(reportConfigJson);
        report.setReportConfiguration(reportConfiguration);
        return Optional.of(report);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  private ReportConfiguration parseReportConfiguration(String json) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.readValue(json, ReportConfiguration.class);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
