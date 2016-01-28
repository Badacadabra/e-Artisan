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
        String mode = req.getParameter("mode");
        String id = req.getParameter("currentUserId");
        
        HttpSession session = req.getSession();
        User userSession = (User) session.getAttribute("currentUser");
        
        try {
        	User user = null;
            if (userSession!=null) { //Here the register request come from the admin zone
            	if (userSession.getRole().equals("admin")){
            		if (mode!=null && mode.equals("insert")) {
            			user = new User(lastName,firstName,email,password,"user");
            			new UserDBHandler().getDb().create(user);
            		} else {
            			User tmpUser = new UserDBHandler().getDb().retrieve(Integer.parseInt(id));
            			user = new User(tmpUser.getId(),lastName,firstName,tmpUser.getDescription(),tmpUser.getImage(),email,password,"user");
            			new UserDBHandler().getDb().update(user);
            		}
 	            	resp.sendRedirect("admin");
 	            } else {
 	            	resp.sendRedirect(req.getContextPath());
 	            }
            } else { //Here the register request come from the public zone
            	user = new User(lastName,firstName,email,password,"user");
            	int userId = new UserDBHandler().getDb().create(user);
                User currentUser =  new User(userId,lastName,firstName,null,null,email,password,"user");
            	session.setAttribute("currentUser", currentUser);
            	resp.sendRedirect("accueil");
            }
        } catch (Exception e) {
            String msg = "Erreur d'inscription : vous devez renseigner tous les champs !"+e.getMessage();
            System.out.println(e.getMessage());
            req.setAttribute("errorRegister", msg);
            this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( req, resp );
        }
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
