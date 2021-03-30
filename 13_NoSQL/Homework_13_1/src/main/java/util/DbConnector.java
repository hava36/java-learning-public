package util;

public interface DbConnector<Connector, Transaction> {

    boolean isConnected();
    void connect();
    void closeConnection();
    void beginTransaction();
    void commitTransaction();
    void rollBackTransaction();
    Transaction getTransaction();
    Connector getConnector();

}
