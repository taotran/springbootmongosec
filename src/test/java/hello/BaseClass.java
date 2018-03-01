package hello;

import com.pycogroup.taotran.App;
import com.pycogroup.taotran.entity.User;
import com.pycogroup.taotran.rest.UserResource;
import com.pycogroup.taotran.service.user.UserService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

//import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

@RunWith(SpringRunner.class)
@SpringBootTest(
//        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = App.class
)
@AutoConfigureMockMvc
@SuppressWarnings("all")
//@Ignore
public class BaseClass {

        @Autowired
    private UserResource userResource;
//
    @Autowired
    UserService userService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private MockMvc mockMvc;

//    @Before
//    public void setUp() throws Exception{
//        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
//        RestAssuredMockMvc.enableLoggingOfRequestAndResponseIfValidationFails();

//        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
//        RestAssured

//        given().auth().with(user("admin").password("admin123"));

//        RestAssuredMockMvc.given().auth().with(user("admin").password("admin123"));
//        given().auth().principal(new Principal() {
//            @Override
//            public String getName() {
//                return null;
//            }
//        })
//        RestAssuredMockMvc.authentication = principal(new UsernamePasswordCredentials("admin", "admin123"));

//        RestAssuredMockMvc.principalWithCredentials("admin", new UsernamePasswordCredentials("admin", "admin123"));
//        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();


//        MockMvcAuthenticationScheme mockMvcAuthenticationScheme = new MockMvcAuthenticationScheme() {
//            @Override
//            public void authenticate(MockMvcRequestSpecification mockMvcRequestSpecification) {
//                mockMvcRequestSpecification.auth().with(user("admin").password("admin123"));
//            }
//        };


//        authScheme.setUserName("admin");
//        authScheme.setPassword("admin123");


//        RestAssuredMockMvc.authentication(new UsernamePasswordAuthenticationToken("admin", "admin123"));

//        given().auth().basic("admin", "admin123");

//        RestAssured.authentication = basic("admin", "admin123");
//        Mockito.when(userService.findAll()).thenReturn(mockUsers());
//        Mockito.when(mockMvc.perform(get("")).)

//    }

    @Before
    public void setUp() {
//        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

//        given().auth().basic("admin", "admin123").when().get("/api/v1/users");

        RestAssuredMockMvc.
                standaloneSetup(userResource);
//        RestAssuredMockMvc.when().get("").
//                auth().principal(new User("admin", "admin123", new SimpleGrantedAuthority("ROLE_ADMIN"))).
//                when().get("/api/v1/users").then().statusCode(200);

        Mockito.when(userService.findAll()).thenReturn(mockUsers());

    }

    @Test
    public void getUsers() {
        RestAssuredMockMvc.authentication(new UsernamePasswordAuthenticationToken("admin", "admin123"));
        given().auth().basic("admin", "admin123").when().get("/api/v1/users").then().statusCode(200);
//        given().get("/v1/api/users").then().statusCode(200);
    }

    List<User> mockUsers() {
        return Arrays.asList(
                new User("testUser1", "abcdef"),
                new User("testUser2", "abcdefg")
        );
    }
}

