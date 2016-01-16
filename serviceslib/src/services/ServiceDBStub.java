package services;

import java.util.HashMap;
import java.util.Map;

/**
 * A stub for interface IServiceDB, which does provide RAM persistency but no long-term persistency.
 * 
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public class ServiceDBStub implements IServiceDB {
    
    /** The map of services. */
    protected Map<Integer,Service> services;

    /**
     * Builds a new, empty list of services.
     */
    public ServiceDBStub() {
	this.services = new HashMap<Integer,Service>();
    }

    /**
     * Adds a service to the database.
     * 
     * @param s The service to add 
     * @throws Exception if a service with the same id already exists in the list or a database access error occurs
     */
    @Override
    public void create(Service s) throws Exception {
        this.services.put(s.getId(),s);
    }

    /**
     * Returns the service with the given id.
     * 
     * @param id The id to search for 
     * @return The service with the given id in the list
     * @throws Exception if no service with the given id exists in the list or a database access error occurs
     */
    @Override
    public Service retrieve(int id) throws IndexOutOfBoundsException {
        if (this.services.containsKey(id)) {
            return this.services.get(id);
        } else
            throw new IndexOutOfBoundsException("Cannot find any service with id : "+ id);
    }

    /**
     * Returns the list of all services in this database.
     * 
     * @return The list of all services in this database as an instance of java.util.Map<Integer,Service>.
     * @throws Exception if a database access error occurs 
     */
    @Override
    public Map<Integer,Service> retrieveAll() throws Exception {
	return this.services;
    }
	
    /**
     * Decides whether a service with a given id exists in the list.
     * 
     * @return true is the list contains a service with the given id, false otherwise
     * @throws Exception if a database access error occurs
     */
    @Override
    public boolean exists(int id) throws Exception {
	for (Integer sId: this.services.keySet()) {
            if (sId == id) {
                return true;
            }
        }
        return false;
    }
	
    /**
     * Updates a service in the database.
     * 
     * @param id The id of the service to update
     * @param s An instance of Service to store in place of the existing one
     * @throws Exception if no service is currently associated to the given id, 
     * or the new instance has an id which already exists in the database, or a database access error occurs
     */
    @Override
    public void update(int id, Service s) throws Exception {
	if (this.services.containsKey(id)) {
	    this.services.put(id,s);
	} else
	    throw new IndexOutOfBoundsException("Cannot find any service with id : "+ id);
    }
	
    /**
     * Removes the service with a given id from this database.
     * @param id The id of the service to remove
     * @throws Exception if no service is currently associated to the given id or a database access error occurs
     */
    @Override
    public void delete(int id) throws Exception {
	if (this.services.containsKey(id)) {
	    this.services.remove(id);
	} else
	    throw new IndexOutOfBoundsException("Cannot find any service with id : "+ id);
    }

}
