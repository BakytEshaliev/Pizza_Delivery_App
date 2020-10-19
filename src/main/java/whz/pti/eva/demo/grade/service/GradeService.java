package whz.pti.eva.demo.grade.service;

import whz.pti.eva.demo.grade.domain.Grade;

import java.util.List;

public interface GradeService {
    List<Grade> listAllGrades();
    void addGrade(String lecture, String grade);
    double calculateAverage();
}

