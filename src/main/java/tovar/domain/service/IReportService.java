package tovar.domain.service;

import java.util.UUID;

import tovar.domain.model.report.Report;

public interface IReportService extends IGenericCrudService<Report, UUID> {
  Report generateReport();
}
