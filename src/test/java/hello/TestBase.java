package hello;

import com.pycogroup.taotran.App;
import com.pycogroup.taotran.entity.User;
import com.pycogroup.taotran.rest.UserResource;
import com.pycogroup.taotran.service.user.UserService;
import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
public class TestBase {

    @MockBean
    private UserService userService;

    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssuredMockMvc.standaloneSetup(new UserResource(userService));

        Mockito.when(userService.findAll()).thenReturn(mockUsers());
    }

    List<User> mockUsers() {
        return Arrays.asList(
                new User("testUser1", "abcdef"),
                new User("testUser2", "abcdefg")
        );
    }
}
