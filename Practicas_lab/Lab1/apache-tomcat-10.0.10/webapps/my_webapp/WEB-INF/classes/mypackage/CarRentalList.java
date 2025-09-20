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
    
    if (nombre.equals("admin") && passwd.equals("admin")){
    	out.println("<html><h1>Rentals list</h1><hr></html>");
    	
      String path = "/WEB-INF/classes/mypackage/rentals.json";
      File file = new File(getServletContext().getRealPath(path));
      
    	JSONParser parser = new JSONParser();
      JSONObject obj = null;
      JSONArray array = null;
      
      if (file.exists()) {
        try {
          obj = (JSONObject) parser.parse(new FileReader(file));
          array = (JSONArray) obj.get("rentals");
        } catch (Exception e) {
          out.println("<p>Error on reading JSON file: " + e.getMessage() + "</p>");
          e.printStackTrace(new PrintWriter(out));
          return;
        }

        int cont = 1;
        for (Object o : array) {
          JSONObject currentObj = (JSONObject)o;
          out.println(
            "<h2>Car Rental Nº " + cont++ + "</h2>" +
            "<p>CO2 Rating: " + currentObj.get("rating") + "</p>" +
            "<p>Engine: " + currentObj.get("engine") + "</p>" +
            "<p>Days of Rental: " + currentObj.get("rental_days") + "</p>" +
            "<p>Units: " + currentObj.get("units") + "</p>" +
            "<p>Discount: " + currentObj.get("discount") + "</p>" +
            "<hr/>");
        }
      }
      else {
        out.println("<p>No JSON file was found: There are no rentals yet</p>");
      }
    }
    
    else {
    	out.println("<html><h1>No Permission (username and password are both 'admin')</h1></html>");
    }
    
    out.println("<br><a href='carrental_home.html'>Back to Home</a>");
    out.println("</body></html>");
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {
    doGet(req, res);
  }
}
