/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.InventoryItem;
import model.ItemData;
import model.database.DAO;

/**
 *
 * @author Christian Gabriel
 */
public class InventoryItemController {

    DAO dao;
    //InventoryItemView view;
    InventoryItem ii = null;

    public InventoryItemController() {
        dao = DAO.getInstance();
        //view = new InventoryItemView();
        ii = new InventoryItem();

    }

    public void addInventoryItem(String name, String description, float unitPrice, String status, String classification, int ID) {
        ItemData itemData = new ItemData(name, description, unitPrice);
        ii.setItemData(itemData);
        ii.setStatus(status);
        ii.setClassification(classification);
        ii.setId(ID);

        dao.add("InventoryItem", ii);
    }
}
