package tovar.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Download {
  private UUID id;
  private Report report;
  private DownloadState state;
  private LocalDateTime generateDate;
  private LocalDateTime expirationDate;
  private String urlS3;
}
