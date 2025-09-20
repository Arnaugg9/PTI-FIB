package mypackage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class CarRentalNew extends HttpServlet {

  int cont = 0;

  public void doGet(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    String rating = req.getParameter("co2_rating");
    String days = req.getparameter("dies_lloguer");
    String discount = req.getparameter("descompte");
    String engine = req.getparameter("sub_model_vehicle");
    String units = req.getparameter("num_vehicles");
    
    boolean correct_data = true;
    
    out.println("<html><h1>New Data: </h1></html>" +  + "")
    
    
    //cont ++;
    
    
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
