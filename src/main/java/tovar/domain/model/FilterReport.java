package tovar.domain.model;

import lombok.Data;

@Data
public class FilterReport {
  private String field;
  private FilterOperator filterOperator;
  private Object value;

}
