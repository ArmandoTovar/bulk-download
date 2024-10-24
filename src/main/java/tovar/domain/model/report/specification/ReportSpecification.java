package tovar.domain.model.report.specification;

public interface ReportSpecification<T> {
  boolean isSatisfiedBy(T item);

  String toSql();
}
