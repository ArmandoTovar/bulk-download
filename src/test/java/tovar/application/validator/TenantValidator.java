package tovar.application.validator;

import java.util.ArrayList;
import java.util.List;

import tovar.domain.model.base.Tenant;
import tovar.domain.model.validator.Validator;

public class TenantValidator implements Validator<Tenant> {

  @Override
  public List<String> validate(Tenant entity) {
    List<String> errors = new ArrayList<>();
    if (entity.getName() == null || entity.getName().trim().isBlank())
      errors.add("Tenant name cannot be empty.");
    if (entity.getName().length() < 3)
      errors.add("Tenant name cannot have less than 3 character");
    return errors;
  }
}
