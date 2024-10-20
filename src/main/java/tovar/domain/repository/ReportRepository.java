package tovar.domain.repository;

import java.util.List;
import java.util.UUID;

import tovar.domain.model.base.Report;

public interface ReportRepository extends IGenericRepository<Report, UUID> {
  List<Report> findByTenant(Long tenantId);
}
