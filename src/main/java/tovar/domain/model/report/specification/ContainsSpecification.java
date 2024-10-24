package tovar.domain.model.report.specification;

import lombok.AllArgsConstructor;
import tovar.domain.model.report.FilterReport;

@AllArgsConstructor
public class ContainsSpecification implements ReportSpecification<FilterReport> {
  private final String field;
  private final String value;

  @Override
  public boolean isSatisfiedBy(FilterReport filter) {
    return filter.getField().equals(field) && filter.getValue().toString().contains(value);
  }

  @Override
  public String toSql() {
    return String.format("%s LIKE '%%%s%%'", field, value);
  }
}
