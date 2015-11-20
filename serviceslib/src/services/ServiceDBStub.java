package services;

import java.util.HashMap;
import java.util.Map;

/**
 * A stub for interface IServiceDB, which does provide RAM persistency but no long-term persistency.
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public class ServiceDBStub implements IServiceDB 
{
	/** The map of services. */
	protected Map<Integer,Service> services;

	/**
     * Builds a new, empty list of services.
     */
	public ServiceDBStub() {
		this.services = new HashMap<Integer,Service>();
	}
	
	@Override
	public void create(Service s) throws Exception {
		this.services.put(s.getId(),s);
	}
	
	@Override
	public Service retrieve(int id) throws IndexOutOfBoundsException {
		if (this.services.containsKey(id)) {
			return this.services.get(id);
		} else
			throw new IndexOutOfBoundsException("Cannot find any service with id : "+ id);
	}
	
	@Override
	public Map<Integer,Service> retrieveAll() throws Exception {
		return this.services;
	}
	
	@Override
	public boolean exists(int id) throws Exception {
		for (Integer sId: this.services.keySet()) {
            if (sId == id) {
                return true;
            }
        }
        return false;
	}
	
	@Override
	public void update(int id, Service s) throws Exception {
		if (this.services.containsKey(id)) {
			this.services.put(id,s);
		} else
			throw new IndexOutOfBoundsException("Cannot find any service with id : "+ id);
	}
	
	@Override
	public void delete(int id) throws Exception {
		if (this.services.containsKey(id)) {
			this.services.remove(id);
		} else
			throw new IndexOutOfBoundsException("Cannot find any service with id : "+ id);
	}

}
