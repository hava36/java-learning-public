package entities;

import entities.keys.LinkedPurchaseKey;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "LinkedPurchaseList")
public class LinkedPurchase implements Serializable {

    @EmbeddedId
    private LinkedPurchaseKey id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", updatable = false, insertable = false)
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", updatable = false, insertable = false)
    private Course course;

}
