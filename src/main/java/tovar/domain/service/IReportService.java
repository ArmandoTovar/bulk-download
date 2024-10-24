package tovar.domain.service;

import java.io.File;
import java.util.UUID;

import io.smallrye.mutiny.Uni;
import tovar.domain.model.report.Report;

public interface IReportService extends IGenericCrudService<Report, UUID> {
  Uni<File> generateReport(Report report);
}
