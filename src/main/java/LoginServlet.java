import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        name = name.trim();
        password = password.trim();
        PrintWriter out = response.getWriter();
        Credentional logi = new Credentional();
        out.println("<!DOCTYPE html>");
        out.println("<html><head><link rel=\"stylesheet\" href=\"style2.css\"></head><body>");
        Scanner scanner = new Scanner(new File("/home/zgymko/IdeaProjects/lab15/src/main/resources/Logi.txt"));
        if(logi.isFirst()) {
            while (scanner.hasNext()) {
                String line = scanner.next();
                String pass = scanner.next();
                logi.add(line,pass);
            }
        }
            if(logi.check(name)) {
                if( logi.checkPass(name, password) ){
                    HttpSession session = request.getSession();
                    session.setAttribute("name", name);
                    response.sendRedirect("/MainServlet");
                }
                else{
                    out.print("<h1>Sorry, password is wrong</h1>");
                    request.getRequestDispatcher("login.html").include(request, response);
                }
            } else {
                out.print("<h1>Sorry, name is wrong</h1>");
                request.getRequestDispatcher("login.html").include(request, response);
            }
        out.println("</body></html>");
        out.close();
    }
}
