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
public class SupplierContact {

    private String supplier;
    private String type;
    private int value;
    
    public SupplierContact(String supplier, String type, int value) {
        setSupplier(supplier);
        setType(type);
        setValue(value);
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
