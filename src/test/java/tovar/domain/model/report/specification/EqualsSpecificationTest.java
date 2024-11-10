package tovar.domain.model.report.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import tovar.domain.model.report.FilterOperator;
import tovar.domain.model.report.FilterReport;

@AllArgsConstructor
public class EqualsSpecificationTest {
  static ReportSpecification<FilterReport> mockReportSpecification;

  @BeforeAll
  static void init() {
    mockReportSpecification = new EqualsSpecification("field", 2);
  }

  @Test
  public void ItIsSatisfiedBy() {
    FilterReport mockFilter = FilterReport.builder().field("field").value(2).filterOperator(FilterOperator.EQUAL)
        .build();
    assertTrue(mockReportSpecification.isSatisfiedBy(mockFilter), "Is SatifiedBy condition");
  }

  @Test
  public void ItIsNotSatisfiedBy() {
    FilterReport mockFilter = FilterReport.builder().field("field").value(1).filterOperator(FilterOperator.GREATER)
        .build();
    assertFalse(mockReportSpecification.isSatisfiedBy(mockFilter), "Is not SatifiedBy condition");
  }

}
