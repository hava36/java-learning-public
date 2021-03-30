package dao.searcher.impl.mongo;

import static dao.mongo.MongoStudentDao.DB_NAME;
import static dao.mongo.MongoStudentDao.STUDENT_COLLECTION_NAME;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import dao.searcher.SearchHandler;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import model.Student;
import org.bson.Document;

@AllArgsConstructor
public class MongoStudentOverAgeFinder implements SearchHandler<Student> {

  private final MongoClient mongoClient;
  private final Integer age;

  @Override
  public List<Student> find() {
    MongoDatabase database = mongoClient.getDatabase(DB_NAME);
    MongoCollection collection = database
        .getCollection(STUDENT_COLLECTION_NAME);

    BasicDBObject basicDBObject = new BasicDBObject();
    basicDBObject.put("$gte", age);
    MongoCursor<Document> iterator = collection.find(new BasicDBObject("age", basicDBObject))
        .iterator();
    List<Student> students = new ArrayList<>();
    while (iterator.hasNext()) {
      Document document = iterator.next();
      Student student = new Student();
      student.setName((String) document.get("name"));
      student.setAge((Integer) document.get("age"));
      students.add(student);
    }
    return students;
  }

}
