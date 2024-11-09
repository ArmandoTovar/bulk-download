package tovar.domain.model.report;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tovar.domain.model.report.specification.*;

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
      case NOT_CONTAINS:
        return new NotContainsSpecification(field, (String) value);
      case INCLUDE:
        return new IncludeSpecification(field, (List<String>) value);
      case NOT_INCLUDE:
        return new NotIncludeSpecification(field, (List<String>) value);
      case GREATER:
        return new GreaterThanSpecification(field, (String) value);
      case LESS:
        return new LessThanSpecification(field, (String) value);
      default:
        throw new IllegalArgumentException("Operator is not supported:" + filterOperator);

    }
  }

}
