package tovar.infrastructure.adapter.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import tovar.application.validator.ValidatorFactory;
import tovar.domain.model.validator.ValidationException;
import tovar.domain.model.validator.Validator;

@ValidateModel
@Interceptor
public class ValidationInterceptor {
  @AroundInvoke
  public Object validateMethod(InvocationContext context) throws Exception {
    List<String> allErrors = new ArrayList<>();
    String transactionId = UUID.randomUUID().toString();
    for (Object param : context.getParameters()) {
      if (param != null) {
        Validator<Object> validator = (Validator<Object>) ValidatorFactory.getValidator(param.getClass());
        if (validator != null) {
          List<String> errors = validator.validate(param);
          if (!errors.isEmpty())
            allErrors.addAll(errors);
        }
      }
    }
    if (!allErrors.isEmpty()) {
      throw new ValidationException(allErrors, transactionId);
    }
    return context.proceed();
  }
}
