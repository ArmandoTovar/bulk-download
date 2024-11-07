package tovar.domain.model.report.specification;

import java.util.List;

public interface ReportSpecification<T> {
  boolean isSatisfiedBy(T item);

  String toSql();

  List<?> getValues();
}
