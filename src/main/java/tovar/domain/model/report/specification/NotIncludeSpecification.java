package tovar.domain.model.report.specification;

import java.util.List;

import lombok.AllArgsConstructor;
import tovar.domain.model.report.FilterReport;

@AllArgsConstructor
public class NotIncludeSpecification implements ReportSpecification<FilterReport> {
  private final String field;
  private final List<?> values;

  @Override
  public boolean isSatisfiedBy(FilterReport filter) {
    return filter.getField().equals(field) && !values.contains(filter.getValue());
  }

  @Override
  public String toSql() {
    String notInClause = String.join(", ", values.stream().map(Object::toString).toArray(String[]::new));
    return String.format("%s NOT IN (%s)", field, notInClause);
  }
}
