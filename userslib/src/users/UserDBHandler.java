package users;
import java.sql.Connection;
import java.sql.SQLException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import persons.*;

/**
 * @author 21410938
 *
 */
public class UserDBHandler
{
	/** The unique link to the database (null if none active). */
    private static Connection link;

    /** The unique instance of class SQLProductsDB (null if none). */
    private static SQLUserDB db;

    /**
     * Builds a new instance, using the strings used in the environment.
     * @throws NamingException if strings host, database, username, password, or table cannot be found
     * @throws SQLException if any problem occurs for accessing the database
     */
    public UserDBHandler () throws NamingException, SQLException {
        if (UserDBHandler.db==null) {
            UserDBHandler.initialize();
        }
    }

    /**
     * Returns the instance of SQLUserDB.
     * @throws NamingException if strings host, database, username, password, or table cannot be found
     * @throws SQLException if any problem occurs for accessing the database
     */
    public SQLUserDB getDb () throws NamingException, SQLException {
        if (UserDBHandler.db==null) {
            UserDBHandler.initialize();
        }
        System.out.println("getDB");
        return UserDBHandler.db;
    }

    /**
     * Releases the connection to the database.
     * @throws SQLException if any problem occurs while closing the connection
     */
    public static void close () throws SQLException {
        if (UserDBHandler.link!=null) {
            UserDBHandler.link.close();
        }
    }

    // Helper methods =====================================================================

    /**
     * Initializes the connection to the database and the instance of SQLProductsDB.
     * For each of these objects, nothing occurs if it is already initialized.
     * @throws NamingException if strings host, database, username, password, or table cannot be found
     * @throws SQLException if any problem occurs for accessing the database
     */
    private static void initialize () throws NamingException, SQLException {
        String host=InitialContext.doLookup("java:comp/env/host");
        String database=InitialContext.doLookup("java:comp/env/database");
        String username=InitialContext.doLookup("java:comp/env/username");
        String password=InitialContext.doLookup("java:comp/env/password");
        String table=InitialContext.doLookup("java:comp/env/users");
        UserDBHandler.db=new SQLUserDB(UserDBHandler.getLink(host,database,username,password),table);
        UserDBHandler.db.createTables();
    }

    /**
     * Returns the link to the database, which is active.
     * @param host The hostname for the DBMS
     * @paam database The name for the database to use in the DBMS
     * @param username The username for connecting to the database
     * @param password The password for connecting to the database
     * @return An active link to the database
     * @throws SQLException if no active link can be established
     */
    private static Connection getLink (String host, String database, String username, String password) throws SQLException {
        if (UserDBHandler.link==null) {
            MysqlDataSource ds=new MysqlDataSource();
            ds.setServerName(host);
            ds.setDatabaseName(database);
            UserDBHandler.link=ds.getConnection(username,password);
        }
        if (!UserDBHandler.link.isValid(0)) {
            throw new SQLException("Failed to initialize a valid connection to database.");
        }
        return UserDBHandler.link;
    }
}
