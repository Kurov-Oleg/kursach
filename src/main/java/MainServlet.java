import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class MainServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        File f = new File("Credential.txt");
        if( !f.exists() ) {
            f.createNewFile();
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html><head><link rel=\"stylesheet\" href=\"style.css\"></head><body>");
        HttpSession session = request.getSession();
        if( session.getAttribute("name") != null ){
            if( session.getAttribute("name").equals("admin") ) {
                request.getRequestDispatcher("linksa.html").include(request, response);
            } else {
                request.getRequestDispatcher("links.html").include(request, response);

            }
        } else {
            request.getRequestDispatcher("login.html").include(request, response);
        }
        String admin = (String) session.getAttribute("name");
        ToDo todo = new ToDo();
        out.println("<div id=\"in progress\" class=\"tabcontent\"> <h3>in progress</h3>");
        out.println(" <form name = \"form27\" action = \"DoneServlet\" method = \"post\">");
        out.println("<input type = \"text\" name = \"topic\" placeholder = \"topic\"> <br>");
        out.println("<input type = \"submit\"  value = \"set as done\">");
        out.println("</form> <br> <br>");
        out.println(todo.checkIn(admin)+"</div>");
        out.println("<div id = \"done\" class = \"tabcontent\"> <h3> done </h3>" + todo.checkDone(admin) + "</div>");
        out.println("<div id = \"add\" class = \"tabcontent\">");
        out.println("<form  name = form1\"  action = \"AddServlet\" method = \"post\">");
        out.println("<input type = \"text\" name = \"topic\" placeholder = \"topic\"><br>");
        out.println("<input type = \"text\" name = \"final_date\" placeholder = \"final_date\"> <br>");
        out.println("<input type = \"text\" name = \"task\" placeholder = \"task\"> <br>");
        out.println("<input type = \"submit\"  value = \"add\">");
        out.println("</form>");
        out.println("</div>");
        out.println("</body></html>");
        out.close();
    }
}
