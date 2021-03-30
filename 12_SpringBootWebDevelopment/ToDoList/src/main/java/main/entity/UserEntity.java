package main.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class UserEntity extends AbstractBaseEntity {

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "login", length = 50)
    private String login;

    @Column(name = "password", length = 512)
    private String password;

    @OneToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

}
