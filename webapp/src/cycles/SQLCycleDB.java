package cycles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    private PreparedStatement retrieveAllCouplesStatement;
    /*************Retrieve a couple*****************/
    private PreparedStatement createCoupleStatement;
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
        query = "SELECT services.* FROM `services`, `users`, `user_services` WHERE users.email = "+
        		"user_services.user_id AND services.id = user_services.service_id AND services.status = ? AND users.email = ?";
        this.retrieveCouplesStatement = this.link.prepareStatement(query);
        query = "SELECT services.*, users.* FROM `services`, `users`, `user_services` WHERE users.email = "+
        		"user_services.user_id AND services.id = user_services.service_id AND services.status = ?";
        this.retrieveAllCouplesStatement = this.link.prepareStatement(query);
        
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
        query += "`user_id` VARCHAR(100) NOT NULL, ";
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
    public void create(String userId,int serviceId) throws SQLException {
        this.createCoupleStatement.setString(1,userId);
        this.createCoupleStatement.setInt(2,serviceId);
        this.createCoupleStatement.execute();
    }

    /**
     * Retrieves all services in the database.
     * 
     * @return A list of all services in the database
     * @throws SQLException if a database access error occurs
     */
    public ResultSet retrieveAll(String userId,String status) throws SQLException {
    	this.retrieveCouplesStatement.setString(1,status);
    	this.retrieveCouplesStatement.setString(2,userId);
        ResultSet rs = this.retrieveCouplesStatement.executeQuery();
        /*if (!rs.next()) {
        	//System.out.println("Aucune donnée");
            return null;
        }*/
        return rs;
    }
    /**
     * Retrieves all services in the database.
     * 
     * @return A list of all services in the database
     * @throws SQLException if a database access error occurs
     */
    public ResultSet retrieveAll(String status) throws SQLException {
    	this.retrieveAllCouplesStatement.setString(1,status);
        ResultSet rs = this.retrieveAllCouplesStatement.executeQuery();
        /*if (!rs.next()) {
            return null;
        }*/
        return rs;
    }
    
}
