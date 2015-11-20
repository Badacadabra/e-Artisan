package users;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import Users.*;

/**
 * @author 21410938
 *
 */
public class SQLUserDB
{
	/** The name for the SQL table where to store Users. */
    protected String table;

    /** A prepared statement for creations. */
    private PreparedStatement createUserStatement;

    /** A prepared statement for retrieval of one User. */
    private PreparedStatement retrieveUserStatement;

    /** A link to the database. */
    protected Connection link;

    /**
     * Builds a new instance.
     * @param link An active connection to an SQL database
     * @param table The name of the table where to store Users
     * @throws SQLException if a database access error occurs
     */
    public SQLUserDB (Connection link, String table) throws SQLException {
        this.link=link;
        this.table=table;
        String query=null;
        query="INSERT INTO `"+this.table+"` VALUES(?,?,?,?)";
        this.createUserStatement=this.link.prepareStatement(query);
        query="SELECT * FROM `"+this.table+"` WHERE name=?";
        this.retrieveUserStatement=this.link.prepareStatement(query);
    }

    //@Override
    public void addUser (User User) throws SQLException {
        this.create(User);
    }

    //@Override
    public List<User> getAll () throws SQLException {
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
        query+="`email`  VARCHAR(100) NOT NULL, ";
        query+="`name` VARCHAR(100) NOT NULL, ";
        query+="`firstname` VARCHAR(100) NOT NULL, ";
        query+="`passwd`  VARCHAR(100) NOT NULL, ";
        query+="PRIMARY KEY (`email`)";
        query+=")";
        Statement statement=this.link.createStatement();
        statement.execute(query);
    }

    /**
     * Stores a new User in the database.
     * @param User The User to store
     * @throws SQLException if a database access error occurs
     */
    public void create (User User) throws SQLException {
        this.createUserStatement.setString(1,User.getName());
        this.createUserStatement.setString(2,User.getFirstName());
        this.createUserStatement.setString(3,User.getEmail());
        this.createUserStatement.setString(4,User.getPasswd());
        this.createUserStatement.execute();
    }

    /**
     * Retrieves all the Users in the database.
     * @return A list of all Users in the database
     * @throws SQLException if a database access error occurs
     */
    public List<User> retrieveAll () throws SQLException {
        String query="SELECT * FROM `"+this.table+"`";
        ResultSet rs=null;
        Statement statement=this.link.createStatement();
        rs=statement.executeQuery(query);
        List<User> res=new ArrayList<User>();
        while (rs.next()) {
            res.add(new User(rs.getString("name"),rs.getString("firstName"),rs.getString("email"),rs.getString("passwd")));
        }
        return res;
    }

    /**
     * Retrieves a User in the database.
     * @param name The name of the User
     * @return A User, or null if none with the given name exists in the database
     * @throws SQLException if a database access error occurs
     */
    public User retrieve (String name) throws SQLException {
        this.retrieveUserStatement.setString(1,name);
        ResultSet rs=this.retrieveUserStatement.executeQuery();
        if (!rs.next()) {
            return null;
        }
        return new User(rs.getString("name"),rs.getString("firstName"),rs.getString("email"),rs.getString("passwd"));
    }

    /**
     * Drops the table from the database. Nothing occurs if the table does not exist.
     * @throws SQLException if a database access error occurs
     */
    public void deleteTables () throws SQLException {
        String query="DROP TABLE IF EXISTS `"+this.table+"`";
        Statement statement=this.link.createStatement();
        statement.execute(query);
    }

    /**
     * Deletes a User. Nothing occurs in case the User does not exist in the database.
     * @param User The User
     * @throws SQLException if a database access error occurs
     */
    public void delete (User User) throws SQLException {  
        String query="DELETE FROM `"+this.table+"` WHERE name=\""+User.getName()+"\"";
        Statement statement=this.link.createStatement();
        statement.execute(query);
    }
}
