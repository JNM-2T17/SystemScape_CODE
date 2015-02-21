/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caista.model;

import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author Christian Gabriel
 */
public class Supplier {

    private String name;
    private String address;
    private ArrayList<SupplierContact> contacts;

    public Supplier(String name, String address) {
        contacts = new ArrayList<SupplierContact>();
        setName(name);
        setAddress(address);
    }
    
    public Supplier() {
        contacts = new ArrayList<SupplierContact>();
        setName("");
        setAddress("");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public SupplierContact getSupplierContact(int index) {
        if (index < 0 || index >= contacts.size()) {
            return null;
        }
        return contacts.get(index);
    }

    public Iterator getSupplierContact() {
        return contacts.iterator();
    }

    public void addSupplierContact(String supplier, String type, int value) {
        contacts.add(new SupplierContact(supplier, type, value));
    }

    public void setSupplierContact(ArrayList<SupplierContact> contacts) {
        this.contacts = contacts;
    }

}
