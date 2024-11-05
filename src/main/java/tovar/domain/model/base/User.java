package tovar.domain.model.base;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tovar.domain.model.validator.HiddenColumn;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends AuditableEntity<UUID> {

  @HiddenColumn
  private String name;
  private String email;
  @JsonAlias(value = { "tenant_id", "tenantId" })
  private Long tenantId;
}
