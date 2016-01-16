package users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for SQL storage of users in a database.
 * The email of the user is taken to be its primary key.
 * 
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public class SQLUserDB {
    
    /** The name for the SQL table where to store users. */
    protected String table;

    /** A prepared statement for creations. */
    private PreparedStatement createUserStatement;

    /** A prepared statement for retrieval of one user. */
    private PreparedStatement retrieveUserStatement;
    
    /** A prepared statement to update one user. */
    private PreparedStatement updateUserStatement;
    
    /** A prepared statement to check one user. */
    private PreparedStatement checkUserStatement;
    
    /** A prepared statement to see if a user exists */
    private PreparedStatement isUserStatement;
    
    /** A link to the database. */
    protected Connection link;

    /**
     * Builds a new instance.
     * 
     * @param link An active connection to an SQL database
     * @param table The name of the table where to store users
     * @throws SQLException if a database access error occurs
     */
    public SQLUserDB(Connection link, String table) throws SQLException {
        this.link=link;
        this.table=table;
        String query=null;
        query="INSERT INTO `"+this.table+"` VALUES(?,?,?,?,?,MD5(?))";
        this.createUserStatement=this.link.prepareStatement(query);
        query="SELECT * FROM `"+this.table+"` WHERE email=?";
        this.retrieveUserStatement=this.link.prepareStatement(query);
        query="UPDATE `"+this.table+"` SET name = ?, firstname =?, description = ?, image = ?, email = ?, passwd = MD5(?) where email = ?";
        this.updateUserStatement=this.link.prepareStatement(query);
        query="SELECT * FROM `"+this.table+"` WHERE email=? and passwd=MD5(?)";
        this.checkUserStatement=this.link.prepareStatement(query);
        query="SELECT * FROM `"+this.table+"` WHERE email=?";
        this.isUserStatement=this.link.prepareStatement(query);
    }

    /**
     * Adds a new user in the database.
     * 
     * @param The user to add in the database.
     * @throws SQLException if a database access error occurs
     */
    public void addUser(User user) throws SQLException {
        this.create(user);
    }

    /**
     * Gets all users in the database.
     * 
     * @return All users in the database.
     * @throws SQLException if a database access error occurs
     */
    public List<User> getAll() throws SQLException {
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
        query+="`name` VARCHAR(100) NOT NULL, ";
        query+="`firstname` VARCHAR(100) NOT NULL, ";
        query+="`description` text NULL, ";
        query+="`image` VARCHAR(100) NULL, ";
        query+="`email`  VARCHAR(100) NOT NULL, ";
        query+="`passwd`  VARCHAR(100) NOT NULL, ";
        query+="PRIMARY KEY (`email`)";
        query+=")";
        Statement statement=this.link.createStatement();
        statement.execute(query);
    }

    /**
     * Stores a new user in the database.
     * 
     * @param User The user to store
     * @throws SQLException if a database access error occurs
     */
    public void create(User user) throws SQLException {
        this.createUserStatement.setString(1,user.getName());
        this.createUserStatement.setString(2,user.getFirstName());
        this.createUserStatement.setString(3,user.getDescription());
        this.createUserStatement.setString(4,user.getImage());
        this.createUserStatement.setString(5,user.getEmail());
        this.createUserStatement.setString(6,user.getPassword());
        this.createUserStatement.execute();
    }

    /**
     * Retrieves all the users in the database.
     * 
     * @return A list of all users in the database
     * @throws SQLException if a database access error occurs
     */
    public List<User> retrieveAll() throws SQLException {
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
     * Retrieves a user in the database.
     * 
     * @param email The email of the user
     * @return A user, or null if none with the given name exists in the database
     * @throws SQLException if a database access error occurs
     */
    public User retrieve(String email) throws SQLException {
        this.retrieveUserStatement.setString(1,email);
        ResultSet rs=this.retrieveUserStatement.executeQuery();
        if (!rs.next()) {
            return null;
        }
        return new User(rs.getString("name"),rs.getString("firstName"),rs.getString("description"),rs.getString("image"),rs.getString("email"),rs.getString("passwd"));
    }
    
    /**
     * Checks if a user is valid (if the email matches the password).
     * 
     * @param email The user's email
     * @param password The user's password
     * @return True or false
     * @throws SQLException if a database access error occurs
     */
    public boolean isValidUser(String email, String password) throws SQLException {
        this.checkUserStatement.setString(1,email);
        this.checkUserStatement.setString(2,password);
        ResultSet rs=this.checkUserStatement.executeQuery();
        return rs.next();
    }
    
    /**
     * Checks if a user exists in the database.
     * 
     * @param email The user's email
     * @return True or false
     * @throws SQLException if a database access error occurs
     */
    public boolean exists(String email) throws SQLException {
        this.isUserStatement.setString(1,email);
        ResultSet rs=this.isUserStatement.executeQuery();
        return rs.next();
    }
    
    /**
     * Update user information in the database.
     * 
     * @param user The user
     * @param email The user's email
     * @throws SQLException if a database access error occurs
     */
    public void update(User user, String email) throws SQLException {  
        this.updateUserStatement.setString(1,user.getName());
        this.updateUserStatement.setString(2,user.getFirstName());
        this.updateUserStatement.setString(3,user.getDescription());
        this.updateUserStatement.setString(4,user.getImage());
        this.updateUserStatement.setString(5,user.getEmail());
        this.updateUserStatement.setString(6,user.getPassword());
        this.updateUserStatement.setString(7,email);
        this.updateUserStatement.execute();
    }

    /**
     * Drops the table from the database. Nothing occurs if the table does not exist.
     * 
     * @throws SQLException if a database access error occurs
     */
    public void deleteTables() throws SQLException {
        String query="DROP TABLE IF EXISTS `"+this.table+"`";
        Statement statement=this.link.createStatement();
        statement.execute(query);
    }

    /**
     * Deletes a user. Nothing occurs in case the user does not exist in the database.
     * 
     * @param User The user
     * @throws SQLException if a database access error occurs
     */
    public void delete(User user) throws SQLException {  
        String query="DELETE FROM `"+this.table+"` WHERE email=\""+user.getEmail()+"\"";
        Statement statement=this.link.createStatement();
        statement.execute(query);
    }

}
