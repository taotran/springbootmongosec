package com.pycogroup.taotran;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.pycogroup.taotran.rest.UserResource;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//import io.restassured.module.mockmvc.RestAssuredMockMvc;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
//@AutoConfigureMockMvc
//@WithUserDetails(value = "admin")
public class MvcTest {

//    @Autowired
//    private WebApplicationContext context;

//    @Autowired
//    private MockMvc mockMvc;

    @Before
    public void setUp() {
//        RestAssuredMockMvc.webAppContextSetup(context);
//        RestAssuredMockMvc.webAppContextSetup(this.context);
//        mockMvc = new MockMvcFactory(mockMvc).build()
        RestAssuredMockMvc.standaloneSetup(new UserResource());
    }

}


