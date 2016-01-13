package servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;
import java.io.File;

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
@WebServlet("/ModifyProfileServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*10,    // 10 MB 
                 maxFileSize=1024*1024*50,          // 50 MB
                 maxRequestSize=1024*1024*100)      // 100 MB

public class ModifyProfileServlet extends HttpServlet {
    
	/**
     * Directory where uploaded files will be saved, its relative to
     * the web application directory.
     */
    private static final String UPLOAD_DIR = "uploads";
    
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
    	
        HttpSession session = req.getSession();
        User userSession = (User) session.getAttribute("user");
        
        
        if (userSession!=null) {
        	try {
        		/* gets absolute path of the web application
                String applicationPath = req.getServletContext().getRealPath("");
                // constructs path of the directory to save uploaded file
                String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
                  
                // creates the save directory if it does not exists
                File fileSaveDir = new File(uploadFilePath);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdirs();
                }
                System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());
                System.out.println(uploadFilePath);
                
                String fileName = null;
                //Get all the parts from request and write it to the file on server
                for (Part part : req.getParts()) {
                    fileName = getFileName(part);
                    part.write(uploadFilePath + File.separator + fileName);
                }
                System.out.println(fileName + " File uploaded successfully!");
                
                //req.setAttribute("message", fileName + " File uploaded successfully!");*/
        		req.setCharacterEncoding("UTF-8");
            	String firstName = req.getParameter("firstName");
                String lastName = req.getParameter("lastName");
                String email = req.getParameter("email");
                String password = req.getParameter("password");
                String description = req.getParameter("description");
                String image = "none";
           	 	User user = new User(lastName, firstName, description, image, email, password);
           	 	new UserDBHandler().getDb().update(user, userSession.getEmail());
           	 	//session.invalidate();
           	 	session.setAttribute("user", user);
           	 	this.getServletContext().getRequestDispatcher( "/views/profil.jsp" ).forward( req, resp );
            } catch (Exception e) {
                String msg = e.getLocalizedMessage();
                req.setAttribute("error", msg);
                this.getServletContext().getRequestDispatcher( "/views/profil.jsp" ).forward( req, resp );
            }
        } else {
        	resp.sendRedirect("e-artisan");
        }
    }/**
     * Méthode permettant de récupérer le nom de l'image uploader
     
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        //System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }*/

}

