package main.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import main.entity.UserEntity;

@Data
@NoArgsConstructor
public class TaskDto extends AbstractBaseDto {

    private String description;
    private Integer duration;
    private UserEntity author;
    private UserEntity responsible;

}
