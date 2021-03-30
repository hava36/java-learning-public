package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import java.util.List;

@Getter
@ToString(callSuper = true)
@Entity
@Table(name = "Courses")
@NoArgsConstructor
public class Course extends AbstractIdintifiableObject {

    @Column(name = "name")
    private String name;

    @Column(name = "duration")
    private Integer duration;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "enum")
    private CourseType type;

    @Column(name = "description")
    private String description;

    @Column(name = "students_count")
    private Integer studentsCount;

    @Column(name = "price")
    private Integer price;

    @Column(name = "price_per_hour")
    private float pricePerHour;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "course")
    private List<Subscription> subscriptions;

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Subscriptions",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private List<Student> students;

}
