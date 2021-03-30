package entities.keys;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class LinkedPurchaseKey implements Serializable {

    @Column(name = "student_id", updatable = false, insertable = false)
    private Integer studentId;

    @Column(name = "course_id", updatable = false, insertable = false)
    private Integer courseId;

    public LinkedPurchaseKey(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

}
