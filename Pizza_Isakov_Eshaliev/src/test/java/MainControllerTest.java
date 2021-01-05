import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import whz.pti.pizza.demo.boundary.MainController;
import whz.pti.pizza.demo.domain.repositories.UserRepository;
import whz.pti.pizza.demo.security.domain.CurrentUser;
import whz.pti.pizza.demo.security.domain.Customer;
import whz.pti.pizza.demo.security.domain.User;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
@SpringBootTest(classes = {MainController.class})
class MainControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
//    @Autowired
//    MockHttpSession mocksession;
//    @Autowired
//    UserRepository userRepo;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();
    }

    @Test
    void testLoginPageView() throws Exception {
        mockMvc.perform(post("/login").param("loginName","bnutz").param("password","n1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("home")).andDo(print());

    }

    @Test
    @WithMockUser(username = "bnutz",password = "n1",roles = "USER")
    void testMainPage() throws Exception {
        mockMvc.perform(get("/home").with(user("bnutz").password("n1").roles("USER")))
                .andExpect(status().isOk())
                .andDo(print());
    }
}