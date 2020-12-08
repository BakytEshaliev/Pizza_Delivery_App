package whz.pti.pizza.demo.boundary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import whz.pti.pizza.demo.domain.repositories.UserRepository;
import whz.pti.pizza.demo.security.domain.Customer;
import whz.pti.pizza.demo.security.domain.User;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
class MainControllerTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    MockHttpSession mocksession;
    @Autowired
    UserRepository userRepo;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();
//        mocksession = new MockHttpSession();
//        mocksession.setAttribute("user",userRepo.findByLoginName("bnutz"));
    }

    @Test
    void testMainPageView() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("login")).andDo(print());

    }

//    @Test
//    void testLogin() throws Exception {
//        mockMvc.perform(get("/home")
//                    .session(mocksession))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
}