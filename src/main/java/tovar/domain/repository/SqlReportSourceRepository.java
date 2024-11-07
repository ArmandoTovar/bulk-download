package tovar.domain.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tovar.domain.model.report.FilterReport;
import tovar.domain.model.report.Report;
import tovar.domain.model.report.ReportConfiguration;
import tovar.domain.model.report.ReportSourceRepository;
import tovar.domain.model.report.specification.ReportSpecification;

public abstract class SqlReportSourceRepository implements ReportSourceRepository {
  @Override
  public List<Map<String, Object>> getData(Report report) {
    ReportConfiguration reportConfiguration = report.getReportConfiguration();
    StringBuilder query = new StringBuilder("SELECT ");

    if (reportConfiguration.getSelectedColumn() != null && !reportConfiguration.getSelectedColumn().isEmpty()) {
      query.append(String.join(", ", reportConfiguration.getSelectedColumn()));
    } else {
      query.append("*");
    }

    query.append(" FROM ").append(reportConfiguration.getTableName());

    List<Object> parameters = new ArrayList<>();

    if (reportConfiguration.getFilters() != null && !reportConfiguration.getFilters().isEmpty()) {
      List<String> conditions = new ArrayList<>();
      for (FilterReport filter : reportConfiguration.getFilters()) {
        ReportSpecification<FilterReport> specification = filter.toSpecification();
        conditions.add(specification.toSql());
        parameters.addAll(specification.getValues());
      }

      query.append(" WHERE ").append(String.join(" AND ", conditions));
    }

    System.out.println(query.toString());
    return execute(query.toString(), parameters);
  }

  public abstract List<Map<String, Object>> execute(String query, List<Object> parameters);
}
