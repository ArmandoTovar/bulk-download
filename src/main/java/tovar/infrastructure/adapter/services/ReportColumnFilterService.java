package tovar.infrastructure.adapter.services;

import java.util.Map;
import jakarta.enterprise.context.ApplicationScoped;
import tovar.application.service.ColumnFilterService;

@ApplicationScoped
public class ReportColumnFilterService extends ColumnFilterService {
  public Map<String, Object> getFiltersForEntity(String tableName) throws ClassNotFoundException {
    return getColumnFilters(tableName);
  }
}
