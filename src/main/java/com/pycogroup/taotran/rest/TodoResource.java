package com.pycogroup.taotran.rest;


import com.pycogroup.taotran.entity.Todo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoResource extends BaseResource<Todo> {


}