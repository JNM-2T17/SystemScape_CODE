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

/**
 *
 * @author Christian Gabriel
 */
public class SupplierController {

    private DAO dao;
    private Supplier supplier;
    private ArrayList<SupplierContact> supplierContacts;
    //private SupplierView view;

    public SupplierController() {
        dao = DAO.getInstance();
        //view = new SupplierView();
        supplier = new Supplier();
        supplierContacts = new ArrayList();
    }

    public void addSupplier(String name, String address) {

        supplier.setName(name);
        supplier.setAddress(address);
        Iterator<SupplierContact> iterator = supplier.getSupplierContact();
        
        dao.add("Supplier", supplier);
        while(iterator.hasNext()){
            dao.add("SupplierContact", iterator.next());
        }
    }

    public void addSupplierContact(String name, String type, int value) {
        supplier.addSupplierContact(name, type, value);
    }

}
