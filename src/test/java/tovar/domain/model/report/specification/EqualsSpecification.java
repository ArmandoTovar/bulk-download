package tovar.domain.model.report.specification;

import java.util.List;

import lombok.AllArgsConstructor;
import tovar.domain.model.report.FilterReport;

@AllArgsConstructor
public class EqualsSpecification implements ReportSpecification<FilterReport> {
  private final String field;
  private final Object value;

  @Override
  public boolean isSatisfiedBy(FilterReport filter) {
    return filter.getField().equals(field) && filter.getValue().equals(value);
  }

  @Override
  public String toSql() {
    return String.format("%s = ?", field);
  }

  @Override
  public List<?> getValues() {
    return List.of(value);
  }
}
