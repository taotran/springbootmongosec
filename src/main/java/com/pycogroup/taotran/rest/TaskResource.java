package com.pycogroup.taotran.rest;


import com.pycogroup.taotran.constant.MappingPath;
import com.pycogroup.taotran.entity.Task;
import com.pycogroup.taotran.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(MappingPath.TASK)
public class TaskResource extends BaseKafkaResource<Task, com.pycogroup.taotran.springbootmongosec.avroentity.Task> {

    private final TaskService taskService;

    @Autowired
    public TaskResource(TaskService taskService) {
//        Assert.notNull(sender, "'kafkaSender' must not be null!");
        Assert.notNull(taskService, "'taskService' must not be null!");


        this.taskService = taskService;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Task task) {
        final Task t = taskService.save(task);

        final com.pycogroup.taotran.springbootmongosec.avroentity.Task serializedTask =
                com.pycogroup.taotran.springbootmongosec.avroentity.Task.newBuilder()
                        .setId(t.getId())
                        .setTitle(t.getTitle())
                        .setDescription(t.getDescription())
                        .setDueDate(t.getDueDate().getTime())
                        .setCreatedDate(t.getCreatedDate().getTime())
                        .setUpdatedDate(t.getUpdatedDate().getTime())
                        .setPriority(t.getPriority().name().toUpperCase())
                        .build();
        sender.send(serializedTask);


    }
}
