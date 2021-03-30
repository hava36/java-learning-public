package util.db;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class JedisConnector implements DbConnector<Jedis> {

    private Jedis jedis;
    private Transaction transaction;

    public JedisConnector(String host, Integer port) {
        this.jedis = new Jedis(host, port);
    }

    @Override
    public boolean isConnected() {
        return jedis.isConnected();
    }

    @Override
    public void connect() {
        jedis.connect();
    }

    @Override
    public Jedis getConnector() {
        return jedis;
    }

}
