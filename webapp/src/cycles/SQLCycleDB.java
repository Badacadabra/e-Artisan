package cycles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import services.ServiceDBHandler;

/**
 * A class for SQL storage of services in a database.
 * The id of the service is taken to be its primary key.
 * 
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public class SQLCycleDB {
    
    /** A link to the database. */
    protected Connection link;
    /*************Retrieve a couple*****************/
    private PreparedStatement retrieveCouplesStatement;
    /*************Retrieve a couple*****************/
    private PreparedStatement retrieveAllCouplesNeedsStatement;
    /*************Retrieve all user couple*****************/
    private PreparedStatement retrieveAllCouplesOffersStatement;
    /*************Retrieve all user couple*****************/
    private PreparedStatement retrieveUserCouplesStatement;
    /*************Retrieve a couple*****************/
    private PreparedStatement createCoupleStatement;
    /*************Remove a couple*****************/
    private PreparedStatement removeCoupleStatement;
    /**
     * Builds a new instance.
     * 
     * @param link An active connection to an SQL database
     * @param table The name of the table where to store Services
     * @throws SQLException if a database access error occurs
     */
    public SQLCycleDB(Connection link) throws SQLException {
        this.link = link;
        String query = null;
        query = "INSERT INTO `user_services` SET user_id = ?, service_id = ?";
        this.createCoupleStatement =this.link.prepareStatement(query);
        query = "SELECT services.* FROM `services`, `users`, `user_services` WHERE users.id = "+
        		"user_services.user_id AND services.id = user_services.service_id AND services.status = ? AND users.id = ?";
        this.retrieveCouplesStatement = this.link.prepareStatement(query);
        query = "SELECT services.* FROM `services`, `users`, `user_services` WHERE users.id = "+
        		"user_services.user_id AND services.id = user_services.service_id AND users.id = ?";
        this.retrieveUserCouplesStatement = this.link.prepareStatement(query);
        query = "SELECT services.*, users.* FROM `services`, `users`, `user_services` WHERE users.id = "+
        		"user_services.user_id AND services.id = user_services.service_id AND services.status = ?";
        this.retrieveAllCouplesNeedsStatement = this.link.prepareStatement(query);
        query = "SELECT services.*, users.* FROM `services`, `users`, `user_services` WHERE users.id = "+
        		"user_services.user_id AND services.id = user_services.service_id AND services.status = ?";
        this.retrieveAllCouplesOffersStatement = this.link.prepareStatement(query);
        query = "DELETE FROM `user_services` WHERE user_id = ? AND service_id = ?";
        this.removeCoupleStatement = this.link.prepareStatement(query);
        
    }
    /**
     * Resets the link to the database.
     * This method can be used in case the connection breaks down.
     * 
     * @param link An active link to the database
     */
    public void setLink(Connection link) {
        this.link=link;
    }

    /**
     * Creates the necessary table in the database. Nothing occurs if the table already exists.
     * 
     * @throws SQLException if a database access error occurs
     */
    public void createTables() throws SQLException {
        String query="CREATE TABLE IF NOT EXISTS `user_services` (";
        query += "`user_id` int(255) NOT NULL, ";
        query +="`service_id` int(255) NOT NULL";
        query += ")";
        Statement statement = this.link.createStatement();
        statement.execute(query);
    }

    /**
     * Stores a new service in the database.
     * 
     * @param service The service to store
     * @throws SQLException if a database access error occurs
     */
    public void create(int userId,int serviceId) throws SQLException {
        this.createCoupleStatement.setInt(1,userId);
        this.createCoupleStatement.setInt(2,serviceId);
        this.createCoupleStatement.execute();
    }

    /**
     * Retrieves all services in the database.
     * 
     * @return A list of all services in the database
     * @throws SQLException if a database access error occurs
     */
    public ResultSet retrieveAll(int userId,String status) throws SQLException {
    	this.retrieveCouplesStatement.setString(1,status);
    	this.retrieveCouplesStatement.setInt(2,userId);
        ResultSet rs = this.retrieveCouplesStatement.executeQuery();
        return rs;
    }
    /**
     * Retrieves all services in the database.
     * 
     * @return A list of all services in the database
     * @throws SQLException if a database access error occurs
     */
    public ResultSet retrieveAllNeeds(String status) throws SQLException {
    	this.retrieveAllCouplesNeedsStatement.setString(1,status);
        ResultSet rs = this.retrieveAllCouplesNeedsStatement.executeQuery();
        return rs;
    }
    /**
     * Retrieves all services in the database.
     * 
     * @return A list of all services in the database
     * @throws SQLException if a database access error occurs
     */
    public ResultSet retrieveAllOffers(String status) throws SQLException {
    	this.retrieveAllCouplesOffersStatement.setString(1,status);
        ResultSet rs = this.retrieveAllCouplesOffersStatement.executeQuery();
        return rs;
    }
    /**
     * Retrieves all services in the database.
     * 
     * @return A list of all services in the database
     * @throws SQLException if a database access error occurs
     */
    public ResultSet retrieveUserServices(int userId) throws SQLException {
    	this.retrieveUserCouplesStatement.setInt(1,userId);
        ResultSet rs = this.retrieveUserCouplesStatement.executeQuery();
        return rs;
    }
    /**
     * Remove user's couple
     * 
     */
    public void remove(int userId,int serviceId) throws SQLException {
    	this.removeCoupleStatement.setInt(1,userId);
    	this.removeCoupleStatement.setInt(2,serviceId);
        this.removeCoupleStatement.execute();
    }
    /**
     * Remove all user's couples
     * 
     * @return void
     */
    public void removeAllUserCouples(int userId) throws SQLException {
    	try {
    		ResultSet rs = this.retrieveUserServices(userId);
    		System.out.println(rs.getInt("id"));
    		while(rs.next()) {
				new ServiceDBHandler().getDb().delete(rs.getInt("id"));
				this.remove(userId,rs.getInt("id"));
    		}
		} catch (Exception e) {
			System.out.println("Erreur lors de la suppression des services"+e.getMessage());
		}
    }   
}