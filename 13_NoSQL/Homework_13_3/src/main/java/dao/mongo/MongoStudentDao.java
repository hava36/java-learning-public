package dao.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dao.Dao;
import dao.searcher.SearchHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import model.Course;
import model.Student;
import org.bson.codecs.ValueCodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.jsr310.Jsr310CodecProvider;
import org.bson.codecs.pojo.PojoCodecProvider;

public class MongoStudentDao implements Dao<Student> {

  public static final String DB_NAME = "db_test";
  public static final String STUDENT_COLLECTION_NAME = "students";

  private final MongoClient mongoClient;
  private final MongoDatabase database;
  private final MongoCollection<Student> collection;

  public MongoStudentDao(MongoClient mongoClient) {
    this.mongoClient = mongoClient;
    this.database = mongoClient.getDatabase(DB_NAME)
        .withCodecRegistry(CodecRegistries.fromProviders(
            PojoCodecProvider.builder().register(Student.class, Course.class).build(),
            new Jsr310CodecProvider(),
            new ValueCodecProvider()));
    this.collection = database
        .getCollection(STUDENT_COLLECTION_NAME, Student.class);

  }

  @Override
  public void add(Student student) {

    collection.insertOne(student);

  }

  @Override
  public void addAll(List<Student> students) {

    collection.drop();
    collection.insertMany(students);

  }

  @Override
  public List<Student> readAll() {
    List<Student> students = new ArrayList<>();
    collection.find().forEach((Consumer<? super Student>) student -> {
      students.add(student);
    });

    return students;
  }

  @Override
  public List<Student> read(SearchHandler<Student> handler) {
    return handler.find();
  }

}
