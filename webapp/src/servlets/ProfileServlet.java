package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import users.User;
import users.UserDBHandler;

/**
 * A servlet that handles profile page.
 * 
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        
    	HttpSession session = req.getSession();
        User userSession = (User) session.getAttribute("currentUser");
        String id = req.getParameter("id");
        
        if (userSession!=null) {
        	try {
				User user = new UserDBHandler().getDb().retrieve(Integer.parseInt(id));
				req.setAttribute("user", user);
			} catch (SQLException e) {
				String error = "Erreur survenue lors de la récupération des données"+e.getMessage();
				req.setAttribute("error", error);
				System.out.println(error);
			} catch (NamingException e) {
				String error = "Erreur survenue lors de la récupération des données"+e.getMessage();
				req.setAttribute("error", error);
				System.out.println(error);
			}
        	this.getServletContext().getRequestDispatcher( "/views/profil.jsp" ).forward( req, resp );
            
        } else {
        	resp.sendRedirect(req.getContextPath());
        }
        
    }

}
