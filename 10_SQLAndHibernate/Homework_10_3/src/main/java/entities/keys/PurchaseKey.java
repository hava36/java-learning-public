package entities.keys;

import entities.Course;
import entities.Student;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@Embeddable
public class PurchaseKey implements Serializable {

    @Column(name = "student_name", updatable = false, insertable = false)
    private String studentName;

    @Column(name = "course_name", updatable = false, insertable = false)
    private String courseName;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @Column(name = "price")
    private Integer price;

    public PurchaseKey(String studentName, String courseName) {
        this.studentName = studentName;
        this.courseName = courseName;
    }

}
