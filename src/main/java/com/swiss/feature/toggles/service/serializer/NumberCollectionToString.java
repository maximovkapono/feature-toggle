package com.swiss.feature.toggles.service.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import liquibase.util.StringUtils;

import java.io.IOException;
import java.util.Collection;

public class NumberCollectionToString extends JsonSerializer<Collection<Long>> {

    @Override
    public void serialize(Collection<Long> customers, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeObject(StringUtils.join(customers, ",", Object::toString));
    }
}
