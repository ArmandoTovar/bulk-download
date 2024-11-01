package tovar.domain.model.report;

import java.util.List;
import java.util.Map;

public interface ReportSourceRepository {
  List<Map<String, Object>> getData(Report report);

  List<Map<String, Object>> execute(String query);

}
