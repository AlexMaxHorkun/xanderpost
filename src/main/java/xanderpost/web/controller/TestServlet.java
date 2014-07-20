package xanderpost.web.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestServlet extends HttpServlet{

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
        res.getWriter().println("<h3>Vasia</h3>");
    }
}
