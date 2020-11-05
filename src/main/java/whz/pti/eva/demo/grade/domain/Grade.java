package whz.pti.eva.demo.grade.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@ToString(exclude = {"id"})
public class Grade {
    @Id @GeneratedValue
    private Integer id;
    private String lecture;
    private String grade;

    public Grade(String lecture, String grade) {
        this.lecture = lecture;
        this.grade = grade;
    }
}
