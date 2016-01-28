package services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * A class for SQL storage of services in a database.
 * The id of the service is taken to be its primary key.
 * 
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public class SQLServiceDB {
    
    /** The name for the SQL table where to store services. */
    protected String table;

    /** A prepared statement for creations. */
    private PreparedStatement createServiceStatement;

    /** A prepared statement for retrieval of one service. */
    private PreparedStatement retrieveServiceStatement;
    
    /** A prepared statement to update a service. */
    private PreparedStatement updateServiceStatement;

    
    /** A prepared statement to see if a service exists */
    private PreparedStatement isServiceStatement;
    
    /** A prepared statement to see if a service exists */
    private PreparedStatement deleteStatement;
    
    /** A link to the database. */
    protected Connection link;

    /**
     * Builds a new instance.
     * 
     * @param link An active connection to an SQL database
     * @param table The name of the table where to store Services
     * @throws SQLException if a database access error occurs
     */
    public SQLServiceDB(Connection link, String table) throws SQLException {
        this.link = link;
        this.table = table;
        String query = null;
        query = "INSERT INTO `" + this.table + "` SET name = ?, description = ?, publicationDate = ?, deadline = ?, status = ?";
        this.createServiceStatement = this.link.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        query = "SELECT * FROM `" + this.table + "` WHERE id=?";
        this.retrieveServiceStatement = this.link.prepareStatement(query);
        query = "UPDATE `" + this.table + "` SET name = ?, description = ?, publicationDate = ?, deadline = ?, status = ? where id = ?";
        this.updateServiceStatement = this.link.prepareStatement(query);
        query = "SELECT * FROM `" + this.table + "` WHERE id = ?";
        this.isServiceStatement=this.link.prepareStatement(query);
        query = "DELETE FROM `" + this.table + "` WHERE id = ?";
        this.deleteStatement = this.link.prepareStatement(query);
    }

    /**
     * Adds a new service in the database.
     * 
     * @param The service to add in the database.
     * @throws SQLException if a database access error occurs
     */
    public void addService(Service Service) throws SQLException {
        this.create(Service);
    }

    /**
     * Gets all services in the database.
     * 
     * @return All services in the database
     * @throws SQLException if a database access error occurs
     */
    public List<Service> getAll() throws SQLException {
        return this.retrieveAll();
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
        String query="CREATE TABLE IF NOT EXISTS `"+this.table+"` (";
        query +="`id` int(255) NOT NULL AUTO_INCREMENT,";
        query += "`name` VARCHAR(100) NOT NULL, ";
        query += "`description` TEXT NOT NULL, ";
        query += "`publicationDate` DATETIME NOT NULL, ";
        query += "`deadline` DATETIME NOT NULL, ";
        query += "`status` VARCHAR(20) NOT NULL, ";
        query += "PRIMARY KEY (`id`)";
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
    public int create(Service service) throws SQLException {
        this.createServiceStatement.setString(1,service.getName());
        this.createServiceStatement.setString(2,service.getDescription());
        this.createServiceStatement.setDate(3,new Date(service.getPublicationDate().getTimeInMillis()));
        this.createServiceStatement.setDate(4,new Date(service.getDeadline().getTimeInMillis()));
        this.createServiceStatement.setString(5,service.getStatus());
        this.createServiceStatement.execute();
        ResultSet rs = this.createServiceStatement.getGeneratedKeys();
        int last_inserted_id = 0;
        if (rs != null && rs.next()) {
        	last_inserted_id = rs.getInt(1);
        }
        return last_inserted_id;
    }

    /**
     * Retrieves all services in the database.
     * 
     * @return A list of all services in the database
     * @throws SQLException if a database access error occurs
     */
    public List<Service> retrieveAll() throws SQLException {
        String query = "SELECT * FROM `" + this.table + "`";
        ResultSet rs = null;
        Statement statement = this.link.createStatement();
        rs=statement.executeQuery(query);
        List<Service> res = new ArrayList<Service>();
        while (rs.next()) {
        	res.add(new Service(rs.getInt("id"),rs.getString("name"), rs.getString("description"),new GregorianCalendar(2015, GregorianCalendar.NOVEMBER, 13),new GregorianCalendar(2015, GregorianCalendar.NOVEMBER, 13),rs.getString("status")));
        }
        return res;
    }
    
    /**
     * Retrieves all services in the database, given a certain status.
     * 
     * @param status The status
     * @return All services that have the given status
     * @throws SQLException if a database access error occurs
     */
    public List<Service> retrieveAll(String status) throws SQLException {
        String query = "SELECT * FROM `" + this.table + "` where status=?";
        ResultSet rs = null;
        PreparedStatement statement = this.link.prepareStatement(query);
        statement.setString(1, status);
        rs=statement.executeQuery();
        List<Service> res = new ArrayList<Service>();
        while (rs.next()) {
        	res.add(new Service(rs.getInt("id"),rs.getString("name"), rs.getString("description"),new GregorianCalendar(2015, GregorianCalendar.NOVEMBER, 13),new GregorianCalendar(2015, GregorianCalendar.NOVEMBER, 13),rs.getString("status")));
        }
        return res;
    }
    
    /**
     * Retrieves a service in the database.
     * 
     * @param name The name of the service
     * @return A service, or null if none with the given name exists in the database
     * @throws SQLException if a database access error occurs
     */
    public Service retrieve(int id) throws SQLException {
        this.retrieveServiceStatement.setInt(1,id);
        ResultSet rs = this.retrieveServiceStatement.executeQuery();
        if (!rs.next()) {
            return null;
        }
        return new Service(rs.getInt("id"),rs.getString("name"), rs.getString("description"),new GregorianCalendar(2015, GregorianCalendar.NOVEMBER, 13),new GregorianCalendar(2015, GregorianCalendar.NOVEMBER, 13),rs.getString("status"));
    }
    
    /**
     * Checks if a service exists in the database.
     * 
     * @param id The service's id
     * @return True or false
     * @throws SQLException if a database access error occurs
     */
    public boolean exists(int id) throws SQLException {
        this.isServiceStatement.setInt(1,id);
        ResultSet rs=this.isServiceStatement.executeQuery();
        return rs.next();
    }
    
    /**
     * Stores a new service in the database.
     * 
     * @param service The service to store
     * @throws SQLException if a database access error occurs
     */
    public void update(Service service, int id) throws SQLException {
        this.updateServiceStatement.setString(1,service.getName());
        this.updateServiceStatement.setString(2,service.getDescription());
        this.updateServiceStatement.setDate(3,new Date(service.getPublicationDate().getTimeInMillis()));
        this.updateServiceStatement.setDate(4,new Date(service.getDeadline().getTimeInMillis()));
        this.updateServiceStatement.setString(5,service.getStatus());
        this.updateServiceStatement.setInt(6,id);
        this.updateServiceStatement.execute();
    }

    /**
     * Drops the table from the database. Nothing occurs if the table does not exist.
     * 
     * @throws SQLException if a database access error occurs
     */
    public void deleteTables() throws SQLException {
        String query = "DROP TABLE IF EXISTS `" + this.table + "`";
        Statement statement = this.link.createStatement();
        statement.execute(query);
    }

    /**
     * Deletes a service. Nothing occurs in case the service does not exist in the database.
     * 
     * @param Service The service
     * @throws SQLException if a database access error occurs
     */
    public void delete(int id) throws SQLException {  
    	this.deleteStatement.setInt(1, id);
    	this.deleteStatement.execute();
    }
    
}
