package controller.work;

import models.ToDo;

import java.io.*;

import javax.servlet.http.*;

public class DoneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        ToDo book = new ToDo();
        String admin = (String) session.getAttribute("name");
        String topic = request.getParameter("topic");
        book.setAs(admin,topic,"done");
        response.sendRedirect("/MainServlet");
    }
}
