package tovar.domain.service;

import java.io.File;
import java.util.Optional;
import java.util.UUID;
import tovar.domain.model.report.Report;

public interface IReportService extends IGenericCrudService<Report, UUID> {
  File generateReport(UUID id);

  Optional<Report> getReportByIdNonReactive(UUID id);
}
