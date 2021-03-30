package entities;

import lombok.*;
import java.io.Serializable;
import java.util.Date;

@Data
public class Purchase implements Serializable {

    private String studentName;

    private String courseName;

    private Integer price;

    private Integer studentId;

    private Integer courseId;

}
