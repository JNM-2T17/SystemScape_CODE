/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caista.model;

/**
 *
 * @author Christian Gabriel
 */
public class InventoryItem {

    private ItemData itemData;
    private String status;
    private String classification;
    private int ID;

    public InventoryItem() {
        setItemData(null);
        setStatus("");
        setClassification("");
        setId(0);
    }
    
    public InventoryItem(ItemData itemData, String status, String classification, int id){
        setItemData(itemData);
        setStatus(status);
        setClassification(classification);
        setId(id);
    }

    public ItemData getItemData() {
        return this.itemData;
    }

    public void setItemData(ItemData itemData) {
        this.itemData = itemData;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClassification() {
        return this.classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public int getId() {
        return this.ID;
    }

    public void setId(int ID) {
        this.ID = ID;
    }
}
