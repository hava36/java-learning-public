package dao.impl.redis;

import dao.CourseDao;
import model.Course;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import util.DbConnector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseDaoRedisImpl implements CourseDao {

    public static final String HASH_KEY = "course#%d";
    public static final String SET_KEY = "courses";

    private DbConnector<Jedis, Transaction> dbConnector;

    public CourseDaoRedisImpl(DbConnector<Jedis, Transaction> dbConnector) {
        this.dbConnector = dbConnector;
    }

    @Override
    public void add(Course course) {
        Transaction transaction = dbConnector.getTransaction();
        String hash = String.format(HASH_KEY, course.getId());
        transaction.sadd(SET_KEY, String.valueOf(course.getId()));
        transaction.hset(hash, "id", String.valueOf(course.getId()));
        transaction.hset(hash, "name", course.getName());
    }

    @Override
    public Course get(Integer id) {
        Jedis jedis = dbConnector.getConnector();
        Map<String, String> result = jedis.hgetAll(String.format(HASH_KEY, id));
        Course course = new Course();
        course.setId(id);
        course.setName(result.get("name"));
        return course;
    }

    @Override
    public List<Course> getAll() {
        List<Course> courses = new ArrayList<>();
        Jedis jedis = dbConnector.getConnector();
        Set<String> keys = jedis.smembers(SET_KEY);
        keys.forEach(key -> {
            courses.add(get(Integer.valueOf(key)));
        });
        return courses;
    }

    @Override
    public void remove(Integer id) {
        Transaction transaction = dbConnector.getTransaction();
        String hash = String.format(HASH_KEY, id);
        transaction.srem(SET_KEY, String.valueOf(id));
        transaction.del(hash);
    }

}
