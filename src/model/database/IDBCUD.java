/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package caista.model.database;

/**
 *
 * @author Christian Gabriel
 */

public interface IDBCUD extends IDBGet
{
	/**
	* adds obj to the database
	* @param obj object to add
	*/
	public void add( Object obj );
	
	/**
	* updates the object with key origKey with the values in obj
	* @param obj new values of object
	* @param origKey original key of object
	*/
	public void update( Object obj, String origKey );
	
	/**
	* deletes object
	* @param obj object to delete
	*/
	public void delete( Object obj );
}