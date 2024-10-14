package tovar.domain.model;

import java.util.List;

import lombok.Data;

@Data
public class ReportConfiguration {
  public List<FilterReport> filters;
  public List<String> selectedColumn;

}
