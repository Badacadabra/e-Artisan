package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import graph.Graph;
import services.Service;
import users.User;

/**
 * A servlet that handles cycles.
 * 
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public class CyclesServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

            User user1 = new User("User1", "User1", "user1@gmail.com", "password");
            Service service1 = new Service("Service1", "description", new GregorianCalendar(), new GregorianCalendar(), "status");
            Couple userService1 = new Couple(user1, service1);
            
            User user2 = new User("User2", "User2", "user2@gmail.com", "password");
            Service service2 = new Service("Service2", "description", new GregorianCalendar(), new GregorianCalendar(), "status");
            Couple userService2 = new Couple(user2, service2);
            
            User user3 = new User("User3", "User3", "user3@gmail.com", "password");
            Service service3 = new Service("Service3", "description", new GregorianCalendar(), new GregorianCalendar(), "status");
            Couple userService3 = new Couple(user3, service3);
            
            User user4 = new User("User4", "User4", "user4@gmail.com", "password");
            Service service4 = new Service("Service4", "description", new GregorianCalendar(), new GregorianCalendar(), "status");
            Couple userService4 = new Couple(user4, service4);
            
            User user5 = new User("User5", "User5", "user5@gmail.com", "password");
            Service service5 = new Service("Service5", "description", new GregorianCalendar(), new GregorianCalendar(), "status");
            Couple userService5 = new Couple(user5, service5);
            
            Graph<Couple> graph = new Graph<>();
            
            graph.addVertex(userService1);
            graph.addVertex(userService2);
            graph.addVertex(userService3);
            graph.addVertex(userService4);
            graph.addVertex(userService5);
            
            graph.addEdge(userService1, userService2);
            graph.addEdge(userService2, userService3);
            graph.addEdge(userService3, userService4);
            graph.addEdge(userService4, userService5);
            graph.addEdge(userService5, userService1);
            
            List<Couple> cycles = graph.bfs(userService1);
            List<Tuple> cycles2 = new ArrayList<>();
            
            for (Couple c : cycles) {
                List<Couple> list = graph.getSuccessors(c);
                for (Couple c2 : list) {
                    if (cycles.contains(c2)) {
                        cycles2.add(new Tuple(c.getUser(), c.getService(), c2.getUser()));
                    }
                }
            }
            
            req.setAttribute("cycles2", cycles2);
            
            this.getServletContext().getRequestDispatcher( "/views/cycles.jsp" ).forward( req, resp );
        
    }
    
    public static class Couple {
        
        User user;
        Service service;
        
        public Couple(User user, Service service) {
            this.user = user;
            this.service = service;
        }
        
        public User getUser() {
            return user;
        }
        
        public Service getService() {
            return service;
        }
        
        public String toString() {
            return "Prestataire : " + user.getName() + " | Service : " + service.getName();
        }
        
    }
    
    public static class Tuple {
        
        User sender;
        Service service;
        User receiver;
        
        public Tuple(User sender, Service service, User receiver) {
            this.sender = sender;
            this.service = service;
            this.receiver = receiver;
        }
        
        public User getSender() {
            return sender;
        }
        
        public Service getService() {
            return service;
        }
        
        public User getReceiver() {
            return receiver;
        }
        
        public String toString() {
            return "Prestataire : " + sender.getName() + " | Service : " + service.getName() + " | Receiver : " + receiver.getName();
        }
        
    }

}