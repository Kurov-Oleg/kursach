import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        Desk book = new Desk();
        HttpSession session = request.getSession();
        session.invalidate();

        PrintWriter out=response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html><head><link rel=\"stylesheet\" href=\"style2.css\"></head><body>");

        request.getRequestDispatcher("login.html").include(request, response);
        out.println("You are successfully logged out!");
        out.println("<p1>" + book.getDesk() + "</p1>");
        out.println("</html></body>");

        out.close();
    }
}
