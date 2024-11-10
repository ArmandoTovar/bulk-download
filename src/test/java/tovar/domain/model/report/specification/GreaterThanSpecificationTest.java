package tovar.domain.model.report.specification;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;
import tovar.domain.model.report.FilterOperator;
import tovar.domain.model.report.FilterReport;

@QuarkusTest
public class GreaterThanSpecificationTest {
  static ReportSpecification<FilterReport> mockReportSpecification;

  @BeforeAll
  static void init() {
    mockReportSpecification = new GreaterThanSpecification("field", 2);
  }

  @Test
  public void ItIsSatisfiedBy() {
    FilterReport mockFilter = FilterReport.builder().field("field").value(3).filterOperator(FilterOperator.GREATER)
        .build();
    Assertions.assertTrue(mockReportSpecification.isSatisfiedBy(mockFilter), "Is SatifiedBy condition");
  }

  @Test
  public void ItIsNotSatisfiedBy() {
    FilterReport mockFilter = FilterReport.builder().field("field").value(1).filterOperator(FilterOperator.GREATER)
        .build();
    Assertions.assertFalse(mockReportSpecification.isSatisfiedBy(mockFilter), "Is not SatifiedBy condition");
  }

  @Test
  public void ItToSql() {
    Assertions.assertEquals("field > ?", mockReportSpecification.toSql());
  }

}
