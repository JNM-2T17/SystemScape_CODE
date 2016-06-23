/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Iterator;
import model.InventoryItem;
import model.ItemData;
import model.database.DAO;
import model.database.InventoryItemDAO;
import view.Observer;
import view.Subject;

/**
 *
 * @author Christian Gabriel
 */
public class InventoryItemController implements InventoryItemInterface, Subject {

    private static InventoryItemController instance;
    private DAO dao;
    private ArrayList<Observer> observerList;
    private InventoryItem inventoryItem;
    private InventoryItemDAO inventoryItemDAO;
    //InventoryItemView view;

    public InventoryItemController() {
        dao = DAO.getInstance();
        observerList = new ArrayList();
        inventoryItemDAO = new InventoryItemDAO();
    }
    
    public static InventoryItemController getInstance() {
        if (instance == null) {
            instance = new InventoryItemController();
        }
        return instance;
    }
    
    public Object get(String key){
        return dao.get("inventoryitem", key);
    }

    @Override
    public void addInventoryItem(InventoryItem invItem) {
        // TODO Auto-generated method stub
        dao.add("ItemData", new ItemData(invItem.getName(), invItem.getDescription(), invItem.getUnitPrice()));
        dao.add("InventoryItem", invItem);
        notifyObserver();
        WarrantyController.getInstance().notifyObserver();
        ContractController.getInstance().notifyObserver();
    }

    public void editInventoryItem(InventoryItem inventoryItem, String origKey){
        //System.out.println(inventoryItem.getName()+"wow\n");
	dao.update("InventoryItem", inventoryItem, origKey);
	notifyObserver();
        WarrantyController.getInstance().notifyObserver();
        ContractController.getInstance().notifyObserver();
    }
    
    public void deleteInventoryItem(InventoryItem inventoryItem){
    	dao.delete("InventoryItem", inventoryItem);
    	notifyObserver();
        WarrantyController.getInstance().notifyObserver();
        ContractController.getInstance().notifyObserver();
    }

    public Iterator getAll() {
        return dao.get("InventoryItem");
    }

    public Iterator filter(Iterator conditions) {
        return dao.filter("InventoryItem", conditions);
    }
    
    public Iterator getDistinct(String string){
        return dao.getDistinct("InventoryItem", string);
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

    @Override
    public void notifyObserver() {
        for (Observer o : observerList) {
            o.update();
        }
    }

	public Iterator getAllQuantity() {
		return dao.get("InventoryItemQuantity");
	}
    
    public int getID(){
        return inventoryItemDAO.getID();
    }
}
