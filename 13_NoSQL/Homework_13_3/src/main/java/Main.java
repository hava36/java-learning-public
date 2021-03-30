import com.mongodb.MongoClient;
import dao.mongo.MongoStudentDao;
import dao.searcher.impl.mongo.MongoOldestStudentFinder;
import dao.searcher.impl.mongo.MongoStudentOverAgeFinder;
import dao.searcher.impl.mongo.MongoYoungestStudentFinder;
import java.io.IOException;
import java.util.List;
import model.Student;
import service.Service;
import service.impl.StudentService;
import util.parser.Parser;
import util.parser.comapator.StudentComparatorByAge;
import util.parser.csv.StudentCSVParser;

public class Main {

  public static void main(String[] args) throws IOException {

    MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);

    Parser<List<Student>> parser = new StudentCSVParser(
        Main.class.getClassLoader().getResource("mongo.csv").getFile());

    List<Student> students = parser.parse();

    Service<Student> service = new StudentService(
        new MongoStudentDao(mongoClient));

    service.addAll(students);

    System.out.printf("Student count: %s", service.readAll().size());
    System.out.println();

    System.out.printf("Students over 40: %s",
        service.read(new MongoStudentOverAgeFinder(mongoClient, 40)).size());
    System.out.println();

    students = service.readAll();
    students.sort(new StudentComparatorByAge());

    System.out.printf("Youngest student %s",
        service.read(new MongoYoungestStudentFinder(mongoClient)).get(0));
    System.out.println();

    System.out.printf("Oldest student %s",
        service.read(new MongoOldestStudentFinder(mongoClient)).get(0));
    System.out.println();

  }

}
