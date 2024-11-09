package tovar.application.validator;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tovar.domain.model.report.FilterReport;
import tovar.domain.model.validator.HiddenColumn;

public class ReportConfigurationValidator {
  public static boolean validateSelectedColumns(Class<?> clazz, List<String> selectedColums) {
    Set<String> classFields = Stream.of(clazz.getDeclaredFields())
        .filter(field -> !field.isAnnotationPresent(HiddenColumn.class)).map(Field::getName)
        .collect(Collectors.toSet());
    return selectedColums.stream().allMatch(classFields::contains);
  }

  public static boolean validateFilters(Class<?> clazz, List<FilterReport> filters) {
    for (FilterReport filter : filters) {
      try {
        Field field = clazz.getDeclaredField(filter.getField());
        if (!isCompatibleType(field.getType(), filter.getValue()))
          return false;
      } catch (NoSuchFieldException e) {
        return false;
      }
    }
    return false;
  }

  private static boolean isCompatibleType(Class<?> fieldType, Object value) {
    if (value == null)
      return true;
    if (fieldType.isAssignableFrom(value.getClass()))
      return true;
    return false;
  }

}
