package tovar.domain.repository;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletionStage;

import tovar.domain.model.report.Report;

public interface ReportRepository extends IGenericRepository<Report, UUID> {
  CompletionStage<List<Report>> findByTenant(Long tenantId);
}
