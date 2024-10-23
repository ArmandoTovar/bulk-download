package tovar.domain.repository;

import java.util.List;
import java.util.UUID;

import io.smallrye.mutiny.Uni;
import tovar.domain.model.report.Report;

public interface ReportRepository extends IGenericRepository<Report, UUID> {
  Uni<List<Report>> findByTenant(Long tenantId);
}
