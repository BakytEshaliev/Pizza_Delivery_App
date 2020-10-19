package whz.pti.eva.demo.grade.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import whz.pti.eva.demo.grade.domain.Grade;
import whz.pti.eva.demo.grade.service.GradeServiceImpl;

import java.util.List;

@RestController("/")
class GradeController {

    @Autowired
    GradeServiceImpl gradeService;


    @GetMapping
    public String home() {
        return "Hello this is home page";
    }

    @PostMapping(value = "add")
    public void addGrade(@RequestParam String lecture,
                         @RequestParam String grade) {
        gradeService.addGrade(lecture, grade);
    }

    @GetMapping("get_avg")
    public Double getAvgGrade() {
        return gradeService.calculateAverage();
    }

    @GetMapping("grades")
    public List<Grade> getGrades() {
        return gradeService.listAllGrades();
    }


}
