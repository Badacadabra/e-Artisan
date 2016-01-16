package services;
import java.sql.Connection;
import java.sql.SQLException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * A class for accessing the database for services. This class can be used as a bean with (read-only) property "db".
 * Strings host, database, username, password, and table are expected to be found.
 * At any moment there is only one instance of the link to the database, and one instance of the SQLUserDB class, maintained by this class.
 * Connector/J is used for accessing the DBMS.
 * 
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public class ServiceDBHandler {
    
    /** The unique link to the database (null if none active). */
    private static Connection link;

    /** The unique instance of class SQLProductsDB (null if none). */
    private static SQLServiceDB db;

    /**
     * Builds a new instance, using the strings used in the environment.
     * 
     * @throws NamingException if strings host, database, username, password, or table cannot be found
     * @throws SQLException if any problem occurs for accessing the database
     */
    public ServiceDBHandler() throws NamingException, SQLException {
        if (ServiceDBHandler.db==null) {
            ServiceDBHandler.initialize();
        }
    }

    /**
     * Returns the instance of SQLServiceDB.
     * 
     * @throws NamingException if strings host, database, username, password, or table cannot be found
     * @throws SQLException if any problem occurs for accessing the database
     */
    public SQLServiceDB getDb() throws NamingException, SQLException {
        if (ServiceDBHandler.db==null) {
            ServiceDBHandler.initialize();
        }
        return ServiceDBHandler.db;
    }

    /**
     * Releases the connection to the database.
     * 
     * @throws SQLException if any problem occurs while closing the connection
     */
    public static void close() throws SQLException {
        if (ServiceDBHandler.link!=null) {
            ServiceDBHandler.link.close();
        }
    }

    // Helper methods =====================================================================

    /**
     * Initializes the connection to the database and the instance of SQLServiceDB.
     * For each of these objects, nothing occurs if it is already initialized.
     * 
     * @throws NamingException if strings host, database, username, password, or table cannot be found
     * @throws SQLException if any problem occurs for accessing the database
     */
    private static void initialize() throws NamingException, SQLException {
        String host=InitialContext.doLookup("java:comp/env/host");
        String database=InitialContext.doLookup("java:comp/env/database");
        String username=InitialContext.doLookup("java:comp/env/username");
        String password=InitialContext.doLookup("java:comp/env/password");
        String table=InitialContext.doLookup("java:comp/env/services");
        ServiceDBHandler.db=new SQLServiceDB(ServiceDBHandler.getLink(host,database,username,password),table);
        ServiceDBHandler.db.createTables();
    }

    /**
     * Returns the link to the database, which is active.
     * 
     * @param host The hostname for the DBMS
     * @paam database The name for the database to use in the DBMS
     * @param username The username for connecting to the database
     * @param password The password for connecting to the database
     * @return An active link to the database
     * @throws SQLException if no active link can be established
     */
    private static Connection getLink(String host, String database, String username, String password) throws SQLException {
        if (ServiceDBHandler.link==null) {
            MysqlDataSource ds=new MysqlDataSource();
            ds.setServerName(host);
            ds.setDatabaseName(database);
            ServiceDBHandler.link=ds.getConnection(username,password);
        }
        if (!ServiceDBHandler.link.isValid(0)) {
            throw new SQLException("Failed to initialize a valid connection to database.");
        }
        return ServiceDBHandler.link;
    }

}
