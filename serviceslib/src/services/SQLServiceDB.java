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
 * @author Macky Dieng
 * @author Baptiste Vannesson
 *
 */
public class SQLServiceDB
{
	/** The name for the SQL table where to store Services. */
    protected String table;

    /** A prepared statement for creations. */
    private PreparedStatement createServiceStatement;

    /** A prepared statement for retrieval of one Service. */
    private PreparedStatement retrieveServiceStatement;

    /** A link to the database. */
    protected Connection link;

    /**
     * Builds a new instance.
     * @param link An active connection to an SQL database
     * @param table The name of the table where to store Services
     * @throws SQLException if a database access error occurs
     */
    public SQLServiceDB (Connection link, String table) throws SQLException {
        this.link = link;
        this.table = table;
        String query = null;
        query = "INSERT INTO `" + this.table + "(name,description,publicationDate,deadline)` VALUES(?,?,?,?)";
        this.createServiceStatement = this.link.prepareStatement(query);
        query = "SELECT * FROM `" + this.table + "` WHERE id=?";
        this.retrieveServiceStatement = this.link.prepareStatement(query);
    }

    //@Override
    public void addService (Service Service) throws SQLException {
        this.create(Service);
    }

    //@Override
    public List<Service> getAll () throws SQLException {
        return this.retrieveAll();
    }

    // Methods

    /**
     * Resets the link to the database.
     * This method can be used in case the connection breaks down.
     * @param link An active link to the database
     */
    public void setLink (Connection link) {
        this.link=link;
    }

    /**
     * Creates the necessary table in the database. Nothing occurs if the table already exists.
     * @throws SQLException if a database access error occurs
     */
    public void createTables () throws SQLException {
        String query="CREATE TABLE IF NOT EXISTS `"+this.table+"` (";
        query += "`name` VARCHAR(100) NOT NULL, ";
        query += "`description` TEXT NOT NULL, ";
        query += "`publicationDate` DATETIME NOT NULL, ";
        query += "`deadline` DATETIME NOT NULL, ";
        query += "PRIMARY KEY (`id`)";
        query += ")";
        Statement statement = this.link.createStatement();
        statement.execute(query);
    }

    /**
     * Stores a new Service in the database.
     * @param Service The Service to store
     * @throws SQLException if a database access error occurs
     */
    public void create (Service service) throws SQLException {
        this.createServiceStatement.setString(1,service.getName());
        this.createServiceStatement.setString(2,service.getDescription());
        this.createServiceStatement.setDate(3,new Date(service.getPublicationDate().getTimeInMillis()));
        this.createServiceStatement.setDate(4,new Date(service.getDeadline().getTimeInMillis()));
        this.createServiceStatement.execute();
    }

    /**
     * Retrieves all the Services in the database.
     * @return A list of all Services in the database
     * @throws SQLException if a database access error occurs
     */
    public List<Service> retrieveAll () throws SQLException {
        String query = "SELECT * FROM `" + this.table + "`";
        ResultSet rs = null;
        Statement statement = this.link.createStatement();
        rs=statement.executeQuery(query);
        List<Service> res = new ArrayList<Service>();
        while (rs.next()) {
            res.add(new Service(rs.getString("name"), rs.getString("description"), rs.getDate("publicationDate"), rs.getDate("deadline")));
        }
        return res;
    }

    /**
     * Retrieves a Service in the database.
     * @param name The name of the Service
     * @return A Service, or null if none with the given name exists in the database
     * @throws SQLException if a database access error occurs
     */
    public Service retrieve (String name) throws SQLException {
        this.retrieveServiceStatement.setString(1,name);
        ResultSet rs = this.retrieveServiceStatement.executeQuery();
        if (!rs.next()) {
            return null;
        }
        return new Service(rs.getString("name"), rs.getString("description"), rs.getDate("publicationDate"), rs.getDate("deadline"));
    }

    /**
     * Drops the table from the database. Nothing occurs if the table does not exist.
     * @throws SQLException if a database access error occurs
     */
    public void deleteTables () throws SQLException {
        String query = "DROP TABLE IF EXISTS `" + this.table + "`";
        Statement statement = this.link.createStatement();
        statement.execute(query);
    }

    /**
     * Deletes a Service. Nothing occurs in case the Service does not exist in the database.
     * @param Service The Service
     * @throws SQLException if a database access error occurs
     */
    public void delete (Service service) throws SQLException {  
        String query = "DELETE FROM `" + this.table + "` WHERE name=\"" + service.getName() + "\"";
        Statement statement = this.link.createStatement();
        statement.execute(query);
    }
}

