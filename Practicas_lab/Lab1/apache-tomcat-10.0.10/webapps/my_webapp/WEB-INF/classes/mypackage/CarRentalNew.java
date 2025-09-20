package mypackage;


import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import netscape.javascript.JSObject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Iterator;

public class CarRentalNew extends HttpServlet {

  public void saveRental(PrintWriter out, String rating, String days, String discount, String engine, String units) {
    String path = "/WEB-INF/classes/mypackage/rentals.json";
    File file = new File(getServletContext().getRealPath(path));

    out.println("<p>Writing in: " + file.getAbsolutePath() + "</p>");

    JSONObject obj = null;
    JSONArray array = null;

    if (!file.exists()) {
      out.println("<p>Creating new JSON file</p>");
      obj = new JSONObject();
			array = new JSONArray();
      obj.put("rentals", array);
    }
    else {
      JSONParser parser = new JSONParser();
      try {
        out.println("<p>JSON existst, updating file</p>");
				obj = (JSONObject) parser.parse(new FileReader(file));
				array = (JSONArray) obj.get("rentals");
			} catch (Exception e) {
				out.println("<p>Error on reading JSON file: " + e.getMessage() + "</p>");
				e.printStackTrace(new PrintWriter(out));
				return;
			}
    }

    JSONObject newRental = new JSONObject();

    newRental.put("rating", rating);
		newRental.put("engine", engine);
		newRental.put("rental_days", days);
		newRental.put("units", units);
		newRental.put("discount", discount);

		array.add(newRental);

    try {
      FileWriter fileWriter = new FileWriter(file);
      fileWriter.write(obj.toJSONString());
			fileWriter.flush();
			out.println("<p>The JSON file was updated</p>");
    } catch(IOException e) {
      out.println("<p>Error on writing on JSON file: " + e.getMessage() + "</p>");
				e.printStackTrace(new PrintWriter(out));
				return;
    }

  }

  public void doGet(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {

    res.setContentType("text/html");
    PrintWriter out = res.getWriter();

    String rating = req.getParameter("co2_rating");
    String days = req.getParameter("dies_lloguer");
    String discount = req.getParameter("descompte");
    String engine = req.getParameter("sub_model_vehicle");
    String units = req.getParameter("num_vehicles");
    

    boolean error = false;

    if (rating.equals("54")) rating = rating + " (ExtraLow)"; 
    else if (rating.equals("71")) rating = rating + " (Low)"; 
    else if (rating.equals("82")) rating = rating + " (Medium)"; 
    else if (rating.equals("139")) rating = rating + " (High)"; 
    else error = true;

    try {
      if (Integer.valueOf(units) <= 0) error = true;
      if (Integer.valueOf(days) <= 0) error = true;
      if (Float.valueOf(discount) > 100.f) error = true;
    } catch(NumberFormatException e) {
      error = true;
    }

    if (!error) {
      out.println("<html>" +
      "<head><title>Rental Details</title></head>" +
      "<body>" +
        "<h1>Car Rental Details</h1>" +
        "<p>CO2 Rating: " + rating + "</p>" +
        "<p>Engine: " + engine + "</p>" +
        "<p>Days of Rental: " + days + "</p>" +
        "<p>Units: " + units + "</p>" +
        "<p>Discount: " + discount + "</p>" +
        "<hr/>" +
        "<br><br><a href='carrental_home.html'>Home</a>" +
      "</body>" +
      "</html>");

      saveRental(out, rating, days, discount, engine, units);
    }

    else {
      out.println("<html>" +
      "<head><title>Error</title></head>" +
      "<body>" +
        "<h1>An incorrect value was introduced</h1><br>" +
        "<hr/>" +
        "<br><br><a href='carrental_home.html'>Home</a>" +
      "</body>" +
      "</html>");
    }
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {
    doGet(req, res);
  }
}
