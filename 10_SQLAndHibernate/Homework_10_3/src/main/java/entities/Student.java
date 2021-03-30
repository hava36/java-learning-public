package entities;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@ToString(callSuper = true)
@Entity
@Table(name = "Students")
public class Student extends AbsractHumanObject {

    @Column(name = "registration_date")
    private Date registrationDate;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "student")
    private List<Subscription> subscriptions;

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Subscriptions",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private List<Course> courses;

}
