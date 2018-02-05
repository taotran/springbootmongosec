package com.pycogroup.taotran.entity.projection;

import com.pycogroup.taotran.entity.User;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "simple", types = User.class)
public interface UserSimpleProjection {

    String getId();

    String getUsername();

}
