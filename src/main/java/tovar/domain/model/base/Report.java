package tovar.domain.model.base;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tovar.domain.model.report.FrecuencyType;
import tovar.domain.model.report.ReportFormat;
import tovar.domain.model.report.ReportState;
import tovar.domain.model.report.ReportConfiguration;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class Report extends AuditableEntity<UUID> {
  private String name;
  private ReportFormat reportFormat;
  private FrecuencyType frecuencyType;
  private User user;
  private Tenant tenant;
  private ReportState reportState;
  private ReportConfiguration ReportConfiguration;
}
