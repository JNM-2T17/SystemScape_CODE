/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import view.Observer;
import view.Subject;
import model.ItemData;
import model.PurchaseOrder;
import model.Supplier;
import model.database.DAO;

/**
 *
 * @author Christian Gabriel
 */
public class PurchaseOrderController implements Subject, PurchaseOrderInterface, ItemDataInterface {

    private static PurchaseOrderController instance;
    private DAO dao;
    private PurchaseOrder po;
    private ArrayList<Observer> observerList;

    public PurchaseOrderController() {
        dao = DAO.getInstance();
        po = new PurchaseOrder();
        observerList = new ArrayList();
    }
    
    public void incrementQuantityReceived(String key, String itemName)
    {
		dao.incrementQuantityReceived(/***parameters here****/);
    }

    public void init() {
        po = new PurchaseOrder();
    }

    public static PurchaseOrderController getInstance() {
        if (instance == null) {
            instance = new PurchaseOrderController();
        }
        return instance;
    }

    public Iterator filter(Iterator conditions) {
        return dao.filter("PurchaseOrder", conditions);
    }

    public float computeAmount(ItemData i) {
        return po.computeTotal(i);
    }

    public PurchaseOrder getPurchaseOrder() {
        return po;
    }

    public void setPurchaseOrder(PurchaseOrder po) {
        this.po = po;
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

    public Iterator getAll() {
        return dao.get("PurchaseOrder");
    }

    @Override
    public void addItem(ItemData item, int qty) {
        // TODO Auto-generated method stub
        po.addItem(item.getName(), item.getDescription(), item.getUnitPrice(), qty);
        //editPurchaseOrder(po);
        notifyObserver();
    }

    @Override
    public void editItem(ItemData item, int qty, ItemData key) {
        // TODO Auto-generated method stub
        po.deleteItem(key);
        po.addItem(item.getName(), item.getDescription(), item.getUnitPrice(), qty);
        notifyObserver();
    }

    public float computeGrandTotal() {
        float total = 0;
        total = po.computeGrandTotal();
        //System.out.println("TOTAL ISIDE PO CONTROLLER: "+ total);
        return total;
    }

    @Override
    public void addPurchaseOrder(PurchaseOrder purchaseOrder) {
        // TODO Auto-generated method stub
        // Supplier supplier = (Supplier) dao.get("Supplier", supplierName);
        po.setDate(purchaseOrder.getDate());
        po.setIdNo(purchaseOrder.getIdNo());
        po.setType(purchaseOrder.getType());
        po.setSupplier(purchaseOrder.getSupplier());

        for (Iterator<ItemData> i = po.getItems(); i.hasNext();) {
            dao.add("ItemData", i.next());
        }

        dao.add("PurchaseOrder", po);
        notifyObserver();
    }

    public boolean checkItemExists(String itemName) {
        Iterator<ItemData> items = po.getItems();
        ItemData id = null;
        while (items.hasNext()) {
            id = items.next();
            if (itemName.equals(id.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void editPurchaseOrder(PurchaseOrder purchaseOrder) {
        for (Iterator<ItemData> i = purchaseOrder.getItems(); i.hasNext();) {
            dao.add("ItemData", i.next());
        }
        dao.update("purchaseorder", purchaseOrder, "" + purchaseOrder.getIdNo());
        notifyObserver();
    }
}
