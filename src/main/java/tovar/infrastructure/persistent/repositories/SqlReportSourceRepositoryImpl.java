package tovar.infrastructure.persistent.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
  public List<Map<String, Object>> execute(String query, List<Object> parameters) {
    List<Map<String, Object>> results = new ArrayList<>();
    try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {

      int index = 1;
      for (Object parameter : parameters) {
        if (parameter instanceof String) {
          statement.setString(index, (String) parameter);
        } else if (parameter instanceof Integer) {
          statement.setInt(index, (Integer) parameter);
        } else if (parameter instanceof Long) {
          statement.setLong(index, (Long) parameter);
        } else if (parameter instanceof Double) {
          statement.setDouble(index, (Double) parameter);
        } else if (parameter instanceof Boolean) {
          statement.setBoolean(index, (Boolean) parameter);
        } else if (parameter == null) {
          statement.setNull(index, java.sql.Types.NULL);
        } else {
          statement.setObject(index, parameter);
        }
        index++;
      }

      ResultSet resultSet = statement.executeQuery();
      ResultSetMetaData metaData = resultSet.getMetaData();
      int columnCount = metaData.getColumnCount();

      while (resultSet.next()) {
        Map<String, Object> row = new HashMap<>();
        for (int i = 1; i <= columnCount; i++) {
          row.put(metaData.getColumnName(i), resultSet.getObject(i));
        }
        results.add(row);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return results;
  }

}
