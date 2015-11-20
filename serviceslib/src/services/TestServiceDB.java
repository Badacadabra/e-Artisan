package services;

import java.util.Collection;
import java.util.GregorianCalendar;

/**
 * A class for running some basic tests on classes which implement interface IServiceDB.
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public class TestServiceDB {
	
	/**
     * Runs a series of tests on an instance of a class which implements IServiceDB.
     * The instance is assumed to represent an empty database of services when passed to
     * this method. If tests go well, the database is empty again when the method exits.
     * The method uses assertions to run tests.
     * @param instance An instance of the class to be tested, representing an empty
     * database of services
     * @throws Exception if an unexpected error occurs
     */
    public static void test(IServiceDB instance) throws Exception {
    	
    	instance.create(new Service("TOTO", new GregorianCalendar(2015, GregorianCalendar.NOVEMBER, 13),
    			new GregorianCalendar(2015, GregorianCalendar.NOVEMBER, 13)));
       	instance.create(new Service("TATA", new GregorianCalendar(2014, GregorianCalendar.FEBRUARY, 9),
       			new GregorianCalendar(2015, GregorianCalendar.MARCH, 15)));
       	instance.create(new Service("TITI", new GregorianCalendar(2011, GregorianCalendar.DECEMBER, 1),
       			new GregorianCalendar(2015, GregorianCalendar.DECEMBER, 25)));
       	instance.create(new Service("TUTU", new GregorianCalendar(2015, GregorianCalendar.JANUARY, 1),
       			new GregorianCalendar(2015, GregorianCalendar.DECEMBER, 31)));
       	instance.create(new Service("TETE", new GregorianCalendar(2000, GregorianCalendar.MARCH, 10),
       			new GregorianCalendar(2001, GregorianCalendar.APRIL, 27)));

        // Testing "R" methods
       	Collection<Service> all = instance.retrieveAll().values();
        assert all.size() == 5;
        boolean totoFound = false;
        for (Service s: all) {
            if ("TOTO".equals(s.getName())) {
                totoFound = true;
            }
        }
        assert totoFound;
        assert instance.exists(3);
        assert !instance.exists(999);
        Service toto = instance.retrieve(1);
        Service titi = instance.retrieve(3);
        Service tete = instance.retrieve(5);
        assert "TOTO".equals(toto.getName());
        assert "TITI".equals(titi.getName());
        assert "TETE".equals(tete.getName());

        // Testing "U" methods
        instance.update(2,new Service("TYTY", new GregorianCalendar(2014, GregorianCalendar.FEBRUARY, 9),
       			new GregorianCalendar(2015, GregorianCalendar.MARCH, 15)));
        assert instance.exists(2);
        Service tyty = instance.retrieve(2);
        assert "TYTY".equals(tyty.getName());

        // Testing "D" methods
        instance.delete(4);
        assert instance.retrieveAll().size() == 4;
        assert !instance.exists(4);
        assert instance.exists(5);

    }
	
}
