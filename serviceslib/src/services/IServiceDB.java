/**
 * 
 */
package services;

import java.util.Collection;
import java.util.Map;

/**
 * Interface pour les services échangés.
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public interface IServiceDB {
	
	/**
	 * @param s l'instance du service à renvoyer
	 * @throws Exception
	 */
	public void create(Service s) throws Exception;
	/**
	 * @param id l'identifiant du service à renvoyer
	 * @return
	 * @throws Exception
	 */
	public Service retrieve(int id) throws Exception;
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<Integer,Service> retrieveAll() throws Exception;
	/**
	 * @param id l'identifiant du service à modifier
	 * @param s l'instance du service à modifier
	 * @throws Exception
	 */
	public void update(int id, Service s) throws Exception;
	/**
	 * @param id l'identifiant du service à supprimer
	 * @throws Exception
	 */
	public void delete(int id) throws Exception;
	
}
