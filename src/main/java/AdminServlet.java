import SignIn.Credential;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import javax.servlet.*;
import javax.servlet.http.*;

public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        String admin = (String) session.getAttribute("name");
        if( admin.equals("admin")) {
            String name = request.getParameter("user");
            Credential logi = new Credential();
            logi.delete(name);
            Files.write(Paths.get(System.getProperty("user.dir")+"/Credential.txt"),
                    logi.getBook().entrySet().stream().map(k->k.getKey()+" "+k.getValue()).collect(Collectors.toList()),
                    StandardCharsets.UTF_8);
            File file = new File(System.getProperty("user.dir")+name+".txt");
            file.delete();
        }
        response.sendRedirect("/MainServlet");
    }
}
