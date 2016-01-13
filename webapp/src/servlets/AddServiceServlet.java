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
import users.User;

/**
 * A servlet which adds a service. 
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public class AddServiceServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
       
    	// We get parameters from query
    	//String id = req.getParameter("id");
    	HttpSession session = req.getSession();
		User userSession = (User) session.getAttribute("user");
        
        if (userSession!=null) {
        	String name = req.getParameter("name");
            String description = req.getParameter("description");
            String publicationDate = req.getParameter("publicationDate");
            String deadline = req.getParameter("deadline");
            String status = req.getParameter("needOrOffer");
            
            // Conversion from strings to dates
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            
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
            
            
            
            String error = "";
            try {
    			Service Service = new Service(name, description, calPublication, calDeadline, status);
    			new ServiceDBHandler().getDb().create(Service);
    			
            } catch (Exception e) {
                error = "Erreur dans l'ajout du service." + e;
                req.setAttribute("error", error);
                if (status.equals("need")) {
                    this.getServletContext().getRequestDispatcher( "/views/besoins.jsp" ).forward( req, resp );
                } else {
                    this.getServletContext().getRequestDispatcher( "/views/offres.jsp" ).forward( req, resp );
                }
            }
            // If everything went well
            String message = "Service créé avec succès !";
            req.setAttribute("message", message);
            if (status.equals("need")) {
            	resp.sendRedirect("besoins");
            } else {
            	resp.sendRedirect("offres");
            }
            	
            
        } else {
        	resp.sendRedirect("e-artisan");
        }
        
    }

    @Override
    public void destroy () {
        try {
            ServiceDBHandler.close();
        } catch (SQLException e) {
            this.log("Erreur lors de la cl&ocirc;ture de la connexion SQL ("+e+").");
       }
    }
}

