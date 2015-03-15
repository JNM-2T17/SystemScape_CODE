/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Date;

import view.Content;

/**
 *
 * @author Christian Gabriel
 */

public class ITAsset extends HardwareItem {

    private int assetTag;
	private String serviceTag;
	private Date deliveryDate;
	private Contract contract;
        private Warranty warranty;
        
	public ITAsset()
	{
		super();
		assetTag = 0;
		serviceTag = "";
		deliveryDate = null;
	}
	
	public ITAsset(int ID, String name, String description, float unitPrice, String invoiceNo, String location, String status, String classification, int assetTag, String serviceTag, Date deliveryDate, Date warrantyStartDate, Date warrantyEndDate, Date contractStartDate, Date contractEndDate, float contractMaintenanceCost) {
		super(ID, name, description, unitPrice, invoiceNo, location, status, classification);
		setAssetTag(assetTag);
		setServiceTag(serviceTag);
		setDeliveryDate(deliveryDate);
                setWarranty(new Warranty(ID, warrantyStartDate, warrantyEndDate));
                setContract(new Contract(ID, contractStartDate, contractEndDate, contractMaintenanceCost));
	}
	
	private ITAsset(ITAssetBuilder itb)
	{
		super(itb.ID,itb.name,itb.description,itb.unitPrice,itb.invoiceNo,itb.location,itb.status,itb.classification);
		setAssetTag(itb.assetTag);
		setServiceTag(itb.serviceTag);
		setDeliveryDate(itb.deliveryDate);
                setContract(itb.contract);
                setWarranty(itb.warranty);
		
	}

	public ITAsset(int ID, int assetTag, String serviceTag, Date deliveryDate) {
		// TODO Auto-generated constructor stub
		super();
		super.setId(ID);
		setServiceTag(serviceTag);
		setDeliveryDate(deliveryDate);
	}

	public int getAssetTag() {
		return assetTag;
	}
	
	public String getServiceTag() {
		return serviceTag;
	}
        
        public Contract getContract(){
            return contract;
        }
        
        public Warranty getWarranty(){
            return warranty;
        }
        
        public Date getContractStartDate(){
            return contract.getStartDate();
        }
        
        public Date getContractEndDate(){
            return contract.getEndDate();
        }
        
        public float getContractMaintenanceCost(){
            return contract.getMaintenanceCost();
        }
        
        public Date getWarrantyStartDate(){
            return warranty.getStartDate();
        }
        
        public Date getWarrantyEndDate(){
            return warranty.getEndDate();
        }
        
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setAssetTag(int assetTag) {
		this.assetTag = assetTag;
	}

	public void setServiceTag(String serviceTag) {
		this.serviceTag = serviceTag;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
        public void setContract(Contract contract){
             this.contract = contract;
        }
        
        public void setWarranty(Warranty warranty){
             this.warranty = warranty;
        }
        
        public void setContractStartDate(Date contractStartDate){
            this.contract.setStartDate(contractStartDate);
        }
        
        public void setContractEndDate(Date contractEndDate){
            this.contract.setEndDate(contractEndDate);
        }
        
        public void setContractMaintenanceCost(int maintenanceCost){
            this.contract.setMaintenanceCost(maintenanceCost);
        }
        
        public void setWarrantyStartDate(Date warrantyStartDate){
            this.warranty.setStartDate(warrantyStartDate);
        }
        
        public void setWarrantyEndDate(Date warrantyStartDate){
            this.warranty.setEndDate(warrantyStartDate);
        }
        
	public static class ITAssetBuilder {
		
		private int ID;
		private String name;
		private String description;
		private float unitPrice;
		private String invoiceNo;
		private String location;
		private String status;
		private String classification;
	 	private int assetTag;
		private String serviceTag;
		private Date deliveryDate;
		private Contract contract;
                private Warranty warranty;
                
		public ITAssetBuilder()
		{
			ID = 0;
			name = "";
			description = "";
			unitPrice = 0;
			invoiceNo = "";
			location = "";
			status = "";
			classification = "";
			assetTag = 0;
			serviceTag = "";
			deliveryDate = null;
		}
		
		public ITAssetBuilder addID(int buildID)
		{
			ID = buildID;
			return this;
		}
		public ITAssetBuilder addName(String buildName)
		{
			name = buildName;
			return this;
		}
		public ITAssetBuilder addDescription(String buildDesc)
		{
			description = buildDesc;
			return this;
		}
		public ITAssetBuilder addUnitPrice(float buildUnit)
		{
			unitPrice = buildUnit;
			return this;
		}
		public ITAssetBuilder addInvoiveNo(String buildInvoice)
		{
			invoiceNo = buildInvoice;
			return this;
		}
		public ITAssetBuilder addLocation(String buildLocation)
		{
			location = buildLocation;
			return this;
		}
		public ITAssetBuilder addStatus(String buildStatus)
		{
			status = buildStatus;
			return this;
		}
		public ITAssetBuilder addClassification(String buildClass)
		{
			classification = buildClass;
			return this;
		}
		public ITAssetBuilder addAssetTag(int buildAsset)
		{
			assetTag = buildAsset;
			return this;
		}
		public ITAssetBuilder addServiceTag(String buildService)
		{
			serviceTag = buildService;
			return this;
		}
		public ITAssetBuilder addDeliveryDate(Date buildDelDate)
		{
			deliveryDate = buildDelDate;
			return this;
		}
                public ITAssetBuilder addContract(Contract buildContract)
		{
			contract = buildContract;
			return this;
		}
                public ITAssetBuilder addWarranty(Warranty buildWarranty)
		{
			warranty = buildWarranty;
			return this;
		}
		public ITAsset build() {
			return new ITAsset(this);
		}
		
		
		
	}
	
}


