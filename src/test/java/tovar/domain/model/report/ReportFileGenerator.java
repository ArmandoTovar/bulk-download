package tovar.domain.model.report;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface ReportFileGenerator {
  File generateFile(Report report, List<Map<String, Object>> data);
}
