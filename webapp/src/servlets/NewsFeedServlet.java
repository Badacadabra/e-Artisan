package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import users.User;

/**
 * A servlet that handles the news feed.
 *
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public class NewsFeedServlet extends HttpServlet {

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
            this.getServletContext().getRequestDispatcher( "/views/actus.jsp" ).forward( req, resp );
        } else {
            resp.sendRedirect(req.getContextPath());
        }

    }

}
