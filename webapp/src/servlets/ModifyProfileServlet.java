package servlets;

import java.io.IOException;

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
public class ModifyProfileServlet extends HttpServlet {
    
    /**
     * Directory where uploaded files will be saved, its relative to the web application directory.
     */
    private static final String UPLOAD_DIR = "uploads";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
    	
        HttpSession session = req.getSession();
        User userSession = (User) session.getAttribute("currentUser");
            
        if (userSession!=null) {
        	try {
        		
            	String firstName = req.getParameter("firstName");
                String lastName = req.getParameter("lastName");
                String email = req.getParameter("email");
                String password = req.getParameter("password");
                String description = req.getParameter("description");
                String image = "none";
                String id = req.getParameter("currentUserId");
                
            	User user = new User(Integer.parseInt(id),lastName, firstName, description, image, email, password,"user");
	           	new UserDBHandler().getDb().update(user);
	           	session.setAttribute("currentUser", user);
	           	this.getServletContext().getRequestDispatcher( "/views/profil.jsp" ).forward( req, resp );
	           	
            } catch (Exception e) {
                String msg = e.getLocalizedMessage();
                req.setAttribute("error", msg);
                this.getServletContext().getRequestDispatcher( "/views/profil.jsp" ).forward( req, resp );
            }
        } else {
            resp.sendRedirect(req.getContextPath());
        }
    }
}
