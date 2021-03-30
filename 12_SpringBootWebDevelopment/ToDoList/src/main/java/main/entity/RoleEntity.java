package main.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@Setter
@Getter
@NoArgsConstructor
public class RoleEntity extends AbstractBaseEntity {

    @Column(name = "name", length = 50)
    private String name;

}
