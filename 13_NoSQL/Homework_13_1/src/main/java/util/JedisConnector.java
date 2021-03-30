package util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class JedisConnector implements DbConnector<Jedis, Transaction> {

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
    public void closeConnection() {
        jedis.close();
    }

    @Override
    public void beginTransaction() {
        this.transaction = jedis.multi();
    }

    @Override
    public void commitTransaction() {
        transaction.exec();
        transaction.close();
    }

    @Override
    public void rollBackTransaction() {
        transaction.discard();
        transaction.close();
    }

    @Override
    public Transaction getTransaction() {
        return transaction;
    }

    @Override
    public Jedis getConnector() {
        return jedis;
    }

}
