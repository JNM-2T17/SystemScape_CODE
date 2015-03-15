/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import model.SupplierContact;

/**
 *
 * @author Christian Gabriel
 */
public class DAO {

    private static DAO instance = null;
    private ArrayList<IDBGet> data;
    private static String password = "";

    private DAO() {
        password = "PASSWORD";//tempo
        BufferedReader br = null;

//        try {
//            br = new BufferedReader(
//                    new FileReader(new File("config.txt")));
//            password = br.readLine();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException ioe) {
//                    ioe.printStackTrace();
//                }
//            }
//        }
        data = new ArrayList<IDBGet>();
        data.add(new PurchaseOrderDAO());
        data.add(new SupplierDAO());
//        data.add(new WarrantyDAO());
//        data.add(new ContractDAO());
        data.add(new InventoryItemDAO());
        data.add(new ItemDataDAO());
        data.add(new SupplierContactDAO());
        data.add(new EmployeeDAO());
        data.add(new WarrantyDAO());
        data.add(new ContractDAO());
        data.add(new UserDAO());
        data.add(new AssignmentDAO());
    }

    public static DAO getInstance() {
        if (instance == null) {
            instance = new DAO();
        }
        return instance;
    }
    
    public Iterator filter( String table, Iterator conditions ){
        if( table.equalsIgnoreCase( "purchaseorder" ) )
		return ((PurchaseOrderDAO)data.get(0)).filter(conditions);
	else if( table.equalsIgnoreCase( "supplier" ) )
		return ((SupplierDAO)data.get(1)).filter(conditions);
        else if( table.equalsIgnoreCase( "inventoryitem" ) )
		return ((InventoryItemDAO)data.get(2)).filter(conditions);
        else if( table.equalsIgnoreCase( "employee" ) )
                return ((EmployeeDAO)data.get(5)).filter(conditions);
	return null;
    }
    
    public Iterator getDistinct( String table, String string ){
        if( table.equalsIgnoreCase( "purchaseorder" ) )
		return ((PurchaseOrderDAO)data.get(0)).getDistinct(string);
	else if( table.equalsIgnoreCase( "supplier" ) )
		return ((SupplierDAO)data.get(1)).getDistinct(string);
        else if( table.equalsIgnoreCase( "inventoryitem" ) )
		return ((InventoryItemDAO)data.get(2)).getDistinct(string);
        else if( table.equalsIgnoreCase( "employee" ) )
                return ((EmployeeDAO)data.get(5)).getDistinct(string);
	return null;
    }
    
   
    public Iterator get( String table )
	{
		if( table.equalsIgnoreCase( "purchaseorder" ) )
			return data.get(0).get();
		else if( table.equalsIgnoreCase( "supplier" ) )
			return data.get(1).get();
                else if( table.equalsIgnoreCase( "inventoryitem" ) )
			return data.get(2).get();
                else if( table.equalsIgnoreCase( "itemdata" ) )
			return data.get(3).get();
                else if( table.equalsIgnoreCase( "suppliercontact" ) )
			return data.get(4).get();
                else if( table.equalsIgnoreCase( "employee" ) )
			return data.get(5).get();
                else if( table.equalsIgnoreCase( "warranty" ) )
			return data.get(6).get();
                else if( table.equalsIgnoreCase( "contract" ) )
			return data.get(7).get();
                else if( table.equalsIgnoreCase( "assignment" ) )
			return data.get(9).get();
		return null;
	}
	
	/**
	* returns all items in a special table
	* @param table table to search
	* @param key key of result set (theater name)
	* @param arg0 index 0 of result set (floor no)
	* @param arg1 index 1 of result set (column no)
	* @return all items in this table
	*/
//	public ResultSet get( String table, String key, int arg0, int arg1 )
//	{
//		CinemaDAO cdao = (CinemaDAO)data.get(0);
//		
//		if( table.equalsIgnoreCase(FLOOR) )
//			return cdao.getFloors( key ); 
//		else if( table.equalsIgnoreCase(COLUMN) )
//			return cdao.getColumns( key, arg0 );
//		else if( table.equalsIgnoreCase(SEAT))
//			return cdao.getSeats( key, arg0, arg1 );
//		
//		return null;
//	}
	
	/**
	* returns the specific object in the given table with key as a primary key
	* @param table table to search
	* @param key primary key
	* @return the specific object with key as a primary key
	*/
	public Object get( String table, String key )
	{
		if( table.equalsIgnoreCase( "purchaseorder" ) )
			return data.get(0).get(key);
		else if( table.equalsIgnoreCase( "supplier" ) )
			return data.get(1).get(key);
                else if( table.equalsIgnoreCase( "inventoryitem" ) )
			return data.get(2).get(key);
                else if( table.equalsIgnoreCase( "itemdata" ) )
			return data.get(3).get(key);
                else if( table.equalsIgnoreCase( "suppliercontact" ) )
			return data.get(4).get(key);
                else if( table.equalsIgnoreCase( "employee" ) )
			return data.get(5).get(key);
                else if( table.equalsIgnoreCase( "warranty" ) )
			return data.get(6).get(key);
                else if( table.equalsIgnoreCase( "contract" ) )
			return data.get(7).get(key);
                else if( table.equalsIgnoreCase( "user" ) )
                        return data.get(8).get(key);
                else if( table.equalsIgnoreCase( "assignment" ) )
			return data.get(9).get(key);
		return null;
	}
	
