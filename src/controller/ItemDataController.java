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
import model.database.ItemDataDAO;
import view.Observer;
import view.Subject;

/**
 *
 * @author Christian Gabriel
 */
public class ItemDataController {

    private static ItemDataController instance;
    private DAO dao;
    private ArrayList<Observer> observerList;
    private ItemData itemData;
    //InventoryItemView view;

    public ItemDataController() {
        dao = DAO.getInstance();
        observerList = new ArrayList();
    }
    
    public static ItemDataController getInstance() {
        if (instance == null) {
            instance = new ItemDataController();
        }
        return instance;
    }
    
    public Object get(String key){
        return dao.get("itemdata", key);
    }


}
