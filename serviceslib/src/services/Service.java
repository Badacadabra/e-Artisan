package services;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A class which describes a service.
 * 
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public class Service {
	
    /** The id of the service. */
    protected int id;
	
    /** The name of the service. */
    protected String name;

    /** The description of the service. */
    protected String description;
	
    /** The publication date of the service. */
    protected GregorianCalendar publicationDate;
	
    /** The deadline of the service. */
    protected GregorianCalendar deadline;
	
    /** The status of the service (need or offer) */
    protected String status;

    /**
     * Builds a new service.
     * 
     * @param name The name of the service
     * @param description The description of the service
     * @param publicationDate The publication date of the service
     * @param deadline The deadline of the service
     * @param status The status of the service
     */
    public Service(String name, String description, GregorianCalendar publicationDate, GregorianCalendar deadline, String status) {
        this.name = name;
	this.description = description;
	this.publicationDate = publicationDate;
	this.deadline = deadline;
	this.status = status;
    }
	
    /**
     * Builds a new service.
     * 
     * @param id The id of the service
     * @param name The name of the service
     * @param description The description of the service
     * @param publicationDate The publication date of the service
     * @param deadline The deadline of the service
     * @param status The status of the service
     */
    public Service(int id, String name, String description, GregorianCalendar publicationDate, GregorianCalendar deadline, String status) {
	this.name = name;
	this.description = description;
	this.publicationDate = publicationDate;
	this.deadline = deadline;
	this.status = status;
	this.id = id;
    }
	
    /**
     * Convert a Date object to a GregorianCalendar object.
     *  
     * @param date The date to convert
     * @return The converted date
     */
    private static GregorianCalendar dateToGregorianCalendar(Date date) {
	GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
	cal.setTime(date);
	return cal;
    }
	
    /**
     * Returns the id of the service.
     * 
     * @return The id of the service
     */
    public int getId() {
        return id;
    }
	
    /**
     * Returns the name of the service.
     * 
     * @return The name of the service
     */
    public String getName() {
        return name;
    }
	
    /**
     * Sets the name of the service.
     * 
     * @param name The name of the service
     */
    public void setName(String name) {
	this.name = name;
    }
	
    /**
     * Returns the description of the service.
     * 
     * @return The description of the service
     */
    public String getDescription() {
	return description;
    }
	
    /**
     * Sets the description of the service.
     * 
     * @param description The description of the service
     */
    public void setDescription(String description) {
	this.description = description;
    }
	
    /**
     * Returns the publication date of the service.
     * 
     * @return The publication date of the service
     */
    public GregorianCalendar getPublicationDate() {
	return publicationDate;
    }
    
    /**
     * Sets the publication date of the service.
     * 
     * @param publicationDate The publication date of the service
     */
    public void setPublicationDate(GregorianCalendar publicationDate) {
        this.publicationDate = publicationDate;
    }
	
    /**
     * Returns the deadline of the service.
     * 
     * @return The deadline of the service
     */
    public GregorianCalendar getDeadline() {
	return deadline;
    }
	
    /**
     * Sets the deadline of the service.
     * 
     * @param The deadline of the service
     */
    public void setDeadline(GregorianCalendar deadline) {
	this.deadline = deadline;
    
    }
    
    /**
     * Returns the status of the service.
     * 
     * @return The status of the service
     */
    public String getStatus() {
	return status;
    }

    /**
     * Sets the status of the service.
     * 
     * @param status The status of the service
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
