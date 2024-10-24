package tovar.domain.model.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tovar.domain.model.report.specification.ContainsSpecification;
import tovar.domain.model.report.specification.EqualsSpecification;
import tovar.domain.model.report.specification.ReportSpecification;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FilterReport {
  private String field;
  private FilterOperator filterOperator;
  private Object value;

  public ReportSpecification<FilterReport> toSpecification() {
    switch (filterOperator) {
      case EQUAL:
        return new EqualsSpecification(field, value);
      case CONTAINS:
        return new ContainsSpecification(field, (String) value);
      default:
        throw new IllegalArgumentException("Operator is not supported:" + filterOperator);

    }
  }

}
