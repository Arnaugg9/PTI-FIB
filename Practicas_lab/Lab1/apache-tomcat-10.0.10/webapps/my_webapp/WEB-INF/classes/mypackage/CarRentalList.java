package mypackage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class CarRentalList extends HttpServlet {

  int cont = 0;

  public void doGet(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    String nombre = req.getParameter("userid");
    String passwd = req.getParameter("password");
    //cont ++;
    
    if (nombre.equals("admin") && passwd.equals("admin")){
    	out.println("<html><h1>Rentals list</h1></html>");
    	
    	
    }
    
    else {
    	out.println("<html><h1>No Permission</h1></html>");
    }
    
    /*
    out.println("<html><big>Hola Amigo "+ nombre + "</big><br>"+
                cont + " Accesos desde su carga.</html>");
                */
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {
    doGet(req, res);
  }
}
