package hello;

import com.pycogroup.taotran.App;
import com.pycogroup.taotran.entity.User;
import com.pycogroup.taotran.service.user.UserService;
import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@ContextConfiguration
@WebAppConfiguration
//@ActiveProfiles("test")
public class TestBase {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mvc;

    @MockBean
    UserService userService;

    @Autowired
    FilterChainProxy springSecurityFilterChain;

    @Mock
    SecurityContext securityContext;

    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();


        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .defaultRequest(get("/api/v1/users").with(user("admin").roles("ADMIN")))
                .apply(springSecurity(springSecurityFilterChain))
                .build();

        RestAssuredMockMvc.mockMvc(mvc);

        Mockito.when(userService.findAll()).thenReturn(mockUsers());
    }

    List<User> mockUsers() {
        return Arrays.asList(
                new User("testUser1", "abcdef"),
                new User("testUser2", "abcdefg")
        );
    }
}
