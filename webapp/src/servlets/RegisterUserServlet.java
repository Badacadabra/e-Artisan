package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import users.User;
import users.UserDBHandler;

/**
 * A servlet that handles the registration form.
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public class RegisterUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        
    	String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        
        try {
        	session.setAttribute("email", email);
			User user = new User(lastName, firstName, email, password);
			new UserDBHandler().getDb().create(user);
        } catch (Exception e) {
            String msg = "Erreur d'inscription : vous devez renseigner tous les champs !";
            req.setAttribute("errorRegister", msg);
            this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( req, resp );
        }
        // Everything went well
        session.setAttribute("userFirstName", firstName);
        session.setAttribute("userEmail", email);
        resp.sendRedirect("accueil");
    }
    /**
     * Terminates the response of this servlet by displaying table of contents and a message.
     * @param request The request for this call
     * @param response The response for this call
     * @param message The message to be forwarded to table of contents
     */
    protected void terminate (HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        // response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/toc.jsp?message="+message));
    }
    @Override
    public void destroy () {
        try {
            UserDBHandler.close();
        } catch (SQLException e) {
            this.log("Erreur lors de la cl&ocirc;ture de la connexion SQL ("+e+").");
       }
    }
}
