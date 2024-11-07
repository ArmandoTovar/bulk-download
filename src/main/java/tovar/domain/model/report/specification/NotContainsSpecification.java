package tovar.domain.model.report.specification;

import java.util.List;

import lombok.AllArgsConstructor;
import tovar.domain.model.report.FilterReport;

@AllArgsConstructor
public class NotContainsSpecification implements ReportSpecification<FilterReport> {
  private final String field;
  private final String value;

  @Override
  public boolean isSatisfiedBy(FilterReport filter) {
    return filter.getField().equals(field) && !filter.getValue().toString().contains(value);
  }

  @Override
  public String toSql() {
    return String.format("%s NOT LIKE ?", field);
  }

  public List<?> getValues() {
    return List.of("%" + value + "%");
  }
}
