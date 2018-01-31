package com.pycogroup.taotran.service.todo;

import com.pycogroup.taotran.entity.Todo;
import com.pycogroup.taotran.service.DocumentServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class TodoServiceBean extends DocumentServiceBean<Todo> implements TodoService {

    @Autowired
    private JdbcMutableAclService aclService;

    private final JdbcOperations jdbcTemplate;

    @Autowired
    public TodoServiceBean(JdbcOperations jdbcTemplate) {

        Assert.notNull(jdbcTemplate, "'jdbcTemplate' must not be null!");

        this.jdbcTemplate = jdbcTemplate;
    }

}
