import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        ToDo book = new ToDo();
        String admin = (String) session.getAttribute("name");
        String final_date = request.getParameter("final_date");
        String task = request.getParameter("task");
        String topic = request.getParameter("topic");
        book.addDo(admin, final_date, task, topic, "in progress");
        response.sendRedirect("/MainServlet");
    }
}
