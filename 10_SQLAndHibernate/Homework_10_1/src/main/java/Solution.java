import java.sql.*;
import java.util.ResourceBundle;

public class Solution {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "1234";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT\n" +
                            "purchaselist.course_name AS course_name,\n" +
                            "COUNT(purchaselist.student_name) / (MAX(Month(purchaselist.subscription_date)) - MIN(Month(purchaselist.subscription_date)) + 1) AS average_sales\n" +
                            "FROM purchaselist\n" +
                            "WHERE YEAR(purchaselist.subscription_date) = 2018\n" +
                            "GROUP BY course_name\n" +
                            "ORDER BY average_sales DESC");

            while (resultSet.next()) {
                System.out.printf("%s - %.2f\n",
                        resultSet.getString("course_name"),
                        resultSet.getDouble("average_sales"));
            }
            statement.close();
            resultSet.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }






    }

}
