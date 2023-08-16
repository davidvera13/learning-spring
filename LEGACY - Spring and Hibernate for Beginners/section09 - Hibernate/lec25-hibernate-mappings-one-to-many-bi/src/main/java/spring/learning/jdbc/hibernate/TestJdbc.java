package spring.learning.jdbc.hibernate;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// run first
public class TestJdbc {
    public static void main(String[] args) {
        String jdbc = "jdbc:mysql://localhost:3309/" +
                "hb-03-one-to-many-bi?" +
                "useSSL=false&" +
                "serverTimezone=UTC&" +
                "createDatabaseIfNotExist=TRUE";

        String user = "root";
        String pwd  = "root";
        try {
            System.out.println("Connecting to database");
            Connection conn = DriverManager
                    .getConnection(jdbc, user, pwd);
            System.out.println(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
