package controller.signIn;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        session.invalidate();
        PrintWriter out=response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html> <head> <link rel = \"stylesheet\" href = \"style2.css\"> </head><body>");
        request.getRequestDispatcher("login.html").include(request, response);
        out.println( "</html> </body>" );
        out.close();
    }
}
