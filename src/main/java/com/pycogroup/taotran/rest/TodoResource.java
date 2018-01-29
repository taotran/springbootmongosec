package com.pycogroup.taotran.rest;


import com.pycogroup.taotran.entity.Todo;
import com.pycogroup.taotran.entity.User;
import com.pycogroup.taotran.service.todo.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoResource {

    private final TodoService todoService;

    @Autowired
    public TodoResource(TodoService todoService) {
        Assert.notNull(todoService, "'todoService' must not be null!");

        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> findAll() {
        return todoService.findAll();
    }

    @GetMapping(path = "/{todoId}")
    public HttpEntity<Todo> findTodo(@PathVariable String todoId) {
        final Todo todo = todoService.findOne(todoId);
        return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addTodo(@RequestBody Todo todo) {
        todoService.save(todo);
    }

    @PostMapping(path = "/testSave")
    public void saveTodo(@RequestBody Todo todo) {
        User user = new User();
        user.setId("5a6ecca0cdd5b509649d6f3e");

        todo.setUser(user);

        todoService.save(todo);
    }


}
