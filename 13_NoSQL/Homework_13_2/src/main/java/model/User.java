package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@ToString
public class User {

    @ToString.Exclude
    private LocalDateTime changedDate;

    private String login;

}
