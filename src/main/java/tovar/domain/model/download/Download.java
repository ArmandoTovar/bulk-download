package tovar.domain.model.download;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tovar.domain.model.base.AuditableEntity;
import tovar.domain.model.base.Report;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class Download extends AuditableEntity<UUID> {
  private Report report;
  private DownloadState state;
  private String urlS3;
}