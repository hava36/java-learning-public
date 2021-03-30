package main.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
@Setter
@Getter
@NoArgsConstructor
public class TaskEntity extends AbstractBaseEntity {

    @Column(name = "description", length = 512, nullable = false)
    private String description;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @OneToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;

    @OneToOne
    @JoinColumn(name = "responsible_id")
    private UserEntity responsible;

}
