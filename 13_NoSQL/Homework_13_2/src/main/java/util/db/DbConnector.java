package util.db;

public interface DbConnector<Connector> {

    boolean isConnected();
    void connect();
    Connector getConnector();

}
