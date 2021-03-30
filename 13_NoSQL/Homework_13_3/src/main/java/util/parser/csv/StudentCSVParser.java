package util.parser.csv;

import exception.parser.student.ParseCourseException;
import exception.parser.student.ParseStudentNameException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Course;
import model.Student;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import util.parser.Parser;

public class StudentCSVParser implements Parser<List<Student>> {

  private final Map<String, Course> courseMap;
  private String filename;
  private CSVParser parser;

  public StudentCSVParser(String filename) {
    this.filename = filename;
    this.courseMap = new HashMap<>();
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  @Override
  public List<Student> parse() {
    List<Student> students = new ArrayList<>();
    try {
      parser = new CSVParser(
          new FileReader(filename),
          CSVFormat.DEFAULT.withIgnoreHeaderCase());
      for (CSVRecord record : parser
      ) {
        Student student = parseStudent(record);
        if (student != null) {
          students.add(student);
        }
      }
      parser.close();
      return students;
    } catch (IOException | ParseStudentNameException | ParseCourseException exception) {
      exception.printStackTrace();
    }
    return null;
  }

  private Student parseStudent(CSVRecord record)
      throws ParseStudentNameException, ParseCourseException {
    Student student = new Student();
    try {
      student.setName(record.get(0));
    } catch (Exception exception) {
      throw new ParseStudentNameException(exception.getMessage());
    }
    try {
      student.setAge(Integer.valueOf(record.get(1)));
    } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception) {
      throw new ParseStudentNameException(exception.getMessage());
    }

    try {
      student.setCourses(parseCourses(record));
    } catch (Exception exception) {
      throw new ParseCourseException(exception.getMessage());
    }
    return student;
  }

  private List<Course> parseCourses(CSVRecord record) throws ParseCourseException {
    List<Course> courses = new ArrayList<>();
    try {
      String[] courseNames = record.get(2).split(",");
      for (String courseName : courseNames
      ) {
        courses.add(parseCourse(courseName));
      }
    } catch (Exception exception) {
      throw new ParseCourseException(exception.getMessage());
    }
    return courses;
  }

  private Course parseCourse(String courseName) {
    Course course = null;
    if (courseMap.containsKey(courseName)) {
      course = courseMap.get(courseName);
    } else {
      course = new Course(courseName);
      courseMap.put(courseName, course);
    }
    return course;
  }

}
