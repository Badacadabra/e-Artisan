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
        String email = (String) session.getAttribute("currentUserEmail");
        String password = (String) session.getAttribute("currentUserPwd");
        String info = (String) session.getAttribute("info");
        User user = (User) session.getAttribute("user");
        //req.setAttribute("email",email);
        //req.setAttribute("password",password);
        System.out.println(user);
        if (info !=null) {
             req.setAttribute("info",info);
             session.removeAttribute("info");
        }
        if (user!=null) {
            req.setAttribute("user",user);
            this.getServletContext().getRequestDispatcher( "/views/profil.jsp" ).forward( req, resp );
        } else {
            String error = "Vous avez pas le droit d'accéder à cette page.";
            req.setAttribute("error",error);
            this.getServletContext().getRequestDispatcher( "/views/profil.jsp" ).forward( req, resp );
        }
        
    }

}
