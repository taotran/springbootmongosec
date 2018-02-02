package com.pycogroup.taotran;

import com.pycogroup.taotran.config.SpringSecurityWebTestConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SpringSecurityWebTestConfig.class
)
@AutoConfigureMockMvc
@SuppressWarnings("all")
public class BaseAppTest {
}
