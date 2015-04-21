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
public class NonITAsset extends HardwareItem{
    private Warranty warranty;
    private Contract contract;
        
    public NonITAsset(int ID, String name, String description, float unitPrice, String invoiceNo, String location, String status, String classification, Date warrantyStartDate, Date warrantyEndDate, Date contractStartDate, Date contractEndDate, float contractMaintenanceCost) {
        super(ID, name, description, unitPrice, invoiceNo, location, status, classification);
        setWarranty(new Warranty(ID, warrantyStartDate, warrantyEndDate));
        setContract(new Contract(ID, contractEndDate, contractEndDate, contractMaintenanceCost));
    }   
    
    public NonITAsset(NonITAssetBuilder niab) {
        super(niab.ID, niab.name, niab.description, niab.unitPrice, niab.invoiceNo, niab.location, niab.status, niab.classification);
        setWarranty(niab.warranty);
        setContract(niab.contract);
    }   
        
        public Warranty getWarranty(){
            return warranty;
        }
        
        public Date getWarrantyStartDate(){
            return warranty.getStartDate();
        }
        
        public Date getWarrantyEndDate(){
            return warranty.getEndDate();
        }
        
        public void setWarranty(Warranty warranty){
             this.warranty = warranty;
        }
        
        public void setWarrantyStartDate(Date warrantyStartDate){
            this.warranty.setStartDate(warrantyStartDate);
        }
        
        public void setWarrantyEndDate(Date warrantyStartDate){
            this.warranty.setEndDate(warrantyStartDate);
        }
        
        public Contract getContract(){
            return contract;
        }
        
        public Date getContractStartDate(){
            return contract.getStartDate();
        }
        
        public Date getContractEndDate(){
            return contract.getEndDate();
        }
        
        public float getMaintenanceCost(){
            return contract.getMaintenanceCost();
        }
        
        public void setContract(Contract contract){
             this.contract = contract;
        }
        
        public void setContractStartDate(Date contractStartDate){
            this.contract.setStartDate(contractStartDate);
        }
        
        public void setContractEndDate(Date contractStartDate){
            this.contract.setEndDate(contractStartDate);
        }
        
        public void setMaintenanceCost(float maintenanceCost){
            this.contract.setMaintenanceCost(maintenanceCost);
        }
        
	public static class NonITAssetBuilder {
			
			private int ID;
			private String name;
			private String description;
			private float unitPrice;
			private String invoiceNo;
			private String location;
			private String status;
			private String classification;
                        private Date startDate;
                        private Date endDate;
                        private Warranty warranty;
                        private Contract contract;
			
			public NonITAssetBuilder()
			{
				ID = 0;
				name = "";
				description = "";
				unitPrice = 0;
				invoiceNo = "";
				location = "";
				status = "";
				classification = "";
                                startDate = null;
                                endDate = null;
			}
			
			public NonITAssetBuilder addID(int buildID)
			{
				ID = buildID;
				return this;
			}
			public NonITAssetBuilder addName(String buildName)
			{
				name = buildName;
				return this;
			}
			public NonITAssetBuilder addDescription(String buildDesc)
			{
				description = buildDesc;
				return this;
			}
			public NonITAssetBuilder addUnitPrice(float buildUnit)
			{
				unitPrice = buildUnit;
				return this;
			}
			public NonITAssetBuilder addInvoiveNo(String buildInvoice)
			{
				invoiceNo = buildInvoice;
				return this;
			}
			public NonITAssetBuilder addLocation(String buildLocation)
			{
				location = buildLocation;
				return this;
			}
			public NonITAssetBuilder addStatus(String buildStatus)
			{
				status = buildStatus;
				return this;
			}
			public NonITAssetBuilder addClassification(String buildClass)
			{
				classification = buildClass;
				return this;
			}
                        public NonITAssetBuilder addStartDate(Date startingDate){
                            startDate = startingDate;
                            return this;
                        }
                        public NonITAssetBuilder addEndDate(Date endingDate){
                            endDate = endingDate;
                            return this;
                        }
                        public NonITAssetBuilder addWarranty(Warranty buildWarranty)
                        {
                            warranty = buildWarranty;
                            return this;
                        }
                        public NonITAssetBuilder addContract(Contract buildContract)
                        {
                            contract = buildContract;
                            return this;
                        }
			public NonITAsset build() {
				return new NonITAsset(this);
			}
		
		
		
	}
}
