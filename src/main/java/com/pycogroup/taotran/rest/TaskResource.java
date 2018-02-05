package com.pycogroup.taotran.rest;


import com.pycogroup.taotran.constant.MappingPath;
import com.pycogroup.taotran.entity.Task;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MappingPath.TASK)
public class TaskResource extends BaseResource<Task> {


}
