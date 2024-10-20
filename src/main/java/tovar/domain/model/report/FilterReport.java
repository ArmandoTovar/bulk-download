package tovar.domain.model.report;

import lombok.Data;

@Data
public class FilterReport {
  private String field;
  private FilterOperator filterOperator;
  private Object value;

}
