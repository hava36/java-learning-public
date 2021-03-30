package dao;

import model.User;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
import util.db.DbConnector;
import util.db.JedisConnector;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class RedisUserDaoImpl implements Dao<String, User> {

    private static final String USER_KEY = "users";

    private DbConnector<Jedis> dbConnector;

    public RedisUserDaoImpl(DbConnector<Jedis> dbConnector) {
        this.dbConnector = dbConnector;
    }

    @Override
    public void add(User user) {
        Jedis jedis = dbConnector.getConnector();
        jedis.zadd(USER_KEY,
                user.getChangedDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
                user.getLogin());
    }

    @Override
    public void update(User user) {
        Jedis jedis = dbConnector.getConnector();
        jedis.zadd(USER_KEY,
                user.getChangedDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
                user.getLogin());
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public User get(String s) {
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        Jedis jedis = dbConnector.getConnector();
        Set<Tuple> data = jedis.zrangeByScoreWithScores(USER_KEY, Double.MIN_VALUE, Double.MAX_VALUE);
        data.forEach(tuple -> {
            result.add(new User(LocalDateTime.now(), tuple.getElement()));
        });
        return result;
    }

}
