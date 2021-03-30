package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class Student {

    private Integer id;

    private String firstName;

    private String lastName;

    private Map<Course, Integer> homeworks;

    public Student() {
        homeworks = new HashMap<>();
    }

    public void addPassedHomework(Course course, Integer count) {
        Integer currentCount = 0;
        if (homeworks.containsKey(course)) {
            currentCount = homeworks.get(course);
        }
        currentCount += count;
        homeworks.put(course, currentCount);
    }

}
