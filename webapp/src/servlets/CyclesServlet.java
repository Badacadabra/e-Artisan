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
            User userSession = (User) session.getAttribute("currentUser");
            req.setCharacterEncoding("UTF-8");

            if (userSession!=null) {

                if (!userSession.getRole().equals("admin")) {
                    req.setAttribute("accessDenied", "Connexion refusée");
                }

                List<Couple> currentUserNeeds = new ArrayList<>();
                List<Couple> usersNeeds = new ArrayList<>();
                List<Couple> usersOffers = new ArrayList<>();
                Graph<Couple> graph = new Graph<>();
                ResultSet rs = null;
                try {
                    // We get all couples users/needs (vertices)
                    rs = new CycleDBHandler().getDb().retrieveAllNeeds("need");
                    //CycleDBHandler.close();
                    //System.out.println(rsNeed.next());
                    System.out.println("need");
                    while(rs.next()) {
                        User user = new User(rs.getString(8),rs.getString(9),rs.getString(12),rs.getString(13),rs.getString(14));
                        Service service = new Service(rs.getString(2),rs.getString(3),new GregorianCalendar(),new GregorianCalendar(),rs.getString("status"));
                        Couple couple = new Couple(user,service);
                        usersNeeds.add(couple);
                        graph.addVertex(couple);
                        if (user.getEmail().equals(userSession.getEmail())) {
                            currentUserNeeds.add(couple);
                        }
                    }
                    rs.close();
                    // We get all couples users/offers (vertices)
                    rs = new CycleDBHandler().getDb().retrieveAllOffers("offer");
                    System.out.println("offer");
                    while(rs.next()) {
                        User user = new User(rs.getString(8),rs.getString(9),rs.getString(12),rs.getString(13),rs.getString(14));
                        Service service = new Service(rs.getString(2),rs.getString(3),new GregorianCalendar(),new GregorianCalendar(),rs.getString("status"));

                        Couple couple = new Couple(user,service);
                        usersOffers.add(couple);
                    }
                    System.out.println("after offer");
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
                    System.out.println("after couple");
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
                            System.out.println("after cycle");
                        req.setAttribute("cycles2", cycles2);
                    } catch (NullPointerException e) {
                        String error = "Aucun cycle trouvé";
                        req.setAttribute("cycleError", error);
                    }
                } catch (Exception e) {
                    String error = "Erreur lors de la récupération des données : "+ e.getMessage();
                    System.out.println(error);
                } finally{
                   try{
                       if (rs != null)
                            rs.close();
                       }catch(SQLException e){}
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
