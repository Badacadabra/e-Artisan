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
 * A Servlet which reads a list of strings and prints it sorted.
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public class RegisterUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        
    	String fname =req.getParameter("firstName");
        String lname =req.getParameter("lastName");
        String email =req.getParameter("email");
        String pwd = req.getParameter("password");
        HttpSession session = req.getSession();
        String error = "Default";
        try {
        	session.setAttribute("email",email);
			User user = new User(lname,fname,email,pwd);
			new UserDBHandler().getDb().create(user);
			resp.sendRedirect("login");
        } catch (Exception e) {
            // this.terminate(req,resp,"Erreur d'inscription, vous devez renseignez tous les champs");
            //return;
            error = "Erreur d'inscription, vous devez renseignez tous les champs" + e;
            req.setAttribute("error",error);
            this.getServletContext().getRequestDispatcher( "/views/signup.jsp" ).forward( req, resp );
            // this.getServletContext().getRequestDispatcher( "/views/signup.jsp" ).forward( req, resp );
        }
        // Everything went well
        String message = "Nous avons bien pris en compte le nouveau utilisateur, merci.";
        req.setAttribute("message",message);
        this.getServletContext().getRequestDispatcher( "/views/signup.jsp" ).forward( req, resp );
        // this.terminate(req,resp,"Nous avons bien pris en compte le nouveau utilisateur, merci.");
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
