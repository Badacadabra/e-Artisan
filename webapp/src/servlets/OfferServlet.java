package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.Service;
import services.ServiceDBHandler;
import users.User;

/**
 * A servlet that handles offers.
 * 
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public class OfferServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
    
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user!=null) {
            try {
        	List<Service> listService = new ServiceDBHandler().getDb().retrieveAll("offer");
        	req.setAttribute("listService", listService);
            } catch (Exception e) {
		// TODO: handle exception
		String error = "Erreur lors de la récupération des données" + e;
		System.out.println(error);
            }        	
            this.getServletContext().getRequestDispatcher( "/views/offres.jsp" ).forward( req, resp ); 	
        } else {
            resp.sendRedirect(req.getContextPath());
        }
        
    }

}
