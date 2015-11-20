package User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A Servlet which reads a list of strings and prints it sorted.
 * @author Bruno Zanuttini, Universit&eacute; de Caen Basse-Normandie, France.
 * @since January, 2012
 */
public class ProfilServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        //HttpSession session = req.getSession();
        /*String email =req.getParameter("email");
        String password =req.getParameter("password");
        Map<String, String> map = new HashMap<String, String>();
        map.put("email", email);
        //String email = request.getAttribute("email");*/
        this.getServletContext().getRequestDispatcher( "/views/profil.jsp" ).forward( req, resp );
     
    }

}
