package tovar.domain.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import tovar.domain.model.report.Report;
import tovar.domain.model.report.ReportFileGenerator;

public class CSVReportFileGenerator implements ReportFileGenerator {

  @Override
  public File generateFile(Report report, List<Map<String, Object>> data) {
    return new File("report.csv");
  }

}
