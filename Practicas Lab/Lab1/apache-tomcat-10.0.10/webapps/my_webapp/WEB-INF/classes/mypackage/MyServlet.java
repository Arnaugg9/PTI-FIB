package mypackage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

    public class MyServlet extends HttpServlet {
      public void doGet(HttpServletRequest req, HttpServletResponse res)
                        throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><big>I'm a servlet!!</big></html>");
      }
    }

