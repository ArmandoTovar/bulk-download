package tovar.domain.model.report;

import java.util.List;

import lombok.Data;

@Data
public class ReportConfiguration {
  public List<FilterReport> filters;
  public List<String> selectedColumn;
}
