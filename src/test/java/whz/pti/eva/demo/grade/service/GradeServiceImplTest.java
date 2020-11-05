package whz.pti.eva.demo.grade.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import whz.pti.eva.demo.grade.domain.Grade;

import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
class GradeServiceImplTest {
    @MockBean
    private GradeServiceImpl gradeService;
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
    void testAverageGrade() throws Exception {
        when(gradeService.calculateAverage()).thenReturn(1.5);

        mockMvc.perform(get("/grades"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("avg_grade",1.5))
                .andExpect(view().name("grades"))
                .andDo(print());
    }


    /**
     * check grades and their size
     * */
    @Test
    void testListAllGrades() throws Exception {
        Grade grade1 = new Grade("Math", "1.5");
        Grade grade2 = new Grade("Eva", "2.3");

        List<Grade> grades = List.of(grade1, grade2);

        when(gradeService.listAllGrades()).thenReturn(grades);

        mockMvc.perform(get("/grades"))
                .andExpect(model().attribute("grades",hasSize(grades.size())))
                .andExpect(model().attribute("grades",grades))
                .andDo(print());
    }
}