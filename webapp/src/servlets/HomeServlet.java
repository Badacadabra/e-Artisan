package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import users.User;

public class HomeServlet extends HttpServlet {
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user!=null) {
            //req.setAttribute("user",user);
            this.getServletContext().getRequestDispatcher( "/views/home.jsp" ).forward( req, resp );
        } else {
        	resp.sendRedirect("e-artisan");
        }
        
    }

}