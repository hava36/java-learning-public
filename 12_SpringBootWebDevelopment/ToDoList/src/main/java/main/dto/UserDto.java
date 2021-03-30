package main.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import main.entity.RoleEntity;

@Data
@NoArgsConstructor
public class UserDto extends AbstractBaseDto {

    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private RoleDto role;

}
