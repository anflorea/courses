package ro.ubbcluj.scs.jsp.lab;

import ro.ubbcluj.scs.jsp.lab.Utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by flo on 29/05/2017.
 */

public class SeePhotosServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new ServletException("POST method used with " +
                getClass( ).getName( )+": GET method required.");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        int n = 5;

        if (request.getParameter("n") != null) {
            int value = (int) Integer.parseInt(request.getParameter("n"));
            if (value > 0)
                n = value;
        }

        if (session.getAttribute("userId") == null) {
            session.setAttribute("error", "Please login first");
            response.sendRedirect("login.jsp");
            return;
        }

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>See Photos</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<a href='index.jsp'>Home</a> <br><br>");

        out.println("<form action='SeePhotosServlet' method='get'>");
        out.println("Number of photos: <input type='text' value='" + n + "' name='n'>");
        out.println("<input type='submit' value='Submit'>");
        out.println("</form>");

        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            Connection conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);

            // Execute SQL query
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT images.ref_id, images.extension, images.votes, user.name FROM images INNER JOIN user ON images.userid=user.id ORDER BY votes DESC LIMIT " + n;
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String ref_id = rs.getString("ref_id");
                String extension = rs.getString("extension");
                String name = rs.getString("name");
                int votes = rs.getInt("votes");

                out.println("<div>");
                out.println("<img src='ImageServlet/" + ref_id + "." + extension + "' style=\"display: inline-block; width: 300px; height: 300px;\" />");
                out.println("<div style=\"display: inline-block;\">");
                out.println("Author: " + name + " Votes: " + votes);
                out.println("</div>");
                out.println("</div>");

            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception pikachu) {
            pikachu.printStackTrace();
        }

        out.println("</body>");
        out.println("</html>");
    }
}
