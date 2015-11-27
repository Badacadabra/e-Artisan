package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.Service;
import services.ServiceDBHandler;

/**
 * A servlet which register a service. 
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public class RegisterServiceServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
       
    	// We get parameters from query
    	String id = req.getParameter("id");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String publicationDate = req.getParameter("publicationDate");
        String deadline = req.getParameter("deadline");
        
        // Conversion from strings to dates
        DateFormat df = new SimpleDateFormat("dd MM yyyy");
        
        Date date1, date2;
        GregorianCalendar calPublication = null;
        GregorianCalendar calDeadline = null;
		try {
			// Date 1
			date1 = df.parse(publicationDate);
			calPublication = new GregorianCalendar(); 
			calPublication.setTime(date1);
			// Date 2
	        date2 = df.parse(deadline);
	        calDeadline = new GregorianCalendar();
	        calDeadline.setTime(date2);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
        
        HttpSession session = req.getSession();
        
        String error = "";
        try {
			Service Service = new Service(name, description, calPublication, calDeadline);
			new ServiceDBHandler().getDb().create(Service);
			
        } catch (Exception e) {
            error = "Erreur dans l'ajout du service." + e;
            req.setAttribute("error",error);
            this.getServletContext().getRequestDispatcher( "/views/addService.jsp" ).forward( req, resp );
        }
        // If everything went well
        String message = "Service créé avec succès !";
        req.setAttribute("message", message);
        resp.sendRedirect("serviceDetails");
    }

    /**
     * Terminates the response of this servlet by displaying table of contents and a message.
     * @param request The request for this call
     * @param response The response for this call
     * @param message The message to be forwarded to table of contents
     */
    @Override
    public void destroy () {
        try {
            ServiceDBHandler.close();
        } catch (SQLException e) {
            this.log("Erreur lors de la cl&ocirc;ture de la connexion SQL ("+e+").");
       }
    }
}

