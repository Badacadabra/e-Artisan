/**
 * 
 */
package services;

import java.util.GregorianCalendar;

/**
 * Interface pour les services échangés.
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public class TestServiceDB {
	
	public static void test (IServiceDB instance) throws Exception {
		instance.create(new Service("TOTO",new GregorianCalendar(2015,GregorianCalendar.NOVEMBER,13),
				new GregorianCalendar(2015,GregorianCalendar.NOVEMBER,13)));
	}
}
