package tovar.application.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tovar.domain.model.base.User;
import tovar.domain.model.validator.Validator;

public class UserValidator implements Validator<User> {
  @Override
  public List<String> validate(User entity) {
    List<String> errors = new ArrayList<>();
    if (entity.getName() == null || entity.getName().trim().isBlank())
      errors.add("User name cannot be empty.");
    if (entity.getName().length() < 3)
      errors.add("User name cannot have less than 3 character");
    if (entity.getEmail() != null && !emailIsValid(entity.getEmail()))
      errors.add("Email addres is not valid");
    return errors;
  }

  private boolean emailIsValid(String email) {
    Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
        Pattern.CASE_INSENSITIVE);
    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
    return matcher.matches();
  }
}
