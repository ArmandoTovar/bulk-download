package tovar.application.service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import io.smallrye.mutiny.Uni;
import tovar.domain.model.base.Tenant;
import tovar.domain.model.base.User;
import tovar.domain.model.report.Report;
import tovar.domain.model.report.ReportSourceRepository;
import tovar.domain.model.report.ReportState;
import tovar.domain.model.validator.ValidationException;
import tovar.domain.repository.IGenericRepository;
import tovar.domain.service.IReportService;
import tovar.domain.service.ReportFileGeneratorFactory;

public abstract class ReportCrudService extends GenericCrudService<Report, UUID> implements IReportService {
  protected abstract ReportSourceRepository getReportSourceRepository();

  protected abstract IGenericRepository<Tenant, Long> getTenantRepository();

  protected abstract IGenericRepository<User, UUID> getUserRepository();

  @Override
  public Uni<Report> create(Report entity) {
    return getTenantRepository().getById(entity.getTenantId())
        .flatMap(optionalTenant -> {
          if (optionalTenant.isEmpty()) {
            return Uni.createFrom().failure(new ValidationException(
                List.of(String.format("Tenant ID %s doesn't exist", entity.getTenantId())),
                UUID.randomUUID().toString()));
          }
          return getUserRepository().getById(entity.getUserId())
              .flatMap(optionalUser -> {
                if (optionalUser.isEmpty()) {
                  return Uni.createFrom().failure(new ValidationException(
                      List.of(String.format("User ID %s doesn't exist", entity.getUserId())),
                      UUID.randomUUID().toString()));
                }
                entity.setReportState(ReportState.ACTIVE);
                return super.create(entity);
              });
        });
  }

  public Uni<File> generateReport(Report report) {
    return getReportSourceRepository().getData(report).map(data -> generateReport(report, data));
  }

  private File generateReport(Report report, List<Map<String, Object>> data) {
    return ReportFileGeneratorFactory.getFileGenerator(report.getReportFormat()).generateFile(report, data);
  }
}
