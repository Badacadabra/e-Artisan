package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import users.User;
import users.UserDBHandler;
/**
 * Servlet de connexion utilisateur.
 * @author Macky Dieng & Baptiste Vannesson.
 */
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        String email =req.getParameter("email");
        String password =req.getParameter("password");
        HttpSession session = req.getSession();
        
        try {
			User user = new UserDBHandler().getDb().retrieve(email);
            session.setAttribute("user",user);
        } catch (Exception e) {
            String msg = "Erreur de connexion avec les infos  email = "+" "+email+"et mdp = "+" "+password;
            req.setAttribute("error",msg);
            this.getServletContext().getRequestDispatcher( "/views/login.jsp" ).forward( req, resp );
        }
        
        resp.sendRedirect("profil");
    }

}
