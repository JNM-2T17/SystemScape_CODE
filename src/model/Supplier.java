/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Christian Gabriel
 */
public class Supplier {

    private String name;
    private String country;
    private String state;
    private String city;
    private ArrayList<SupplierContact> contacts;

    public Supplier(String name, String country, String state, String city) {
        contacts = new ArrayList<SupplierContact>();
        setName(name);
        setCountry(country);
        setState(state);
        setCity(city);
    }
    
    public Supplier() {
        contacts = new ArrayList<SupplierContact>();
        setName("");
        setCountry("");
        setState("");
        setCity("");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public SupplierContact getSupplierContact(int index) {
        if (index < 0 || index >= contacts.size()) {
            return null;
        }
        return contacts.get(index);
    }

    public Iterator getSupplierContactList() {
        return contacts.iterator();
    }

    public void addSupplierContact(String supplier, String type, String info) {
        contacts.add(new SupplierContact(supplier, type, info));
    }

    public void setSupplierContactList(Iterator contacts) {
        while(contacts.hasNext()){
            this.contacts.add(((SupplierContact)contacts.next()));
        }
    }

}
