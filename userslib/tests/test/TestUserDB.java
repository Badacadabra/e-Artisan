package test;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import users.User;
import users.SQLUserDB;

/**
 * A class for running some basic tests on SQLUserDB.
 * 
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public class TestUserDB {

    // Arguments: DBMS host, database, username, and password, resp., in args[0], ..., args[3]
    public static void main(String [] args) {
        if (args.length!=4) {
            System.err.println("You must specify DB host, database, username, and password as arguments");
            System.exit(1);
        }
        System.out.print("Testing class SQLUserDB... ");
        System.out.flush();
        SQLUserDB db=null;
        Connection link=null;
        try {
            link=TestUserDB.createLink(args[0],args[1],args[2],args[3]);
            db=new SQLUserDB(link,"tableUsersTest");
            TestUserDB.test(db);
        } catch (SQLException e) {
            System.out.println("Exception: "+e);
        } catch (AssertionError e) {
            System.out.println("AssertionError e: "+e);
        } finally {
            try {
                db.deleteTables();
            } catch (SQLException e) {
                System.out.println("Exception: "+e);
            }
            try {
                link.close();
            } catch (SQLException e) {
                System.out.println("Exception: "+e);
            }
        }
        System.out.println("OK");
    }

    protected static Connection createLink(String host, String database, String username, String password) throws SQLException {
        MysqlDataSource ds=new MysqlDataSource();
        ds.setServerName(host);
        ds.setDatabaseName(database);
        Connection link=ds.getConnection(username,password);
        if (!link.isValid(0)) {
            throw new SQLException("Failed to initialize a valid connection to database.");
        }
        return link;
    }

    protected static void test(SQLUserDB db) throws SQLException, AssertionError {
        // C
        db.createTables();
        db.create(new User("Toto", "Toto", "toto@gmail.com", "mdp1"));
        db.create(new User("Tata", "Tata", "tata@gmail.com", "mdp2"));
        db.create(new User("Titi", "Titi", "titi@gmail.com", "mdp3"));
        
        // R
        List<User> res=db.retrieveAll();
        assert res.size()==3;
        boolean totoFound=false;
        for (User u : res) {
            if ("Toto".equals(u.getName())) {
                totoFound=true;
                break;
            }
        }
        assert totoFound;
        User tata=db.retrieve("tata@gmail.com");
        assert "Tata".equals(tata.getName());
        
        // U
        db.update(new User("Tutu", "Tutu", "tutu@gmail.com", "mdp4"), "tata@gmail.com");
        assert db.exists("tutu@gmail.com");
        
        // D
        db.delete(tata);
        assert db.retrieveAll().size()==2;
        assert db.retrieve("tata@gmail.com")==null;
    }

}