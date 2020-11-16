package whz.pti.eva.demo.grade.boundary;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class GradeControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();
    }
    @Test
    void testAddGrade() throws Exception {
        mockMvc.perform(post("/add")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .param("lecture","Math")
                    .param("grade","1.5")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("grades"))
                .andDo(print());
    }

    @Test
    void testGetGrades() throws Exception {
        mockMvc.perform(get("/grades"))
                .andExpect(status().isOk())
                .andExpect(view().name("grades"));
    }
}