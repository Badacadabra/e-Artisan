package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import users.User;
/**
 * A Servlet which reads a list of strings and prints it sorted.
 * @author Bruno Zanuttini, Universit&eacute; de Caen Basse-Normandie, France.
 * @since January, 2012
 */
public class ProfilServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
    	HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user!=null) {
            this.getServletContext().getRequestDispatcher( "/views/profil.jsp" ).forward( req, resp );
        } else {
        	resp.sendRedirect(req.getContextPath());
        }
        
    }

}
