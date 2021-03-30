package entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("PMD")
@MappedSuperclass
@ToString(callSuper = true)
@Getter
@Setter
@EqualsAndHashCode
public abstract class AbsractHumanObject extends AbstractIdintifiableObject {

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

}
