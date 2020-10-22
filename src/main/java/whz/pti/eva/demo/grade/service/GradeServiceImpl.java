package whz.pti.eva.demo.grade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.pti.eva.demo.grade.domain.Grade;
import whz.pti.eva.demo.grade.domain.GradeRepository;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService{
    @Autowired
    GradeRepository gradeRepository;

    /**
     * @return list of all grades
     * */
    @Override
    public List<Grade> listAllGrades() {
        return (List<Grade>) gradeRepository.findAll();
    }

    /**
     * adds grade object in H2 database
     * */
    @Override
    public void addGrade(String lecture, String grade) {
        Grade gradeObj = new Grade();
        gradeObj.setLecture(lecture);
        gradeObj.setGrade(grade);
        gradeRepository.save(gradeObj);
    }

    /**
     * function for calculating average grade
     * if there is nothing in list
     * returns 0
     * else sum of all grade divide by the number of lectures
     * @return average
     * */
    @Override
    public double calculateAverage() {
        double sum = 0;
        int amount = listAllGrades().size();
        //exit if there is 0 amount (exception division by 0)
        if (amount == 0){
            return 0;
        }
        for (Grade grade : listAllGrades()) {
            sum += Double.parseDouble(grade.getGrade());
        }
        return sum / amount;
    }
}
