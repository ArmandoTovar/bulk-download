package tovar.domain.repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.smallrye.mutiny.Uni;
import tovar.domain.model.report.FilterReport;
import tovar.domain.model.report.Report;
import tovar.domain.model.report.ReportConfiguration;
import tovar.domain.model.report.ReportSourceRepository;
import tovar.domain.model.report.specification.ReportSpecification;

public abstract class SqlReportSourceRepository implements ReportSourceRepository {
  @Override
  public Uni<List<Map<String, Object>>> getData(Report report) {
    return buildSqlQuery(report).flatMap(
        queryStr -> execute(queryStr));
  }

  private Uni<String> buildSqlQuery(Report report) {
    ReportConfiguration reportConfiguration = report.getReportConfiguration();
    StringBuilder query = new StringBuilder("SELECT ");
    if (reportConfiguration.getSelectedColumn() != null && !reportConfiguration.getSelectedColumn().isEmpty()) {
      query.append(String.join(", ", reportConfiguration.getSelectedColumn()));
    } else {
      query.append("*");
    }

    query.append(" FROM ").append(reportConfiguration.getTableName());

    if (reportConfiguration.getFilters() != null && !reportConfiguration.getFilters().isEmpty()) {
      List<String> conditions = reportConfiguration.getFilters().stream()
          .map(FilterReport::toSpecification)
          .map(ReportSpecification::toSql)
          .collect(Collectors.toList());

      query.append(" WHERE ").append(String.join(" AND ", conditions));
    }

    System.out.println(query.toString());
    return Uni.createFrom().item(query.toString());
  }

  public abstract Uni<List<Map<String, Object>>> execute(String query);
}
