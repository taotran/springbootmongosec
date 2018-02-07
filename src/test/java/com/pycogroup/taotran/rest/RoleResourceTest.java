package com.pycogroup.taotran.rest;

import com.pycogroup.taotran.constant.MappingPath;
import com.pycogroup.taotran.entity.Role;
import com.pycogroup.taotran.service.auth.RoleService;
import org.junit.Ignore;
import org.springframework.boot.test.mock.mockito.MockBean;

@Ignore
public class RoleResourceTest extends BaseResourceTest<Role> {

    public RoleResourceTest() {
        super(Role.class);
    }

    @MockBean
    private RoleService roleService;

    @Override
    protected String getBaseMappingPath() {
        return MappingPath.ROLE;
    }

    @Override
    protected RoleService mockService() {
        return roleService;
    }

    @Override
    protected Role mockObject() {
        final Role role = new Role();
        role.setId(ID);
        role.setRole("ROLE_TEST");
        return role;
    }
}
