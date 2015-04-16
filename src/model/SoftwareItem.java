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
public class SoftwareItem extends InventoryItem {

    private String licenseKey;

    public SoftwareItem(int ID, String name, String description, float unitPrice, String invoiceNo, String location, String status, String classification, String licenseKey) {
        super(ID, name, description, unitPrice, invoiceNo, location, status, classification);
        setLicenseKey(licenseKey);
    }
    
    public SoftwareItem(SoftwareBuilder sb) {
        super(sb.ID, sb.name, sb.description, sb.unitPrice, sb.invoiceNo, sb.location, sb.status, sb.classification);
        setLicenseKey(sb.licenseKey);
    }   

    public String getLicenseKey() {
        return licenseKey;
    }

    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }
    
	public static class SoftwareBuilder {
		
		private int ID;
		private String name;
		private String description;
		private float unitPrice;
		private String invoiceNo;
		private String location;
		private String status;
		private String classification;
		private String licenseKey;
                private Date startDate;
                private Date endDate;
		
		public SoftwareBuilder()
		{
			ID = 0;
			name = "";
			description = "";
			unitPrice = 0;
			invoiceNo = "";
			location = "";
			status = "";
			classification = "";
			licenseKey = "";
                        startDate = null;
                        endDate = null;
		}
		
		public SoftwareBuilder addID(int buildID)
		{
			ID = buildID;
			return this;
		}
		public SoftwareBuilder addName(String buildName)
		{
			name = buildName;
			return this;
		}
		public SoftwareBuilder addDescription(String buildDesc)
		{
			description = buildDesc;
			return this;
		}
		public SoftwareBuilder addUnitPrice(float buildUnit)
		{
			unitPrice = buildUnit;
			return this;
		}
		public SoftwareBuilder addInvoiveNo(String buildInvoice)
		{
			invoiceNo = buildInvoice;
			return this;
		}
		public SoftwareBuilder addLocation(String buildLocation)
		{
			location = buildLocation;
			return this;
		}
		public SoftwareBuilder addStatus(String buildStatus)
		{
			status = buildStatus;
			return this;
		}
		public SoftwareBuilder addClassification(String buildClass)
		{
			classification = buildClass;
			return this;
		}
		public SoftwareBuilder addLicenseKey(String buildKey)
		{
			licenseKey = buildKey;
			return this;
		}
                public SoftwareBuilder addStartDate(Date startingDate){
			startDate = startingDate;
			return this;
		}
                 public SoftwareBuilder addEndDate(Date endingDate){
			endDate = endingDate;
			return this;
		}
		public SoftwareItem build() {
			return new SoftwareItem(this);
		}
	}
	
}
