package tovar.infrastructure.persistent.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import tovar.domain.repository.SqlReportSourceRepository;

@ApplicationScoped
public class SqlReportSourceRepositoryImpl extends SqlReportSourceRepository {
  @Inject
  AgroalDataSource dataSource;

  @Override
  public List<Map<String, Object>> execute(String query) {
    List<Map<String, Object>> resultList = new ArrayList<>();
    try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery()) {
      int columnCount = rs.getMetaData().getColumnCount();
      List<String> columnNames = new ArrayList<>();
      for (int i = 1; i <= columnCount; i++) {
        columnNames.add(rs.getMetaData().getColumnName(i));
      }
      while (rs.next()) {
        Map<String, Object> row = new HashMap<>();
        for (String columnName : columnNames) {
          row.put(columnName, rs.getObject(columnName));
        }
        resultList.add(row);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return resultList;
  }
}
