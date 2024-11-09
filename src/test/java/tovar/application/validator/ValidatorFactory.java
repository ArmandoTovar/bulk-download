package tovar.application.validator;

import java.util.HashMap;
import java.util.Map;

import tovar.domain.model.base.Tenant;
import tovar.domain.model.base.User;
import tovar.domain.model.report.Report;
import tovar.domain.model.validator.Validator;

public class ValidatorFactory {
  private static final Map<Class<?>, Validator<?>> validators = new HashMap<>();
  static {
    validators.put(User.class, new UserValidator());
    validators.put(Tenant.class, new TenantValidator());
    validators.put(Report.class, new ReportValidator());
  }

  @SuppressWarnings("unchecked")
  public static <T> Validator<T> getValidator(Class<T> clazz) {
    return (Validator<T>) validators.get(clazz);
  }
}
