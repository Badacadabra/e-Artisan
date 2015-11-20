/**
 * 
 */
package services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Interface pour les services échangés.
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public class ServiceDBStub implements IServiceDB 
{

	protected Map<Integer,Service> services;
	/**
	 * 
	 */
	public ServiceDBStub() {
		this.services = new HashMap<Integer,Service>();
	}
	@Override
	public void create(Service s) throws Exception {
		// TODO Auto-generated method stub
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
