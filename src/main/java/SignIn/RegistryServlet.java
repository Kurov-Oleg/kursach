package SignIn;

import SignIn.Credential;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RegistryServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        name = name.trim();
        password = password.trim();
        PrintWriter out = response.getWriter();
        Credential credential = new Credential();
        out.println("<!DOCTYPE html>");
        out.println("<html> <head> <link rel = \"stylesheet\" href = \"style2.css\"> </head> <body>");
        Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "/Credential.txt"));
        if (credential.isFirst()) {
            while (scanner.hasNext()) {
                String line = scanner.next();
                String pass = scanner.next();
                credential.add(line, pass);
            }
        }
        if (credential.check (name)) {
            out.print("<h1>Sorry, name is already use</h1>");
            out.println("</body> </html>");
            request.getRequestDispatcher("login.html").include(request, response);
        } else {
            credential.add (name, password);
            out.print("<h1>successfully added </h1>");
            out.println("</body> </html>");
            HttpSession session = request.getSession();
            Files.write(Paths.get(System.getProperty("user.dir") + "/Credential.txt"),
                    credential.getBook().entrySet().stream().map( k->k.getKey() + " " + k.getValue()).collect(Collectors.toList()),
                    StandardCharsets.UTF_8);
            File f = new File(System.getProperty("user.dir") + "/" + name + ".txt");
            f.createNewFile();
            session.setAttribute("name", name);
            response.sendRedirect("/MainServlet");
        }
        out.close();
    }
}
