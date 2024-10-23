package tovar.domain.model.report;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import tovar.domain.model.base.AuditableEntity;
import tovar.domain.model.base.Tenant;
import tovar.domain.model.base.User;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Builder
public class Report extends AuditableEntity<UUID> {
  private String name;
  private ReportFormat reportFormat;
  private FrecuencyType frecuencyType;
  private User user;
  private Tenant tenant;
  private ReportState reportState;
  private ReportConfiguration ReportConfiguration;
  private LocalDateTime lastDownload;

  public String generatedfileName() {
    return String.format("%s-%s-%s-%s.%s", tenant.getName(), user.getName(), frecuencyType.toString(),
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyymmdd-hhmmss")), reportFormat).replaceAll(" ", "-");
  }
}
