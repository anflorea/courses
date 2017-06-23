package ro.ubbcluj.scs.java.snake.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by flo on 04/06/2017.
 */
public class CreateDBServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        try {
            Class.forName(Constants.JDBC_DRIVER);

            Connection conn = DriverManager.getConnection(Constants.DB_BASE_URL, Constants.USER, Constants.PASS);

            Statement stmt = conn.createStatement();
            String sql;
            sql = "CREATE DATABASE IF NOT EXISTS db_snake;";
            stmt.executeUpdate(sql);

            sql = "USE db_snake";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "username VARCHAR(255) NOT NULL," +
                    "name VARCHAR(255) NOT NULL," +
                    "password VARCHAR(255) NOT NULL," +
                    "created_at TIMESTAMP" +
                    ")";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO users (username, name, password) " +
                    "VALUES ('andra', 'Andra Pop', '12345')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO users (username, name, password) " +
                    "VALUES ('flo', 'Andrei Florea', '12345')";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS game (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "userid INT NOT NULL," +
                    "gamestate LONGBLOB NOT NULL," +
                    "started_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                    "FOREIGN KEY (userid) REFERENCES users(id)" +
                    ")";
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();

            out.println("Database created successfully");
        } catch (Exception e) {
            out.println("Database creation error:");
            e.printStackTrace(out);
        }

    }
}
