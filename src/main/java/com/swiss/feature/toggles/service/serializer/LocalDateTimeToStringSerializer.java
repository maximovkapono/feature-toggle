package com.swiss.feature.toggles.service.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;

import static com.swiss.feature.toggles.model.DateConstants.DATE_TIME_PATTERN;

public class LocalDateTimeToStringSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeObject(DATE_TIME_PATTERN.format(date));
    }
}
