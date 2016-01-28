package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.ServiceDBHandler;
import users.User;
import users.UserDBHandler;

public class DeleteServlet extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
	        
	    	HttpSession session = req.getSession();
	        User userSession = (User) session.getAttribute("currentUser");
	        String id = req.getParameter("id");
	        String status = req.getParameter("status");
	        
	        if (userSession!=null) {
	        	try {
	        		if (userSession.getRole().equals("admin")) {
	        			new UserDBHandler().getDb().delete(Integer.parseInt(id));
	        		} else {
	        			new ServiceDBHandler().getDb().delete(Integer.parseInt(id));
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
	        	if (status.equals("need")) {
	            	resp.sendRedirect("besoins");
	            }
	        	if(status.equals("offer")) {
	            	resp.sendRedirect("offres");
	            }
	        	if(status.equals("admin")) {
	            	resp.sendRedirect("admin");
	            }
	        } else {
	        	resp.sendRedirect(req.getContextPath());
	        }
	 }
}
