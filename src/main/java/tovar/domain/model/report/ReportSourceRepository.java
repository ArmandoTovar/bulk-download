package tovar.domain.model.report;

import java.util.List;
import java.util.Map;

import io.smallrye.mutiny.Uni;

public interface ReportSourceRepository {
  Uni<List<Map<String, Object>>> getData(Report report);

  Uni<List<Map<String, Object>>> execute(String query);

}
