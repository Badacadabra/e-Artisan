package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import users.User;
import users.UserDBHandler;

/**
 * A servlet that handles the profile form.
 * 
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */

public class AdminServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
    	
        HttpSession session = req.getSession();
        User userSession = (User) session.getAttribute("user");
            
        if (userSession!=null) {
        	try {
        		List<User> users = new UserDBHandler().getDb().retrieveAll();
        		
        		req.setAttribute("users", users);
           	 	this.getServletContext().getRequestDispatcher( "/views/admin.jsp" ).forward( req, resp );
           	 	
            } catch (Exception e) {
                String msg = e.getMessage();
                req.setAttribute("error", msg);
                this.getServletContext().getRequestDispatcher( "/views/admin.jsp" ).forward( req, resp );
            }
        } else {
            resp.sendRedirect(req.getContextPath());
        }
    }
}
