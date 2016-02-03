package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cycles.CycleDBHandler;
import services.ServiceDBHandler;
import users.User;
import users.UserDBHandler;

public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

            HttpSession session = req.getSession();
            User userSession = (User) session.getAttribute("currentUser"); //current user in the session
            req.setCharacterEncoding("UTF-8");
            int id = Integer.parseInt(req.getParameter("id")); //id of user or service depending to the page
            String status = req.getParameter("status"); //Page status (needs page, offers or admin)

            if (userSession!=null) {
                try {
                    if (userSession.getRole().equals("admin")) {
                        //Deleting services and users by the admin
                        if (status.equals("need") || status.equals("offer")) {
                            new ServiceDBHandler().getDb().delete(id);
                            new CycleDBHandler().getDb().remove(userSession.getId(),id);
                        }
                        else { //Deleting user and all his services by the admin
                            new UserDBHandler().getDb().delete(id);
                            new CycleDBHandler().getDb().removeAllUserCouples(id);
                        }
                    } else { //Deleting services by standard user
                        new ServiceDBHandler().getDb().delete(id);
                        new CycleDBHandler().getDb().remove(userSession.getId(),id);
                    }
                } catch (SQLException e) {
                    String error = "Erreur survenue lors de la suppression"+e.getMessage();
                    System.out.println(error);
                    req.setAttribute("error", error);
                } catch (NamingException e) {
                    String error = "Erreur survenue lors de la suppression"+e.getMessage();
                    System.out.println(error);
                    req.setAttribute("error", error);
                }
                //If everything went well we redirect on the correct route by the status
                if (status.equals("need")) {
                    resp.sendRedirect("besoins");
                }
                if(status.equals("offer")) {
                    resp.sendRedirect("offres");
                }
                if(status.equals("admin")) {
                    resp.sendRedirect("admin");
                }
            } else { //Anonymous user so we redirect him to the homepage
                resp.sendRedirect(req.getContextPath());
            }
     }
}
