package tovar.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Report {
  private UUID id;
  private String name;
  private ReportFormat reportFormat;
  private FrecuencyType frecuencyType;
  private User user;
  private Tenant tenant;
  private ReportState reportState;
  private ReportConfiguration ReportConfiguration;
  private LocalDateTime createDate;
  private LocalDateTime updateDate;
}
