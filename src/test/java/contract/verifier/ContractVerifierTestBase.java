package contract.verifier;

import com.pycogroup.taotran.App;
import com.pycogroup.taotran.entity.User;
import com.pycogroup.taotran.rest.UserResource;
import com.pycogroup.taotran.service.user.UserService;
import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
public class ContractVerifierTestBase {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserResource userResource;

    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssuredMockMvc.standaloneSetup(userResource);

        Mockito.when(userService.findAll()).thenReturn(mockUsers());
    }

//    private List<User> mockUsers() {
//        return Arrays.asList(
//                new User.Builder("testUser1", "abcdef").build(),
//                new User.Builder("testUser2", "abcdefg").build()
//        );
//    }

    private List<User> mockUsers() {
        final User user1 = new User.Builder("testUser1", "abcdef").age(8).build();
        user1.setId("1");

        final User user2 = new User.Builder("testUser2", "abcdefg").age(9).build();
        user2.setId("2");

        return Arrays.asList(user1, user2);
    }
}
