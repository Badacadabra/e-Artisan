package services;

import java.util.Map;

/**
 * An interface for database of services. The interface essentially declares CRUD operations.
 * The key for distinguishing two services from each other is assumed to be a classical id.
 *
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public interface IServiceDB {

    // "C" operations

    /**
     * Adds a service to the database.
     *
     * @param s The service to add
     * @return The id of the latest service inserted in the database
     * @throws Exception if a service with the same id already exists in the list or a database access error occurs
     */
    public int create(Service s) throws Exception;

    // "R" operations

    /**
     * Returns the service with the given id.
     *
     * @param id The id to search for
     * @return The service with the given id in the list
     * @throws Exception if no service with the given id exists in the list or a database access error occurs
     */
    public Service retrieve(int id) throws Exception;

    /**
     * Decides whether a service with a given id exists in the list.
     *
     * @return true is the list contains a service with the given id, false otherwise
     * @throws Exception if a database access error occurs
     */
    public boolean exists(int id) throws Exception;

    // "U" operations

    /**
     * Updates a service in the database.
     *
     * @param id The id of the service to update
     * @param s An instance of Service to store in place of the existing one
     * @throws Exception if no service is currently associated to the given id,
     * or the new instance has an id which already exists in the database, or a database access error occurs
     */
    public void update(Service s, int id) throws Exception;

    // "D" operations

    /**
     * Removes the service with a given id from this database.
     * @param id The id of the service to remove
     * @throws Exception if no service is currently associated to the given id or a database access error occurs
     */
    public void delete(int id) throws Exception;

}
