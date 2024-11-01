package tovar.domain.model.download;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import tovar.domain.model.base.AuditableEntity;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@Builder
@ToString
public class Download extends AuditableEntity<UUID> {
  @JsonAlias(value = { "report_id", "reportId" })
  private UUID reportId;
  private DownloadStatus status;
  private String query;
  private String periodicity;
  @JsonAlias(value = { "retry_count", "retryCount" })
  private int retryCount;
}
