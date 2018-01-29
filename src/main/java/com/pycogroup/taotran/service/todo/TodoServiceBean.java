package com.pycogroup.taotran.service.todo;

import com.pycogroup.taotran.entity.Todo;
import com.pycogroup.taotran.service.DocumentServiceBean;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceBean extends DocumentServiceBean<Todo> implements TodoService {
}
