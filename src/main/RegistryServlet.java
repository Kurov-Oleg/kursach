import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.BatchUpdateException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RegistryServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        name = name.trim();
        password = password.trim();
        PrintWriter out = response.getWriter();
        Logi logi = new Logi();
        out.println("<!DOCTYPE html>");
        out.println("<html><head><link rel=\"stylesheet\" href=\"style2.css\"></head><body>");

        boolean flag = false;
        Scanner scanner = new Scanner(new File("/home/zgymko/IdeaProjects/lab15/src/main/resources/Logi.txt"));
        if (logi.isFirst()) {
            while (scanner.hasNext()) {
                String line = scanner.next();
                String pass = scanner.next();
                logi.add(line, pass);
            }
        }
        if (logi.check(name)) {
            out.print("<h1>Sorry, name is already use</h1>");
            out.println("</body></html>");
            request.getRequestDispatcher("login.html").include(request, response);
        } else {
            logi.add(name, password);
            out.print("<h1>successfully added </h1>");
            out.println("</body></html>");
            HttpSession session = request.getSession();
            Files.write(Paths.get("/home/zgymko/IdeaProjects/lab15/src/main/resources/Logi.txt"),
                    logi.getBook().entrySet().stream().map(k->k.getKey()+" "+k.getValue()).collect(Collectors.toList()),
                    StandardCharsets.UTF_8);


            Writer writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(
                                    new StringBuilder().append("/home/zgymko/IdeaProjects/lab15/src/main/resources/").append(name).append(".txt").toString()),"utf-8"));



            session.setAttribute("name", name);
            response.sendRedirect("/MainServlet");
        }


        out.close();

    }
}
