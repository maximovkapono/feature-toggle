package com.swiss.feature.toggles.service.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringToNumberCollection extends JsonDeserializer<Collection<Long>> {

    @Override
    public Collection<Long> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String dateString = new ObjectMapper().readValue(jsonParser, String.class);
        return Stream.of(dateString.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toSet());
    }
}
