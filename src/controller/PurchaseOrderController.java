/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import model.ItemData;
import model.PurchaseOrder;
import model.database.DAO;

/**
 *
 * @author Christian Gabriel
 */
public class PurchaseOrderController {

    private DAO dao;
    //PurchaseOrderView view;
    private PurchaseOrder po = null;
    private ArrayList<ItemData> itemData;

    public PurchaseOrderController() {
        dao = DAO.getInstance();
        //view = new PurchaseOrderView();
        po = new PurchaseOrder();
        itemData = new ArrayList();
    }

    public void addPurchaseOrder(Date date, int idNo, String type, String supplierName) {//SUBMIT AL SA PURCHASE ORDER

        po.setDate(date);
        po.setIdNo(idNo);
        po.setType(type);
        po.setSupplier(supplierName);
        Iterator<ItemData> iterator = po.getItems();

        dao.add("PurchaseOrder", po);
        while(iterator.hasNext()){
            dao.add("ItemData", iterator.next());
        }
        
    }

    public void addItem(String name, String description, float unitPrice, int qty) {//SUBMIT AL SA ADD ITEM
        
        po.addItem(name, description, unitPrice, qty);
        //dao.add("ItemData", new ItemData(name, description, unitPrice));
    }
}
