package com.pycogroup.taotran.parse.deserializer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.pycogroup.taotran.entity.Task;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.core.ResolvableType;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class TaskJsonDeserializer implements Deserializer<Task> {

    protected final ObjectMapper objectMapper;

    protected final Class<Task> targetType;

    private volatile ObjectReader reader;

    public TaskJsonDeserializer() {

        targetType = (Class<Task>) ResolvableType.forClass(getClass()).getSuperType().resolveGeneric(0);
        objectMapper = new ObjectMapper();

    }

    public TaskJsonDeserializer(ObjectMapper objectMapper) {
        this(null, objectMapper);
    }

    public TaskJsonDeserializer(Class<Task> targetType) {
        this(targetType, new ObjectMapper());
        this.objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @SuppressWarnings("unchecked")
    public TaskJsonDeserializer(Class<Task> targetType, ObjectMapper objectMapper) {
        Assert.notNull(objectMapper, "'objectMapper' must not be null.");
        this.objectMapper = objectMapper;
        if (targetType == null) {
            targetType = (Class<Task>) ResolvableType.forClass(getClass()).getSuperType().resolveGeneric(0);
        }
        Assert.notNull(targetType, "'targetType' cannot be resolved.");
        this.targetType = targetType;
    }

    public void configure(Map<String, ?> configs, boolean isKey) {
        // No-op
    }

    public Task deserialize(String topic, byte[] data) {
        if (this.reader == null) {
            this.reader = this.objectMapper.readerFor(Task.class);
        }
        try {
            Task result = null;
            if (data != null) {
                result = this.reader.readValue(data);
            }
            return result;
        } catch (IOException e) {
            throw new SerializationException("Can't deserialize data [" + Arrays.toString(data) +
                    "] from topic [" + topic + "]", e);
        }
    }

    public void close() {
        // No-op
    }
}