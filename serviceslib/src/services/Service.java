package services;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A class for representing services with an id, a name, a publication date and a deadline.
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
	
    /**
     * Builds a new service.
     * @param name The name of the service
     * @param publicationDate The publication date of the service
     * @param deadline The deadline of the service
     */
	public Service(String name, String description, GregorianCalendar publicationDate, GregorianCalendar deadline)
	{
		this.name = name;
		this.description = description;
		this.publicationDate = publicationDate;
		this.deadline = deadline;
	}
	
    /**
     * Builds a new service.
     * @param name The name of the service
     * @param publicationDate The publication date of the service
     * @param deadline The deadline of the service
     */
	public Service(String name, String description, Date publicationDate, Date deadline)
	{
		this.name = name;
		this.description = description;
		this.publicationDate = dateToGregorianCalendar(publicationDate);
		this.deadline = dateToGregorianCalendar(deadline);
	}
	
	/**
	 * Convert a Date object to a GregorianCalendar object 
	 * @param date The date to convert
	 * @return The converted date
	 */
	private static GregorianCalendar dateToGregorianCalendar(Date date) {
		GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
	/**
	 * Returns the id of the service
	 * @return The id of the service
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Returns the name of the service
	 * @return The name of the service
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * Sets the name of the service
	 * @param name The name of the service
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
	
	/**
	 * Returns the description of the service
	 * @return The name of the service
	 */
	public String getDescription() 
	{
		return description;
	}
	
	/**
	 * Sets the description of the service
	 * @param name The name of the service
	 */
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	/**
	 * Returns the publication date of the service
	 * @return The publication date of the service
	 */
	public GregorianCalendar getPublicationDate() 
	{
		return publicationDate;
	}
	
	/**
	 * Returns the deadline of the service
	 * @return The deadline of the service
	 */
	public GregorianCalendar getDeadline() 
	{
		return deadline;
	}
	
	/**
	 * Sets the deadline of the service
	 * @param The deadline of the service
	 */
	public void setDeadline(GregorianCalendar deadline) 
	{
		this.deadline = deadline;
	}
}
