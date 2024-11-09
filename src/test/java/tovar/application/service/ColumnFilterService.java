package tovar.application.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tovar.domain.model.validator.HiddenColumn;
import tovar.domain.model.validator.RestrictedTable;

public class ColumnFilterService {
  public Map<String, Object> getColumnFilters(String className) {
    Map<String, Object> columnFilter = new HashMap<>();
    Class<?> clazz = isValidOrRestricted(className);
    columnFilter.put("allowedColumns", getAllowedColumns(clazz));
    columnFilter.put("allowedFilters", getAllowedFilters(clazz));
    return columnFilter;
  }

  private Map<String, List<String>> getAllowedFilters(Class<?> clazz) {
    Map<String, List<String>> allowedFilters = new HashMap<>();
    for (Field field : clazz.getDeclaredFields()) {
      if (field.isAnnotationPresent(HiddenColumn.class))
        continue;
      allowedFilters.put(field.getName(), getSupportedOperators(field.getType()));

    }
    return allowedFilters;
  }

  private List<String> getAllowedColumns(Class<?> clazz) {
    List<String> allowedColumns = new ArrayList<>();
    for (Field field : clazz.getDeclaredFields()) {

      if (field.isAnnotationPresent(HiddenColumn.class))
        continue;
      allowedColumns.add(field.getName());
    }
    return allowedColumns;
  }

  private List<String> getSupportedOperators(Class<?> fieldType) {
    if (fieldType == String.class) {
      return Arrays.asList("EQUAL", "CONTAINS", "NOT_CONTAINS", "INCLUDE", "NOT_INCLUDE");
    } else if (Number.class.isAssignableFrom(fieldType) || fieldType.isPrimitive()) {
      return Arrays.asList("EQUAL", "GREATER", "LESS");
    } else {
      return Collections.singletonList("EQUAL");
    }
  }

  private Class<?> isValidOrRestricted(String className) {
    Class<?> clazz = null;
    try {
      clazz = Class.forName("tovar.domain.model.base." + className);
    } catch (ClassNotFoundException e) {
      throw new IllegalArgumentException("TableName not found: " + className);
    }
    if (clazz.isAnnotationPresent(RestrictedTable.class)) {
      throw new IllegalArgumentException("The table: " + className + " is restricted and cannot be used.");
    }
    return clazz;
  }

}
