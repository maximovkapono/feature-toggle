package com.swiss.feature.toggles.service.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;

import static com.swiss.feature.toggles.model.DateConstants.DATE_TIME_PATTERN;

public class StringToLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String dateString = new ObjectMapper().readValue(jsonParser, String.class);
        return LocalDateTime.parse(dateString, DATE_TIME_PATTERN);
    }
}
