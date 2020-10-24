package whz.pti.eva.demo.grade.boundary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import whz.pti.eva.demo.grade.domain.Grade;
import whz.pti.eva.demo.grade.service.GradeServiceImpl;

import java.util.List;

@Controller
@Slf4j
class GradeController {

    @Autowired
    GradeServiceImpl gradeService;

    @GetMapping
    public String home(){
        return "index";
    }

    /**
     * bins "add" post request for "http://localhost:8080/add"
     * with 2 parameters (lecture,grade)
     * for example
     * http://localhost:8080/add?lecture=Math&grade=1.5
     * and then it redirects to "grades" view
     * */
    @PostMapping(value = "add")
    public String addGrade(@RequestParam String lecture,
                           @RequestParam String grade,
                           Model model) {
        gradeService.addGrade(lecture, grade);
        return "redirect:grades";
    }

    /**
     * bins "grades" get request for url "http://localhost:8080/grades"
     * it gets all grades from DB and gives to view with average grade
     * @return grades.html file
     * */
    @GetMapping("grades")
    public String getGrades(Model model) {
        List<Grade> grades = gradeService.listAllGrades();
        model.addAttribute("grades",grades);
        double avg = gradeService.calculateAverage();
        //for debugging
        log.info("Average grade is " + avg);
        model.addAttribute("avg_grade", avg);
        return "grades";
    }




}
