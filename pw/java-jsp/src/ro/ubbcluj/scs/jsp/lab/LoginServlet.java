package ro.ubbcluj.scs.jsp.lab;

import ro.ubbcluj.scs.jsp.lab.Utils.Constants;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

/**
 * Created by flo on 27/05/2017.
 */
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("user");
        String password = request.getParameter("password");
        String pass = "";

        HttpSession session = request.getSession();
        boolean ok = false;

        try{
            // Register JDBC driver
            Class.forName(Constants.JDBC_DRIVER);

            // Open a connection
            Connection conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);

            // Execute SQL query
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, name, username, password FROM USER";
            ResultSet rs = stmt.executeQuery(sql);

            // Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                if (!rs.getString("username").equals(userName))
                    continue;
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String dbPassword = rs.getString("password");

                log("user: " + name + " : " + dbPassword + " -- " + password);
                if (password.equals(dbPassword)) {

                    ok = true;
                    session.setAttribute("userId", id);
                    session.setAttribute("userName", userName);
                    session.setAttribute("name", name);
                }
            }

            // Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch(Exception e) {
            //Handle errors for Class.forName
            session.setAttribute("error", e.getStackTrace().toString());
        } //end try

        if (ok) {
            session.setAttribute("message", "Successfully logged in.");
            response.sendRedirect("index.jsp");
        } else {
            session.setAttribute("error", "Login failed. Invalid username or password");
            response.sendRedirect("login.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
