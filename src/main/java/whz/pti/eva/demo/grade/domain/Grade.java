package whz.pti.eva.demo.grade.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Grade {
    @Id @GeneratedValue
    private Integer id;
    private String lecture;
    private String grade;
}
