package tovar.application.validator;

import java.util.ArrayList;
import java.util.List;

import tovar.domain.model.report.Report;
import tovar.domain.model.validator.Validator;

public class ReportValidator implements Validator<Report> {
  @Override
  public List<String> validate(Report entity) {
    List<String> errors = new ArrayList<>();
    if (entity.getName() == null || entity.getName().trim().isBlank())
      errors.add("Report name cannot be empty.");
    if (entity.getName().length() < 5)
      errors.add("Report name cannot have less than 5 character");
    if (entity.getTenantId() == null || entity.getTenantId() < 0)
      errors.add("Tenant id name cannot be empty.");
    if (entity.getUserId() == null)
      errors.add("User id name cannot be empty.");
    return errors;
  }

}
