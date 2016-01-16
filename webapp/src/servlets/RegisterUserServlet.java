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
 * 
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
            User user = new User(lastName,firstName,"_none", "_none",email,password);
            new UserDBHandler().getDb().create(user);
            session.setAttribute("user", user);
        } catch (Exception e) {
            String msg = "Erreur d'inscription : vous devez renseigner tous les champs !"+e.getMessage();
            System.out.println(e.getMessage());
            req.setAttribute("errorRegister", msg);
            this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( req, resp );
        }
      
        resp.sendRedirect("accueil");
    }
    
    @Override
    public void destroy() {
        try {
            UserDBHandler.close();
        } catch (SQLException e) {
            this.log("Erreur lors de la cl&ocirc;ture de la connexion SQL ("+e+").");
       }
    }
    
}
