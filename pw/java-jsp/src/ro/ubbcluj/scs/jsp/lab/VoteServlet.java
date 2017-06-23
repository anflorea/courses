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

public class VoteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        int imageId = Integer.parseInt(request.getParameter("imageid"));
        int vote = Integer.parseInt(request.getParameter("vote"));

        int userId = (int) session.getAttribute("userId");

        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            Connection conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);

            // Execute SQL query
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM images WHERE id=" + imageId;
            ResultSet rs = stmt.executeQuery(sql);

            int currentVote = 0;
            if (rs.next())
                currentVote = rs.getInt("votes");

            sql = "UPDATE images SET votes=" + (currentVote + vote) + " WHERE id=" + imageId;
            stmt.executeUpdate(sql);

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        session.setAttribute("message", "Vote assigned successfully.");
        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("userId") == null) {
            session.setAttribute("error", "Please login first");
            response.sendRedirect("login.jsp");
            return;
        }

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Vote</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<a href='index.jsp'>Home</a>");

        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            Connection conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);

            // Execute SQL query
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM images";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int imageId = rs.getInt("id");
                String ref_id = rs.getString("ref_id");
                String extension = rs.getString("extension");
                int userId = rs.getInt("userid");
                int votes = rs.getInt("votes");

                if (userId == (int) session.getAttribute("userId")) {
                    continue;
                }

                out.println("<div>");
                out.println("<img src='ImageServlet/" + ref_id + "." + extension + "' style=\"display: inline-block; width: 300px; height: 300px;\" />");
                out.println("<form action=\"VoteServlet\" method=\"post\" style=\"display: inline-block;\">");
                out.println("Vote: <input type=\"text\" name=\"vote\"> <input type='hidden' name='imageid' value='" + imageId + "'> <input type=\"submit\" value=\"Submit vote\"> ");
                out.println("</form>");
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
