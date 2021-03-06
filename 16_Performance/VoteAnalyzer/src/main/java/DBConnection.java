import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Map.Entry;
import model.Voter;

public class DBConnection {

  private static final String dbName = "performance";
  private static final String dbUser = "root";
  private static final String dbPass = "1234";
  private static Connection connection;

  public static Connection getConnection() {
    if (connection == null) {
      try {
        connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/" + dbName +
                "?user=" + dbUser + "&password=" + dbPass);
        connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
        connection.createStatement().execute("CREATE TABLE voter_count(" +
            "id INT NOT NULL AUTO_INCREMENT, " +
            "name TINYTEXT NOT NULL, " +
            "birthDate DATE NOT NULL, " +
            "`count` INT NOT NULL, " +
            "PRIMARY KEY(id))");
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return connection;
  }

  public static void insertVoterCounts(Map<Voter, Integer> voterCounts) throws SQLException {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    StringBuffer queryBuffer = new StringBuffer();
    for (Entry<Voter, Integer> entry : voterCounts.entrySet()
    ) {
      if (queryBuffer.length() != 0) {
        queryBuffer.append(", ");
      }
      queryBuffer.append(String
          .format("('%s', '%s', '%s')", entry.getKey().getName(),
              dateFormat.format(entry.getKey().getBirthDay()), entry.getValue()));
      if (queryBuffer.length() > 3000000) {
        queryBuffer.insert(0, "INSERT INTO voter_count(name, birthDate, `count`) VALUES ");
        DBConnection.getConnection().createStatement()
            .execute(queryBuffer.toString());
        queryBuffer.setLength(0);
      }
    }
    if (queryBuffer.length() > 0) {
      queryBuffer.insert(0, "INSERT INTO voter_count(name, birthDate, `count`) VALUES ");
      DBConnection.getConnection().createStatement()
          .execute(queryBuffer.toString());
    }
    System.out.println("Multi-inserting completed");
  }

  public static void countVoter(String name, String birthDay) throws SQLException {
    birthDay = birthDay.replace('.', '-');
    String sql =
        "SELECT id FROM voter_count WHERE birthDate='" + birthDay + "' AND name='" + name + "'";
    ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
    if (!rs.next()) {
      DBConnection.getConnection().createStatement()
          .execute("INSERT INTO voter_count(name, birthDate, `count`) VALUES('" +
              name + "', '" + birthDay + "', 1)");
    } else {
      int id = rs.getInt("id");
      DBConnection.getConnection().createStatement()
          .execute("UPDATE voter_count SET `count`=`count`+1 WHERE id=" + id);
    }
    rs.close();
  }

  public static void printVoterCounts() throws SQLException {
    String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
    ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
    while (rs.next()) {
      System.out.println("\t" + rs.getString("name") + " (" +
          rs.getString("birthDate") + ") - " + rs.getInt("count"));
    }
  }
}
