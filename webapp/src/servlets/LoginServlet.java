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
 * A servlet that handles the login form.
 * 
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        String email = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        
        try {
            if (new UserDBHandler().getDb().isValidUser(email, password)) {
        	User user = new UserDBHandler().getDb().retrieve(email);
                session.setAttribute("user", user);
            }
        } catch (Exception e) {
            this.performError(req,resp,e);
        }
        resp.sendRedirect("accueil");
    }
    
    private void performError(HttpServletRequest req,HttpServletResponse resp,Exception e) 
    		throws ServletException, IOException {
    	String msg = "Connexion refusée. Ce compte n'existe pas dans notre base de données."+e.getMessage();
        req.setAttribute("errorLogin", msg);
        this.getServletContext().getRequestDispatcher(req.getContextPath()).forward( req, resp );
    }

}
