package servlets;

import graph.Graph;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.Service;
import users.User;
import cycles.CycleDBHandler;

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

	    	HttpSession session = req.getSession();
	    	User userSession = (User) session.getAttribute("user");
	        
	    	if (userSession!=null) {
	    		
	        	List<Couple> currentUserNeeds = new ArrayList<>(); 
	        	List<Couple> usersNeeds = new ArrayList<>();
	        	List<Couple> usersOffers = new ArrayList<>();
	        	Graph<Couple> graph = new Graph<>();
	        	
	        	try {
					// We get all couples users/needs (vertices)
					ResultSet rsNeed = new CycleDBHandler().getDb().retrieveAll("need");
					while(rsNeed.next()) {
						User user = new User(rsNeed.getString(7),rsNeed.getString(8),rsNeed.getString(11),rsNeed.getString(12),rsNeed.getString(13));
						Service service = new Service(rsNeed.getString(2),rsNeed.getString(3),new GregorianCalendar(),new GregorianCalendar(),rsNeed.getString("status"));
						Couple couple = new Couple(user,service);
						usersNeeds.add(couple);
						graph.addVertex(couple);
						if (user.getEmail().equals(userSession.getEmail())) {
							currentUserNeeds.add(couple);
						}
					}
					// We get all couples users/offers (vertices)
					ResultSet rsOffer = new CycleDBHandler().getDb().retrieveAll("offer");
					while(rsOffer.next()) {
						User user = new User(rsOffer.getString(7),rsOffer.getString(8),rsOffer.getString(11),rsOffer.getString(12),rsNeed.getString(13));
						Service service = new Service(rsOffer.getString(2),rsOffer.getString(3),new GregorianCalendar(),new GregorianCalendar(),rsOffer.getString("status"));
					
						Couple couple = new Couple(user,service);
						usersOffers.add(couple);
					}
					// We build an edge between two couples user/service
					// The edge is built when the need of a user corresponds to the offer of another user.
					int i = 0;
					for(Couple userOffer : usersOffers) {
						for(Couple userNeed : usersNeeds) {
							if (userOffer.getService()!=null && userNeed.getService()!=null) {
								if (userOffer.getService().getName().equals(userNeed.getService().getName())) {
									Couple matchedCouple = usersNeeds.get(i);
									if (matchedCouple.getUser().getEmail().equals(userOffer.getUser().getEmail())) {
										System.out.println("User matched : "+matchedCouple);
										//matchedCouple.getService().setName(userOffer.getService().getName());
										graph.addEdge(matchedCouple, userNeed);
									}
								}
							}
						}
						i++;
					}
					// We launch BFS on the graph to find cycles
					Couple currentUserService = currentUserNeeds.get(0);
					try {
						 List<Couple> cycles = graph.bfs(currentUserService);
						 System.out.println(cycles);
						 List<Tuple> cycles2 = new ArrayList<>();
				            for (Couple c : cycles) {
				                //List<Couple> list = graph.getSuccessors(c);
				            	List<Couple> list = graph.getPredecessors(c);
				                for (Couple c2 : list) {
				                    if (cycles.contains(c2)) {
				                        cycles2.add(new Tuple(c.getUser(), c.getService(), c2.getUser()));
				                    }
				                }
				            }
			            req.setAttribute("cycles2", cycles2);
					} catch (NullPointerException e) {
						String error = "Aucun cycle trouvé";
						req.setAttribute("cycleError", error);
					}
				} catch (SQLException e) {
					String error = "Erreur lors de la récupération des données : "+ e.getMessage();
					System.out.println(error);
				} catch (NamingException e) {
					String error = "Erreur lors de la récupération des données : "+ e.getMessage();
					System.out.println(error);
				}
	        	this.getServletContext().getRequestDispatcher( "/views/cycles.jsp" ).forward( req, resp );
	    	} else {
	        	resp.sendRedirect(req.getContextPath());
	        }
	    	
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
            return "Utilisateur : " + user.getFirstName() + " "+ user.getName() + " | Service : " + service.getName();
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
            return "Receiver : " + sender.getFirstName() + " " + sender.getName() + " | Service : " + service.getName() + " | Sender : " + receiver.getFirstName()+" "+ receiver.getName();
        }
        
    }

}