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

    public Iterator filter(Iterator conditions) {
        return dao.filter("Supplier", conditions);
    }

    public Iterator getDistinct(String string) {
        return dao.getDistinct("supplier", string);
    }

    @Override
    public void addSupplier(Supplier supp, SupplierContact sc) {
        // TODO Auto-generated method stub
        supplier.setName(supp.getName());
        supplier.setCountry(supp.getCountry());
        supplier.setState(supp.getState());
        supplier.setCity(supp.getCity());
        dao.add("Supplier", supplier);
        System.out.println("START TO");
        dao.add("SupplierContact", sc);
//        Iterator<SupplierContact> i = supplier.getSupplierContactList(); 
        System.out.println("SUPPLIER CONTACT "+ sc);
        Iterator i = supplier.getSupplierContactList();
        while (i.hasNext()) {
            dao.add("SupplierContact", i.next());
            System.out.println("HI PUMASOK NA: "+i.next().toString());
            System.out.println("WILL NOTIFY OBSERVER oh yeah");
        }
        System.out.println("WILL NOTIFY OBSERVER");
        
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
        System.out.println("may nagregister start");
        o.update();
        observerList.add(o);
        System.out.println("may nagregister end");
    }

    @Override
    public void unregisterObserver(Observer o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyObserver() {
        System.out.println("NOTIFY BERI HARD" + observerList.size());
        for (Observer o : observerList) {
            o.update();
            System.out.println("PUMASOK NOTIFY");
        }
    }

}
