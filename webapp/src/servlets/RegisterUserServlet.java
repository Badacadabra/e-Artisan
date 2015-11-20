package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * A Servlet which reads a list of strings and prints it sorted.
 * @author Bruno Zanuttini, Universit&eacute; de Caen Basse-Normandie, France.
 * @since January, 2012
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
        //session.setAttribute("email",email);
        Map<String, String> map = new HashMap<String, String>();
        //map.put("fname", "fdemo");
        //map.get("fname");
        
        if(email != null &&  pwd != null && lname!=null && fname!= null){
            //map.put("fname",fname);
            //map.put("lname",lname);
           // map.put("email",email);
            //map.put("pwd",pwd);
            session.setAttribute("email",email);
            resp.sendRedirect("login");
        } else {
            String error = "Erreur d'inscription, vous devez renseignez tous les champs";
            req.setAttribute("error",error);
            this.getServletContext().getRequestDispatcher( "/views/signup.jsp" ).forward( req, resp );
        }
    }

}
