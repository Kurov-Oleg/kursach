import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html><head><link rel=\"stylesheet\" href=\"style2.css\"></head><body>");



        HttpSession session = request.getSession();
        toDo book = new toDo();
        String admin = (String) session.getAttribute("name");
        String final_date = request.getParameter("final_date");
        String task = request.getParameter("task");
        String topic = request.getParameter("topic");


            book.addDo(admin, final_date, task, topic, "in progress");


        response.sendRedirect("/MainServlet");




       // out.println( book.getDesk() );


        out.println("</body></html>");
        out.close();
    }


}
