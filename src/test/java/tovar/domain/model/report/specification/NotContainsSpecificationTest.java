package tovar.domain.model.report.specification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import tovar.domain.model.report.FilterOperator;
import tovar.domain.model.report.FilterReport;

@AllArgsConstructor
public class NotContainsSpecificationTest {
  static ReportSpecification<FilterReport> mockReportSpecification;

  @BeforeAll
  static void init() {
    mockReportSpecification = new NotContainsSpecification("field", "va");
  }

  @Test
  public void ItIsSatisfiedBy() {
    FilterReport mockFilter = FilterReport.builder().field("field").value("no")
        .filterOperator(FilterOperator.NOT_CONTAINS)
        .build();
    assertTrue(mockReportSpecification.isSatisfiedBy(mockFilter), "Is SatifiedBy condition");
  }

  @Test
  public void ItIsNotSatisfiedBy() {
    FilterReport mockFilter = FilterReport.builder().field("field").value("value")
        .filterOperator(FilterOperator.NOT_CONTAINS)
        .build();
    assertFalse(mockReportSpecification.isSatisfiedBy(mockFilter), "Is not SatifiedBy condition");
  }

  @Test
  public void ItToSql() {
    assertEquals("field NOT LIKE ?", mockReportSpecification.toSql());
  }

  @Test
  public void ItValidGetValues() {
    assertEquals(List.of("%va%"), mockReportSpecification.getValues());
  }

}
