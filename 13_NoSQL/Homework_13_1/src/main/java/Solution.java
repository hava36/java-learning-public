import model.Course;
import model.Student;
import service.StudentService;
import service.impl.StudentServiceImpl;
import util.DbConnector;
import util.JedisConnector;

public class Solution {

    public static void main(String[] args) {

        DbConnector dbConnector = new JedisConnector("localhost", 6379);
        dbConnector.connect();

        StudentService studentService = new StudentServiceImpl(dbConnector);

        Course course1 = new Course();
        course1.setId(1);
        course1.setName("Java");

        Course course2 = new Course();
        course2.setId(2);
        course2.setName("PHP");
        
        Student student1 = new Student();
        student1.setId(1);
        student1.setFirstName("John");
        student1.setLastName("Johnson");

        student1.addPassedHomework(course1,1);
        student1.addPassedHomework(course2,1);
        student1.addPassedHomework(course2,1);

        studentService.add(student1);

        Student student2 = new Student();
        student2.setId(2);
        student2.setFirstName("Mike");
        student2.setLastName("Mayers");

        student2.addPassedHomework(course1,1);
        student2.addPassedHomework(course2,1);
        student2.addPassedHomework(course2,1);
        student2.addPassedHomework(course2,1);

        studentService.add(student2);

        studentService.getAll().forEach(s -> {
            System.out.println(s);
        });

        if (dbConnector.isConnected()) {
            dbConnector.closeConnection();
        }

    }

}
