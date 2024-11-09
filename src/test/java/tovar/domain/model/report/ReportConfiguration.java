package tovar.domain.model.report;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReportConfiguration {
  public String tableName;
  public List<FilterReport> filters;
  public List<String> selectedColumn;

}
