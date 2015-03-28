/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
    private Supplier supplier;
    private HashMap<ItemData, Integer> item;
    private String invoiceNo;

    public PurchaseOrder() {
        setDate(null);
        setIdNo(0);
        setType("");
        item = new HashMap<ItemData, Integer>();
        setSupplier(null);
    }

    public PurchaseOrder(Date date, int idNo, String type, Supplier supplier, String invoiceNo) {
        setDate(date);
        setIdNo(idNo);
        setType(type);
        setInvoiceNo(invoiceNo);
        item = new HashMap<ItemData, Integer>();
        setSupplier(supplier);
    }
    
    public String getInvoiceNo(){
        return invoiceNo;
    }
    
    public void setInvoiceNo(String invoiceNo){
        this.invoiceNo = invoiceNo;
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

    public Iterator getItems() {
        return item.keySet().iterator();
    }
    
    public Iterator getItemData() {
        return item.entrySet().iterator();
    }

    public int getQuantity(ItemData itemData) {
        return item.get(itemData);
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public float computeTotal(ItemData itemData) {
        return itemData.getUnitPrice() * item.get(itemData);
    }

    public float computeGrandTotal() {
        int total = 0;
        for (Iterator<ItemData> i = item.keySet().iterator(); i.hasNext();) {
            ItemData key = i.next();
            total += computeTotal(key);
        }
        return total;
    }
}
