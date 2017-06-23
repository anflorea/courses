package ro.ubbcluj.scs.jsp.lab;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by flo on 29/05/2017.
 */

public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new ServletException("POST method used with " +
                getClass( ).getName( )+": GET method required.");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        session.removeAttribute("userId");
        session.removeAttribute("userName");
        session.removeAttribute("name");

        session.setAttribute("message", "Successfully logged out");
        response.sendRedirect("index.jsp");
    }
}
