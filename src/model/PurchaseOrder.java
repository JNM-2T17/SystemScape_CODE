/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caista.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Christian Gabriel
 */
public class PurchaseOrder {

    private Date date;
    private int idNo;
    private String type;
    private String supplier;
    private HashMap<ItemData, Integer> item;

    public PurchaseOrder() {
        setDate(null);
        setIdNo(0);
        setType("");
        item = new HashMap<ItemData, Integer>();
        setSupplier("");
    }
    
    public PurchaseOrder(Date date, int idNo, String type, String supplier) {
        setDate(date);
        setIdNo(idNo);
        setType(type);
        item = new HashMap<ItemData, Integer>();
        setSupplier(supplier);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdNo() {
        return idNo;
    }

    public void setIdNo(int idNo) {
        this.idNo = idNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String string) {
        type = string;
    }

    public void addItem(String name, String description, float unitPrice, int qty) {
        ItemData itemData = new ItemData(name, description, unitPrice);
        item.put(itemData, qty);
    }
    
    public Iterator getItems(){
        return item.keySet().iterator();
    }
    
    public int getQuantity(ItemData itemData) {
        return item.get(itemData);
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int computeTotal() {
        int total = 0;
        Iterator<ItemData> keySetIterator = item.keySet().iterator();
        while (keySetIterator.hasNext()) {
            ItemData key = keySetIterator.next();
            total += key.getUnitPrice();
        }
        return total;
    }

//    public int computeGrandTotal() {
//        return 
//    }
}
