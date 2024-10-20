package tovar.infrastructure.persistent.entities;

import java.util.UUID;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
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
import tovar.domain.model.base.Tenant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class UserEntity extends PanacheEntityBase {

  @Id
  @GeneratedValue
  private UUID id;
  private String name;
  private String email;
  @ManyToOne
  @JoinColumn(name = "tenant_id", referencedColumnName = "id")
  private TenantEntity tenant;

  public Tenant getTenant() {
    return Tenant.builder().id(tenant.getId()).name(tenant.getName()).build();
  }
}
