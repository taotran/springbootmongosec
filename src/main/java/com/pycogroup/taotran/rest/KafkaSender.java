package com.pycogroup.taotran.rest;

import com.pycogroup.taotran.springbootmongosec.avroentity.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class KafkaSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaSender.class);


    @Value("${kafka.topic.avro}")
    private String avroTopic;

    @Autowired
    private KafkaTemplate<String, Task> taskKafkaTemplate;

    public void send(Task task) {
        LOGGER.info("sending task='{}' ", task.toString());
        ListenableFuture<SendResult<String, Task>> listenableFuture = taskKafkaTemplate.send(avroTopic, task);

        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, Task>>() {
            @Override
            public void onFailure(Throwable ex) {
                LOGGER.info("fail to send data");
            }

            @Override
            public void onSuccess(SendResult<String, Task> result) {
                LOGGER.info("send '{}' successfully", result.getProducerRecord().value());
            }
        });
    }
}
