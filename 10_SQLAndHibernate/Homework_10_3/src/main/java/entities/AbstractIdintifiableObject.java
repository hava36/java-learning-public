package entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@SuppressWarnings("PMD")
@MappedSuperclass
@ToString
@Getter
@Setter
@EqualsAndHashCode
public abstract class AbstractIdintifiableObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

}
