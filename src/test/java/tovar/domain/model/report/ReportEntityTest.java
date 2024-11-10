package tovar.domain.model.report;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ReportEntityTest {
  static Report mockReport;

  @BeforeAll
  public static void init() {
    mockReport = Report.builder().lastDownload(null).tenantId(1L).frecuencyType(FrecuencyType.DAILY).executed(1)
        .reportFormat(ReportFormat.CSV).build();
  }

  @Test
  public void ItFileNameGeneratedWithoutDate() {
    String reportName = mockReport.getFileNameGenerated();
    Assertions.assertTrue(reportName.startsWith("1_daily_"));
    Assertions.assertTrue(reportName.endsWith("(1).csv"));
  }

  @Test
  public void ItFileNameGeneratedWithDate() {
    LocalDateTime lTime = LocalDateTime.of(1999, 1, 1, 0, 0);
    mockReport.setLastDownload(lTime);
    String reportName = mockReport.getFileNameGenerated();
    Assertions.assertEquals("1_daily_19990101(1).csv", reportName);
  }
}
