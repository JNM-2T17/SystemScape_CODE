/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Laptop
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import view.Observer;
import view.Subject;
import model.Warranty;
import model.Supplier;
import model.database.DAO;

/**
 *
 * @author Christian Gabriel
 */
public class WarrantyController implements Subject{

    private static WarrantyController instance;
    private DAO dao;
    private Warranty warranty;
    private ArrayList<Observer> observerList;

    public WarrantyController() {
        dao = DAO.getInstance();
        warranty = new Warranty();
        observerList = new ArrayList();
    }
    
    public static WarrantyController getInstance() {
        if (instance == null) {
            instance = new WarrantyController();
        }
        return instance;
    }
    
     public Iterator search(String searchStr){
        return dao.search("Warranty", searchStr);
    }
    

    public Warranty getWarranty() {
        return warranty;
    }
    
    public Object getObject(String key){
        return dao.get("warranty", key);
    }

    @Override
    public void registerObserver(Observer o) {
        o.update();
        observerList.add(o);
    }

    @Override
    public void unregisterObserver(Observer o) {
        // TODO Auto-generated method stub
        observerList.remove(o);
    }

    @Override
    public void notifyObserver() {
        // TODO Auto-generated method stub
        for (Observer o : observerList) {
            o.update();
        }
    }
    
    public Iterator getAll(){
        return dao.get("Warranty");
    }
    
    public void addWarranty(Warranty warranty) {
		// TODO Auto-generated method stub
        // Supplier supplier = (Supplier) dao.get("Supplier", supplierName);
        this.warranty.setHardware(warranty.getHardware());
        this.warranty.setStartDate(warranty.getStartDate());
        this.warranty.setEndDate(warranty.getEndDate());

        dao.add("Warranty", this.warranty);
    }

    
    public void editWarranty(Warranty warranty) {
		// TODO Auto-generated method stub

    }
}