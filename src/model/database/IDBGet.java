/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.database;

/**
 *
 * @author Christian Gabriel
 */

import java.util.Iterator;

public interface IDBGet
{
	/**
	* returns all items in this table
	* @return all items in this table
	*/
	public Iterator get();
	
	/**
	* returns the specific object with key as a primary key
	* @param key primary key
	* @return the specific object with key as a primary key
	*/
	public Object get( String key );
	
	/**
	* returns all objects in this table with searchStr in its primary key
	* @param searchStr search key
	* @return all objects in this table with searchStr in its primary key
	*/
	public Iterator search( String searchStr );
}
