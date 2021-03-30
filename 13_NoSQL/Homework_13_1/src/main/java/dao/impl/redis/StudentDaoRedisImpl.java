package dao.impl.redis;

import dao.CourseDao;
import dao.StudentDao;
import model.Student;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import util.DbConnector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StudentDaoRedisImpl implements StudentDao {

    public static final String STUDENT_HASH_KEY = "student#%d";
    public static final String STUDENT_SET_KEY = "students";
    public static final String HOMEWORK_HASH_KEY = "homework#%d";

    private DbConnector<Jedis, Transaction> dbConnector;

    private CourseDao courseDao;

    public StudentDaoRedisImpl(DbConnector<Jedis, Transaction> dbConnector) {
        this.dbConnector = dbConnector;
        this.courseDao = new CourseDaoRedisImpl(dbConnector);
    }

    @Override
    public void add(Student student) {
        Transaction transaction = dbConnector.getTransaction();
        String studentHash = String.format(STUDENT_HASH_KEY, student.getId());
        transaction.sadd(STUDENT_SET_KEY, String.valueOf(student.getId()));
        transaction.hset(studentHash, "id", String.valueOf(student.getId()));
        transaction.hset(studentHash, "first name", student.getFirstName());
        transaction.hset(studentHash, "last name", student.getLastName());

        String homeworkHash = String.format(HOMEWORK_HASH_KEY, student.getId());
        student.getHomeworks().forEach((course, homeworkCount) -> {
            transaction.hset(homeworkHash,
                    String.format("%d", course.getId()), String.valueOf(homeworkCount));
        });

    }

    @Override
    public Student get(Integer id) {
        Jedis jedis = dbConnector.getConnector();
        Map<String, String> result = jedis.hgetAll(String.format(STUDENT_HASH_KEY, id));
        Student student = new Student();
        student.setId(id);
        student.setFirstName(result.get("first name"));
        student.setLastName(result.get("last name"));

        String homeworkHash = String.format(HOMEWORK_HASH_KEY, student.getId());
        jedis.hgetAll(homeworkHash).forEach((courseId, homeworkCount) -> {
            student.addPassedHomework(courseDao.get(Integer.valueOf(courseId)),
                    Integer.valueOf(homeworkCount));
        });

        return student;
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        Jedis jedis = dbConnector.getConnector();
        Set<String> keys = jedis.smembers(STUDENT_SET_KEY);
        keys.forEach(key -> {
            students.add(get(Integer.valueOf(key)));
        });
        return students;
    }

    @Override
    public void remove(Integer id) {
        Transaction transaction = dbConnector.getTransaction();
        String hash = String.format(STUDENT_HASH_KEY, id);
        transaction.srem(STUDENT_SET_KEY, String.valueOf(id));
        transaction.del(hash);
    }

}
