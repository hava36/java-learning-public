import dao.RedisUserDaoImpl;
import model.User;
import redis.clients.jedis.Jedis;
import service.Service;
import service.UserServiceImpl;
import util.db.DbConnector;
import util.db.JedisConnector;
import util.registration.RegisterHandler;
import util.registration.UserRegistrar;
import util.scanner.UserScanner;

import java.time.LocalDateTime;

public class Application {

    public static void main(String[] args) {

        DbConnector<Jedis> dbConnector = new JedisConnector("localhost", 6379);

        Service<String, User> service = new UserServiceImpl(new RedisUserDaoImpl(dbConnector));

        RegisterHandler<User> register = new UserRegistrar(service);

        for (int i = 1; i < 21; i++) {
            register.register(new User(LocalDateTime.now(), String.format("user#%d", i)));
        };

        UserScanner userScanner = new UserScanner(service);
        userScanner.scan();

    }

}
