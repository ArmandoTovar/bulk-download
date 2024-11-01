package tovar.infrastructure.persistent.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import io.agroal.api.AgroalDataSource;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import tovar.domain.model.download.Download;
import tovar.domain.model.download.DownloadStatus;
import tovar.infrastructure.persistent.entities.DownloadEntity;
import tovar.infrastructure.persistent.entities.ReportEntity;

@ApplicationScoped
@WithSession
@WithTransaction
public class DownloadRepositoryImpl extends BaseRepositoryImpl<Download, DownloadEntity, UUID> {

  @Inject
  AgroalDataSource dataSource;

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
  protected DownloadEntity toEntity(Download dto) {
    return DownloadEntity.builder()
        .report(ReportEntity.builder().id(dto.getReportId()).build())
        .retryCount(dto.getRetryCount())
        .periodicity(dto.getPeriodicity())
        .status(dto.getStatus())
        .query(dto.getQuery())
        .id(dto.getId())
        .build();
  }

  public Optional<Download> getByIdNonReactive(UUID id) {
    try (Connection conn = dataSource.getConnection()) {
      PreparedStatement ps = conn.prepareStatement(
          "SELECT id, report_id, status, query, periodicity, retrycount, create_date, update_date, createby, updateby "
              +
              "FROM download WHERE id = ?");
      ps.setObject(1, id);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        Download download = new Download();
        download.setId((UUID) rs.getObject("id"));
        download.setReportId((UUID) rs.getObject("report_id"));
        download.setStatus(DownloadStatus.valueOf(rs.getString("status")));
        download.setQuery(rs.getString("query"));
        download.setPeriodicity(rs.getString("periodicity"));
        download.setRetryCount(rs.getInt("retrycount"));
        download.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
        download.setUpdateDate(rs.getTimestamp("update_date").toLocalDateTime());
        download.setCreateBy(rs.getString("createby"));
        download.setUpdateBy(rs.getString("updateby"));
        return Optional.of(download);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  public void updateNonReactive(Download download) {

    try (Connection conn = dataSource.getConnection()) {
      PreparedStatement ps = conn.prepareStatement(
          "UPDATE download SET report_id = ?, status = ?, query = ?, periodicity = ?, retrycount = ?, " +
              "create_date = ?, update_date = ?, createby = ?, updateby = ? WHERE id = ?");
      ps.setObject(1, download.getReportId());
      ps.setString(2, download.getStatus().name());
      ps.setString(3, download.getQuery());
      ps.setString(4, download.getPeriodicity());
      ps.setInt(5, download.getRetryCount());
      ps.setTimestamp(6, java.sql.Timestamp.valueOf(download.getCreateDate()));
      ps.setTimestamp(7, java.sql.Timestamp.valueOf(download.getUpdateDate()));
      ps.setString(8, download.getCreateBy());
      ps.setString(9, download.getUpdateBy());
      ps.setObject(10, download.getId());
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
