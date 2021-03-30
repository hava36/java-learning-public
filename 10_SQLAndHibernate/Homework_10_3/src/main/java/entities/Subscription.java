package entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@ToString
@Getter
@Entity
@Table(name = "Subscriptions")
public class Subscription implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "subscription_date", nullable = false)
    private Date subscriptionDate;

}
