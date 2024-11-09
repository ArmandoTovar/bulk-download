package tovar.infrastructure.persistent.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tovar.domain.model.download.DownloadStatus;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "download")
public class DownloadEntity extends AuditableEntity {

  @Id
  @GeneratedValue
  private UUID id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "report_id", referencedColumnName = "id")
  private ReportEntity report;
  @Enumerated(EnumType.STRING)
  private DownloadStatus status;
  @Column(columnDefinition = "text")
  private String query;
  @Column(length = 50)
  private String periodicity;
  private int retryCount;
}
