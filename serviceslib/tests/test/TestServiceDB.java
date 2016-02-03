package test;

import java.util.Collection;
import java.util.GregorianCalendar;

import services.SQLServiceDB;
import services.Service;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import services.Service;
import services.SQLServiceDB;

/**
 * A class for running some basic tests on SQLServiceDB.
 *
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public class TestServiceDB {

    // Arguments: DBMS host, database, username, and password, resp., in args[0], ..., args[3]
    public static void main(String [] args) {
        if (args.length!=4) {
            System.err.println("You must specify DB host, database, username, and password as arguments");
            System.exit(1);
        }
        System.out.print("Testing class SQLServiceDB... ");
        System.out.flush();
        SQLServiceDB db=null;
        Connection link=null;
        try {
            link=TestServiceDB.createLink(args[0],args[1],args[2],args[3]);
            db=new SQLServiceDB(link,"tableServicesTest");
            TestServiceDB.test(db);
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

    protected static void test(SQLServiceDB db) throws SQLException, AssertionError {
        // C
        db.createTables();

        db.create(new Service(1, "Service1", "Description du service 1", new GregorianCalendar(2015, GregorianCalendar.NOVEMBER, 13),
                new GregorianCalendar(2015, GregorianCalendar.NOVEMBER, 13), "besoin"));
        db.create(new Service(2, "Service2", "Description du service 2", new GregorianCalendar(2014, GregorianCalendar.FEBRUARY, 9),
                new GregorianCalendar(2015, GregorianCalendar.MARCH, 15), "besoin"));
        db.create(new Service(3, "Service3", "Description du service 3", new GregorianCalendar(2011, GregorianCalendar.DECEMBER, 1),
                new GregorianCalendar(2015, GregorianCalendar.DECEMBER, 25), "offre"));

        // R
        List<Service> res=db.retrieveAll();
        assert res.size()==3 : "READ - Inconsistent number of services in database";
        boolean service1Found=false;
        for (Service s : res) {
            if ("Service1".equals(s.getName())) {
                service1Found=true;
                break;
            }
        }
        assert service1Found : "READ - A valid service is not found in database";
        Service service2=db.retrieve(2);
        assert "Service2".equals(service2.getName()) : "READ - Name mismatch";

        // U
        Service newService = new Service(3, "Service3Modifié", "Description modifiée du service 3",
                new GregorianCalendar(2015, GregorianCalendar.JANUARY, 6),
                new GregorianCalendar(2015, GregorianCalendar.JANUARY, 19), "besoin");
        db.update(newService, 3);
        assert db.exists(3) : "UPDATE - Incorrect update";

        // D
        db.delete(newService.getId());
        assert db.retrieveAll().size()==2;
        assert db.retrieve(3)==null : "DELETE - The service should not exist in database";
    }

}
