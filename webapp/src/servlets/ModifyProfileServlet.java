package servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import users.User;
import users.UserDBHandler;

/**
 * A servlet that handles the profile form.
 *
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public class ModifyProfileServlet extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "upload/";
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
	
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        HttpSession session = req.getSession();
        User userSession = (User) session.getAttribute("currentUser");
        req.setCharacterEncoding("UTF-8");
        
        String firstName = "";
        String lastName = "";
        String email = "";
        String password = "";
        String description = "";

        if (userSession!=null) {
        	
        	// Configuration of file upload
     
            DiskFileItemFactory factory = new DiskFileItemFactory();
            
            factory.setSizeThreshold(MEMORY_THRESHOLD);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
            
            ServletFileUpload upload = new ServletFileUpload(factory);
            
            upload.setFileSizeMax(MAX_FILE_SIZE);
            upload.setSizeMax(MAX_REQUEST_SIZE);
     
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
             
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
        	
            try {
                List<FileItem> formItems = upload.parseRequest(req);
                System.out.println(formItems);
     
                String fileName = "";
                String filePath = "";
                if (formItems != null && formItems.size() > 0) {
                    for (FileItem item : formItems) {
                        if (!item.isFormField() && item.getSize() > 0) {
                            fileName = new File(item.getName()).getName();                          
                            filePath = uploadPath + File.separator + fileName;
                            File storeFile = new File(filePath);
     
                            item.write(storeFile);
                        } else {
                            switch (item.getFieldName()) {
                            	case "firstName":
                            		firstName = item.getString();
                            		break;
                            	case "lastName":
                            		lastName = item.getString();
                            		break;
                            	case "email":
                            		email = item.getString();
                            		break;
                            	case "password":
                            		password = item.getString();
                            		break;
                            	case "description":
                            		description = item.getString();
                            		break;
                            }
                        }
                    }
                }
                
                String image = userSession.getImage();
                if (!fileName.equals("")) {
                	image = fileName;
                }
                
                int id = userSession.getId();
                String role = "user";
                if (userSession.getRole().equals("admin"))
                    role = "admin";
                User user = new User(id, lastName, firstName, description, image, email, password, role);
                new UserDBHandler().getDb().update(user);
                
                session.setAttribute("currentUser", user);
                resp.sendRedirect("profil?id="+id);

            } catch (Exception e) {
                String msg = e.getMessage();
                req.setAttribute("error", msg);
                this.getServletContext().getRequestDispatcher( "/views/profil.jsp" ).forward( req, resp );
            }
        } else {
            resp.sendRedirect(req.getContextPath());
        }
    }
}
