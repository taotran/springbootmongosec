package com.pycogroup.taotran.parse.serializer;

import com.pycogroup.taotran.entity.Task;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class TaskJsonSerializer extends JsonSerializer<Task> {
}
