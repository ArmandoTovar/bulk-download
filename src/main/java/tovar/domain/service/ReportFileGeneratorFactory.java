package tovar.domain.service;

import tovar.domain.model.report.ReportFileGenerator;
import tovar.domain.model.report.ReportFormat;

public class ReportFileGeneratorFactory {
  public static ReportFileGenerator getFileGenerator(ReportFormat format) {
    switch (format) {
      case CSV:
        return new CSVReportFileGenerator();
      case PDF:
        return new PDFReportFileGenerator();
      case XLSX:
        return new XLSXReportFileGenerator();
      default:
        throw new IllegalArgumentException(String.format("Report format doesn't supported: %s", format.toString()));
    }
  }
}
