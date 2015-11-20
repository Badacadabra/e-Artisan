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
        
        
    	String fname =req.getParameter("prenom");
        String lname =req.getParameter("nom");
        String email =req.getParameter("email");
        String pwd =req.getParameter("password");
        HttpSession session = req.getSession();
        String error = "";
        /*try {
        	//if(email != null &&  pwd != null && lname!=null && fname!= null){
                session.setAttribute("email",email);
                //resp.sendRedirect("login");
            } else {
            	error = "Erreur d'inscription, vous devez renseignez tous les champs";
                req.setAttribute("error",error);
                this.getServletContext().getRequestDispatcher( "/views/signup.jsp" ).forward( req, resp );
            }
        } catch (Exception e) {
            //this.terminate(req,resp,"Erreur d'inscription, vous devez renseignez tous les champs");
            //return;
        	req.setAttribute("error",error);
            this.getServletContext().getRequestDispatcher( "/views/signup.jsp" ).forward( req, resp );
        }*/
        // Insert product into DB
        User user = new User(lname,fname,email,pwd);
        try {
            new UserDBHandler().getDb().create(user);
            resp.sendRedirect("login");
        } catch (Exception e) {
        	req.setAttribute("error",error);
            this.getServletContext().getRequestDispatcher( "/views/signup.jsp" ).forward( req, resp );
        }
        // Everything went well
        this.terminate(req,resp,"Nous avons bien pris en compte le nouveau utilisateur, merci.");
    }
    /**
     * Terminates the response of this servlet by displaying table of contents and a message.
     * @param request The request for this call
     * @param response The response for this call
     * @param message The message to be forwarded to table of contents
     */
    protected void terminate (HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/toc.jsp?message="+message));
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
