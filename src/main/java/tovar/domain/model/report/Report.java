package tovar.domain.model.report;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tovar.domain.model.base.AuditableEntity;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Report extends AuditableEntity<UUID> {
  private String name;
  private ReportFormat reportFormat;
  private FrecuencyType frecuencyType;
  @JsonAlias(value = { "user_id", "userId" })
  private UUID userId;
  @JsonAlias(value = { "tenant_id", "tenantId" })
  private Long tenantId;
  private ReportState reportState;
  private ReportConfiguration reportConfiguration;
  private LocalDateTime lastDownload;

}
