package com.pycogroup.taotran.rest;

import com.pycogroup.taotran.springbootmongosec.avroentity.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaSender.class);


    @Value("${kafka.topic.avro}")
    private String avroTopic;

    @Autowired
    private KafkaTemplate<String, Task> taskKafkaTemplate;

    public void send(Task task) {
        LOGGER.info("sending task='{}' ", task.toString());
        taskKafkaTemplate.send(avroTopic, task);
    }
}
