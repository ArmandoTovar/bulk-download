package tovar.infrastructure.adapter.services;

import java.util.UUID;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import tovar.application.service.ReportCrudService;
import tovar.domain.model.base.Tenant;
import tovar.domain.model.base.User;
import tovar.domain.model.report.Report;
import tovar.domain.model.report.ReportSourceRepository;
import tovar.domain.repository.IGenericRepository;
import tovar.domain.repository.SqlReportSourceRepository;
import tovar.infrastructure.persistent.entities.ReportEntity;
import tovar.infrastructure.persistent.repositories.BaseRepositoryImpl;
import tovar.infrastructure.persistent.repositories.ReportRepositoryImpl;
import tovar.infrastructure.persistent.repositories.TenantRepositoryImpl;
import tovar.infrastructure.persistent.repositories.UserRepositoryImpl;

@ApplicationScoped
@WithSession
public class ReportServiceImpl extends ReportCrudService {
  @Inject
  private SqlReportSourceRepository reportSourceRepository;
  @Inject
  private ReportRepositoryImpl reportRepository;

  @Inject
  private TenantRepositoryImpl tenantRepository;

  @Inject
  private UserRepositoryImpl userRepository;

  @Override
  protected BaseRepositoryImpl<Report, ReportEntity, UUID> getRepository() {
    return reportRepository;
  }

  @Override
  protected IGenericRepository<Tenant, Long> getTenantRepository() {
    return tenantRepository;
  }

  @Override
  protected IGenericRepository<User, UUID> getUserRepository() {
    return userRepository;
  }

  @Override
  protected ReportSourceRepository getReportSourceRepository() {
    return reportSourceRepository;
  }

}
