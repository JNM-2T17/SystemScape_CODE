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
    public NonITAsset(int ID, String name, String description, float unitPrice, String invoiceNo, String location, String status, String classification) {
        super(ID, name, description, unitPrice, invoiceNo, location, status, classification);
    }   
    
    public NonITAsset(NonITAssetBuilder niab) {
        super(niab.ID, niab.name, niab.description, niab.unitPrice, niab.invoiceNo, niab.location, niab.status, niab.classification);
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
			public NonITAsset build() {
				return new NonITAsset(this);
			}
		
		
		
	}
}
