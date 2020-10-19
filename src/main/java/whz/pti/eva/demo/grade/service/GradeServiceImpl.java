package whz.pti.eva.demo.grade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.pti.eva.demo.grade.domain.GradeRepository;
import whz.pti.eva.demo.grade.domain.Grade;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService{
    @Autowired
    GradeRepository gradeRepository;

    @Override
    public List<Grade> listAllGrades() {
        return (List<Grade>) gradeRepository.findAll();
    }

    @Override
    public void addGrade(String lecture, String grade) {
        Grade gradeObj = new Grade();
        gradeObj.setLecture(lecture);
        gradeObj.setGrade(grade);
        gradeRepository.save(gradeObj);
    }

    @Override
    public double calculateAverage() {
        double sum = 0;
        int amount = listAllGrades().size();
        for (Grade grade : listAllGrades()) {
            sum += Double.parseDouble(grade.getGrade());
        }
        return sum / amount;
    }
}
