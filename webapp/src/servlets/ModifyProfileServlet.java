package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import users.User;
import users.UserDBHandler;

/**
 * A servlet that handles the profile form.
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public class ModifyProfileServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        // String email = req.getParameter("email");
        // String password = req.getParameter("password");
        // HttpSession session = req.getSession();
        // 
        // try {
			// User user = new UserDBHandler().getDb().retrieve(email);
            // session.setAttribute("user", user);
        // } catch (Exception e) {
            // String msg = "";
            // req.setAttribute("error", msg);
            // this.getServletContext().getRequestDispatcher( "/views/profil.jsp" ).forward( req, resp );
        // }
        // 
        // resp.sendRedirect("profil");
    }

}

