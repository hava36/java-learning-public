import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "Courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int duration;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private  CourseType type;

    private String description;

    @Column(name = "teacher_id")
    private int teacherId;

    @Column(name = "students_count")
    private Integer studentsCount;

    private int price;

    @Column(name = "price_per_hour")
    private float pricePerHour;

}
