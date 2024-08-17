package com.ust.Surveys.model;
//import javax.persistence.AttributeConverter;
//import javax.persistence.Converter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.List;

@Converter(autoApply = true)
public class ListToJsonConverter implements AttributeConverter<List<Integer>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Integer> list) {
        try {
            return list == null ? null : objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert list to JSON", e);
        }
    }

    @Override
    public List<Integer> convertToEntityAttribute(String json) {
        try {
            return json == null ? null : objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, Integer.class));
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert JSON to list", e);
        }
    }
}
