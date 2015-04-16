/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Christian Gabriel
 */
public class InventoryItem {

   private ItemData itemData;
    private String status;
    private String classification;
    private int ID;
    private String invoiceNo;
    private String location;
    private int quantity;
    private Date startDate;
    private Date endDate;

    public InventoryItem(int ID, String name, String description, float unitPrice, String invoiceNo, String location, String status, String classification) {
        itemData = new ItemData(name, description, unitPrice);
        setStatus(status);
        setClassification(classification);
        setId(ID);
        setInvoiceNo(invoiceNo);
        setLocation(location);
    }
    public InventoryItem(InventoryItemBuilder iib) {
        itemData = new ItemData(iib.name, iib.description, iib.unitPrice);
        setStatus(iib.status);
        setClassification(iib.classification);
        setId(iib.ID);
        setInvoiceNo(iib.invoiceNo);
        setLocation(iib.location);
        setQuantity(iib.quantity);
    }

    public InventoryItem() {
		// TODO Auto-generated constructor stub
    	itemData = new ItemData();
    	status = "";
    	classification = "";
    	ID = 0;
    	invoiceNo = "";
    	location = "";
	}

	public String getName() {
        return itemData.getName();

    }

    public void setName(String name) {
        itemData.setName(name);
    }

    public String getDescription() {
        return itemData.getDescription();
    }

    public void setDescription(String description) {
        itemData.setDescription(description);
    }

    public float getUnitPrice() {
        return itemData.getUnitPrice();
    }

    public void setUnitPrice(float unitPrice) {
        itemData.setUnitPrice(unitPrice);
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

    public int getID() {
        return this.ID;
    }

    public void setId(int ID) {
        this.ID = ID;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }
    
    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;

    }
    public int getQuantity(){
    	return quantity;
    }
    public void setQuantity(int quantity){
    	this.quantity=quantity;
    }
    
    public void setStartDate(Date startDate){
    	this.startDate = startDate;
    }
    
    public void setEndDate(Date endDate){
    	this.endDate = endDate;
    }
    
    public Date getStartDate(){
    	return startDate;
    }
    
    public Date getEndDate(){
    	return endDate;
    }
    
public static class InventoryItemBuilder {
		
		private int ID;
		private String name;
		private String description;
		private float unitPrice;
		private String invoiceNo;
		private String location;
		private String status;
		private String classification;
		private int quantity;
                private Date startDate;
                private Date endDate;
		
		public InventoryItemBuilder()
		{
			ID = 0;
			name = "";
			description = "";
			unitPrice = 0;
			invoiceNo = "";
			location = "";
			status = "";
			classification = "";
			quantity=0;
                        startDate = null;
                        endDate = null;
		}
		
		public InventoryItemBuilder addID(int buildID)
		{
			ID = buildID;
			return this;
		}
		public InventoryItemBuilder addName(String buildName)
		{
			name = buildName;
			return this;
		}
		public InventoryItemBuilder addDescription(String buildDesc)
		{
			description = buildDesc;
			return this;
		}
		public InventoryItemBuilder addUnitPrice(float buildUnit)
		{
			unitPrice = buildUnit;
			return this;
		}
		public InventoryItemBuilder addInvoiveNo(String buildInvoice)
		{
			invoiceNo = buildInvoice;
			return this;
		}
		public InventoryItemBuilder addLocation(String buildLocation)
		{
			location = buildLocation;
			return this;
		}
		public InventoryItemBuilder addStatus(String buildStatus)
		{
			status = buildStatus;
			return this;
		}
		public InventoryItemBuilder addClassification(String buildClass)
		{
			classification = buildClass;
			return this;
		}
		public InventoryItemBuilder addQuantity(int buildQuantity){
			quantity=buildQuantity;
			return this;
		}
                public InventoryItemBuilder addStartDate(Date startingDate){
			startDate = startingDate;
			return this;
		}
                 public InventoryItemBuilder addEndDate(Date endingDate){
			endDate = endingDate;
			return this;
		}
		public InventoryItem build() {
			return new InventoryItem(this);
		}
		
		
		
	}
}
