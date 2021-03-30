package entities;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@ToString(exclude = "courses", callSuper = true)
@Getter
@Entity
@Table(name = "Teachers")
public class Teacher extends AbsractHumanObject {

    @Column(name = "salary")
    private Integer salary;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "teacher")
    private List<Course> courses;

}
