package tovar.infrastructure.persistent.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tovar.domain.model.report.FrecuencyType;
import tovar.domain.model.report.ReportConfiguration;
import tovar.domain.model.report.ReportFormat;
import tovar.domain.model.report.ReportState;
import tovar.infrastructure.persistent.converters.ReportConfigurationConverter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "report")
public class ReportEntity extends AuditableEntity {

  @Id
  @GeneratedValue
  private UUID id;

  @Column(length = 50)
  private String name;

  @Enumerated(EnumType.STRING)
  private ReportFormat reportFormat;

  @Enumerated(EnumType.STRING)
  private FrecuencyType frecuencyType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private UserEntity user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tenant_id", referencedColumnName = "id")
  private TenantEntity tenant;

  @Enumerated(EnumType.ORDINAL)
  private ReportState reportState;

  @Convert(converter = ReportConfigurationConverter.class)
  @Column(columnDefinition = "text")
  private ReportConfiguration reportConfiguration;

  private LocalDateTime lastDownload;
}
