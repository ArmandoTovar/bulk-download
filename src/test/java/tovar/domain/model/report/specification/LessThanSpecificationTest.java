package tovar.domain.model.report.specification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import tovar.domain.model.report.FilterOperator;
import tovar.domain.model.report.FilterReport;

@AllArgsConstructor
public class LessThanSpecificationTest {
  static ReportSpecification<FilterReport> mockReportSpecification;

  @BeforeAll
  static void init() {
    mockReportSpecification = new LessThanSpecification("field", 2);
  }

  @Test
  public void ItIsSatisfiedBy() {
    FilterReport mockFilter = FilterReport.builder().field("field").value(1).filterOperator(FilterOperator.LESS)
        .build();
    assertTrue(mockReportSpecification.isSatisfiedBy(mockFilter), "Is SatifiedBy condition");
  }

  @Test
  public void ItIsNotSatisfiedBy() {
    FilterReport mockFilter = FilterReport.builder().field("field").value(3).filterOperator(FilterOperator.LESS)
        .build();
    assertFalse(mockReportSpecification.isSatisfiedBy(mockFilter), "Is not SatifiedBy condition");
  }

  @Test
  public void ItToSql() {
    assertEquals("field < ?", mockReportSpecification.toSql());
  }
}