	/**
	* returns all objects in the given table with searchStr in its primary key
	* @param table table to search
	* @param searchStr search key
	* @return all objects in this table with searchStr in its primary key
	*/
	public Iterator search( String table, String searchStr )
	{
		if( table.equalsIgnoreCase( "purchaseorder" ) )
			return data.get(0).search(searchStr);
		else if( table.equalsIgnoreCase( "supplier" ) )
			return data.get(1).search(searchStr);
                else if( table.equalsIgnoreCase( "inventoryitem" ) )
			return data.get(2).search(searchStr);
                else if( table.equalsIgnoreCase( "itemdata" ) )
			return data.get(3).search(searchStr);
                else if( table.equalsIgnoreCase( "suppliercontact" ) )
			return data.get(4).search(searchStr);
                else if( table.equalsIgnoreCase( "employee" ) )
			return data.get(5).search(searchStr);
                else if( table.equalsIgnoreCase( "warranty" ) )
			return data.get(6).search(searchStr);
                else if( table.equalsIgnoreCase( "contract" ) )
			return data.get(7).search(searchStr);
                else if( table.equalsIgnoreCase( "assignment" ) )
			return data.get(9).search(searchStr);
		return null;
	}
	
	/**
	* returns reports based on mode
	* @param mode whether DAILY, WEEKLY, or MONTHLY
	* @param month month of reports
	* @param year year of reports
	* @return reports based on mode
	*/
	public ResultSet getReports( int mode, int month, int year )
	{
		return null;
	}
	
	/**
	* adds obj to the given table in the database
	* @param table table to add to 
	* @param obj object to add
	*/
	public void add( String table, Object obj )
	{
		if( table.equalsIgnoreCase( "purchaseorder" ) )
			((IDBCUD)data.get(0)).add( obj );
		else if( table.equalsIgnoreCase( "supplier" ) )
			((IDBCUD)data.get(1)).add( obj );
                else if( table.equalsIgnoreCase( "inventoryitem" ) )
			((IDBCUD)data.get(2)).add( obj );
                else if( table.equalsIgnoreCase( "itemdata" ) )
			((IDBCUD)data.get(3)).add( obj );
                else if( table.equalsIgnoreCase( "suppliercontact" ) )
			((IDBCUD)data.get(4)).add( obj );
                else if( table.equalsIgnoreCase( "employee" ) )
			((IDBCUD)data.get(5)).add( obj );
                else if( table.equalsIgnoreCase( "warranty" ) )
			((IDBCUD)data.get(6)).add( obj );
                else if( table.equalsIgnoreCase( "contract" ) )
			((IDBCUD)data.get(7)).add( obj );
                else if( table.equalsIgnoreCase( "assignment" ) )
			((IDBCUD)data.get(9)).add( obj );
	}
	
	/**
	* updates the object with key origKey with the values in obj in given table
	* @param table table to update into
	* @param obj new values of object
	* @param origKey original key of object
	*/
	public void update( String table, Object obj, String origKey )
	{
		if( table.equalsIgnoreCase( "purchaseorder" ) )
			((IDBCUD)data.get(0)).update(obj, origKey);
		else if( table.equalsIgnoreCase( "supplier" ) )
			((IDBCUD)data.get(1)).update(obj, origKey);
                else if( table.equalsIgnoreCase( "inventoryitem" ) )
			((IDBCUD)data.get(2)).update(obj, origKey);
                else if( table.equalsIgnoreCase( "itemdata" ) )
			((IDBCUD)data.get(3)).update(obj, origKey);
                else if( table.equalsIgnoreCase( "suppliercontact" ) )
			((IDBCUD)data.get(4)).update(obj, origKey);
                else if( table.equalsIgnoreCase( "employee" ) )
			((IDBCUD)data.get(5)).update(obj, origKey);
                else if( table.equalsIgnoreCase( "warranty" ) )
			((IDBCUD)data.get(6)).update(obj, origKey);
                else if( table.equalsIgnoreCase( "contract" ) )
			((IDBCUD)data.get(7)).update(obj, origKey);
                else if( table.equalsIgnoreCase( "assignment" ) )
			((IDBCUD)data.get(9)).update(obj, origKey);
                
	}
	
	/**
	* deletes object in given table
	* @param table table to delete from
	* @param obj object to delete
	*/
	public void delete( String table, Object obj )
	{
		if( table.equalsIgnoreCase( "purchaseorder" ) )
			((IDBCUD)data.get(0)).delete(obj);
		else if( table.equalsIgnoreCase( "supplier" ) )
			((IDBCUD)data.get(1)).delete(obj);
                else if( table.equalsIgnoreCase( "inventoryitem" ) )
			((IDBCUD)data.get(2)).delete(obj);
                else if( table.equalsIgnoreCase( "itemdata" ) )
			((IDBCUD)data.get(3)).delete(obj);
                else if( table.equalsIgnoreCase( "suppliercontact" ) )
			((IDBCUD)data.get(4)).delete(obj);
                else if( table.equalsIgnoreCase( "employee" ) )
			((IDBCUD)data.get(5)).delete(obj);
                else if( table.equalsIgnoreCase( "warranty" ) )
			((IDBCUD)data.get(6)).delete(obj);
                else if( table.equalsIgnoreCase( "contract" ) )
			((IDBCUD)data.get(7)).delete(obj);
                else if( table.equalsIgnoreCase( "assignment" ) )
			((IDBCUD)data.get(9)).delete(obj);
                
	}
       
}
