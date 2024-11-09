package tovar.domain.model.report.specification;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import tovar.domain.model.report.FilterReport;

@AllArgsConstructor
public class IncludeSpecification implements ReportSpecification<FilterReport> {
  private final String field;
  private final List<?> values;

  @Override
  public boolean isSatisfiedBy(FilterReport filter) {
    return filter.getField().equals(field) && values.contains(filter.getValue());
  }

  @Override
  public String toSql() {
    String inClause = values.stream().map(v -> "?").collect(Collectors.joining(", "));
    return String.format("%s IN (%s)", field, inClause);
  }

  @Override
  public List<?> getValues() {
    return values;
  }
}
