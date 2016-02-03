package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cycles.CycleDBHandler;
import services.Service;
import services.ServiceDBHandler;
import users.User;

/**
 * A servlet that handles needs.
 *
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public class NeedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("currentUser");
        req.setCharacterEncoding("UTF-8");

        if (user!=null) {
            if (!user.getRole().equals("admin")) {
                req.setAttribute("accessDenied", "Connexion refusée");
            }
            try {
                List<Service> listService = new ArrayList<>(); //getDb().retrieveAll("need");
                ResultSet rsNeed = new CycleDBHandler().getDb().retrieveAll(user.getId(),"need");
                while(rsNeed.next()) {
                    Service service = new Service(rsNeed.getInt(1),rsNeed.getString(2),rsNeed.getString(3),new GregorianCalendar(),new GregorianCalendar(),rsNeed.getString("status"));
                    listService.add(service);
                }
                req.setAttribute("listService", listService);
            } catch (Exception e) {
                String error = "Erreur lors de la récupération des données" + e;
                System.out.println(error);
            }
            this.getServletContext().getRequestDispatcher( "/views/besoins.jsp" ).forward( req, resp );
        } else {
            resp.sendRedirect(req.getContextPath());
        }

    }

}
