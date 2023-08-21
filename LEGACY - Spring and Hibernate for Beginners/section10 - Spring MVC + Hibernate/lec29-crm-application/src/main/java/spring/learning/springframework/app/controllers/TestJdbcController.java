package spring.learning.springframework.app.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet("/db")
public class TestJdbcController extends HttpServlet {
    private static final long serialVersionUID = 1L;


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     * http://localhost:8080/spring-course/db
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // setup connection variables
        String user = "root";
        String pwd  = "root";

        String jdbcUrl = "jdbc:mysql://localhost:3309/" +
                "web_customer_tracker?" +
                "useSSL=false&" +
                "serverTimezone=UTC&" +
                "createDatabaseIfNotExist=TRUE";

        String driver = "com.mysql.cj.jdbc.Driver";

        // get connection to database
        try {
            PrintWriter out = response.getWriter();
            out.println("Connecting to database : " + jdbcUrl);

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(jdbcUrl, user, pwd);

            out.println("\nConnection successful " + conn);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
