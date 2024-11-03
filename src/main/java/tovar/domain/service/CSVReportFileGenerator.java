package tovar.domain.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import tovar.domain.model.report.Report;
import tovar.domain.model.report.ReportFileGenerator;

public class CSVReportFileGenerator implements ReportFileGenerator {

  @Override
  public File generateFile(Report report, List<Map<String, Object>> data) {
    System.out.println("Current working directory: " + System.getProperty("user.dir"));
    var file = new File(report.getFileNameGenerated());

    try (FileWriter writer = new FileWriter(file)) {
      if (!data.isEmpty()) {
        Map<String, Object> firstRow = data.get(0);
        String headers = String.join(",", firstRow.keySet());
        writer.write(headers + "\n");

        for (Map<String, Object> row : data) {
          String rowData = String.join(",",
              row.values().stream()
                  .map(value -> value != null ? value.toString() : "")
                  .toArray(String[]::new));
          writer.write(rowData + "\n");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return file;
  }

}
