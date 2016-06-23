/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Iterator;

import model.Supplier;
import model.SupplierContact;
import model.database.DAO;
import view.Observer;
import view.Subject;

/**
 *
 * @author Christian Gabriel
 */
public class SupplierController implements SupplierInterface, Subject {

    private static SupplierController instance = null;
    private DAO dao;
    private Supplier supplier;
    private ArrayList<Observer> observerList;

    public SupplierController() {
        dao = DAO.getInstance();
        observerList = new ArrayList();
        supplier = new Supplier();
    }

    public static SupplierController getInstance() {
        if (instance == null) {
            instance = new SupplierController();
        }
        return instance;
    }

    public void init() {
        supplier = new Supplier();
    }
    
    public void editSupplier(Supplier supp, String key){
    	
    	//System.out.println("boom punit "+supp.getName());
        supplier.setName(supp.getName());
        supplier.setCountry(supp.getCountry());
        supplier.setState(supp.getState());
        supplier.setCity(supp.getCity());
        dao.delete("suppliercontact" ,supp.getName());
        dao.update("Supplier", supplier, key);
        

        Iterator i = supp.getSupplierContactList();
        while (i.hasNext()) {
            dao.add("SupplierContact", (SupplierContact) i.next());
        }
        //System.out.println("SHIT" + SupplierController.getInstance().supplier.getName());
        notifyObserver();
    }
    
    public Iterator filter(Iterator conditions) {
        return dao.filter("Supplier", conditions);
    }

    public Iterator getDistinct(String string) {
        return dao.getDistinct("supplier", string);
    }

    @Override
    public void addSupplier(Supplier supp) {
        // TODO Auto-generated method stub
    	supplier = new Supplier();
        supplier.setName(supp.getName());
        supplier.setCountry(supp.getCountry());
        supplier.setState(supp.getState());
        supplier.setCity(supp.getCity());
        dao.add("Supplier", supplier);
        

        Iterator i = supp.getSupplierContactList();
        while (i.hasNext()) {
            dao.add("SupplierContact", (SupplierContact) i.next());

        }

        notifyObserver();
    }

    public Object getObject(String key) {
        return dao.get("Supplier", key);
    }

    public Iterator getAll() {
        return dao.get("Supplier");
    }

    @Override
    public void registerObserver(Observer o) {
        o.update();
        observerList.add(o); 
    }

    @Override
    public void unregisterObserver(Observer o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Supplier getSupplier() {
    	return supplier;
    }

    @Override
    public void notifyObserver() { 
        for (Observer o : observerList) {
            o.update();  
        }
    }

}
