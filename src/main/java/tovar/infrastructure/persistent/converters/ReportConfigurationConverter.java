package tovar.infrastructure.persistent.converters;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tovar.domain.model.report.ReportConfiguration;

@Converter(autoApply = true)
public class ReportConfigurationConverter implements AttributeConverter<ReportConfiguration, String> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public String convertToDatabaseColumn(ReportConfiguration reportConfiguration) {
    try {
      if (reportConfiguration == null) {
        return null;
      }
      return objectMapper.writeValueAsString(reportConfiguration);
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException("Error converting ReportConfiguration to JSON", e);
    }
  }

  @Override
  public ReportConfiguration convertToEntityAttribute(String dbData) {
    try {
      if (dbData == null) {
        return null;
      }
      return objectMapper.readValue(dbData, ReportConfiguration.class);
    } catch (IOException e) {
      throw new IllegalArgumentException("Error converting JSON to ReportConfiguration", e);
    }
  }
}
