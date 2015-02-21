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
public class ItemData {

    private String name;
    private String description;
    private float unitPrice;

    public ItemData(String name, String description, float unitPrice) {
        setName(name);
        setDescription(description);
        setUnitPrice(unitPrice);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

}
