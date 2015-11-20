
package services;

import java.util.GregorianCalendar;

/**
 * Classe permettant de représenter des services échangés.
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public class Service {
	
	/**Identifiant du service**/
	protected int id;
	
	/**Nom du service**/
	protected String name;
	/**Date de publication d'un service**/
	protected  GregorianCalendar datePublication;
	/**Date limite du service**/
	protected  GregorianCalendar dateLimite;
	
	public Service(String name, GregorianCalendar datePublication, GregorianCalendar dateLimite)
	{
		this.name = name;
		this.datePublication = datePublication;
		this.dateLimite = dateLimite;
	}
	/**
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}
	/**
	 * 
	 * @return
	 */
	public String getName() 
	{
		return name;
	}
	/**
	 * 
	 * @param name
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 
	 * @return
	 */
	public GregorianCalendar getDatePublication() 
	{
		return datePublication;
	}
	/**
	 * 
	 * @param datePublication
	 */
	public void setDatePublication(GregorianCalendar datePublication) 
	{
		this.datePublication = datePublication;
	}
	/**
	 * 
	 * @return
	 */
	public GregorianCalendar getDateLimite() 
	{
		return dateLimite;
	}
	/**
	 * 
	 * @param dateLimite
	 */
	public void setDateLimite(GregorianCalendar dateLimite) 
	{
		this.dateLimite = dateLimite;
	}
}
