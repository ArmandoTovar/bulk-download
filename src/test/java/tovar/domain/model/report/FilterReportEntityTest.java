package tovar.domain.model.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import tovar.domain.model.report.specification.GreaterThanSpecification;
import tovar.domain.model.report.specification.ReportSpecification;

@QuarkusTest
public class FilterReportEntityTest {

  static FilterReport mockFilterReport;

  @BeforeAll
  public static void init() {
    mockFilterReport = FilterReport.builder().field("field").value("value").filterOperator(FilterOperator.GREATER)
        .build();
  }

  @Test
  public void ItValidToReportSpecification() {
    assertTrue(mockFilterReport.toSpecification() instanceof ReportSpecification,
        "Expected ReportSpecification");
  }

  @Test
  public void ItToSpecification() {
    ReportSpecification<FilterReport> specification = mockFilterReport.toSpecification();
    assertTrue(specification instanceof GreaterThanSpecification, "Expected GreaterThanSpecification");
    assertEquals(List.of("value"), ((GreaterThanSpecification) specification).getValues());
  }

  @Test
  public void ItNotValidToSpecification() {
    FilterReport filterReport = new FilterReport();
    filterReport.setFilterOperator(FilterOperator.UNSUPPORTED);
    Exception exception = assertThrows(IllegalArgumentException.class, filterReport::toSpecification);
    assertEquals("Operator is not supported: " + filterReport.getFilterOperator(), exception.getMessage());
  }

}
