package model;

public class HardwareItem extends InventoryItem {
	
	public HardwareItem(int ID, String name, String description, float unitPrice, String invoiceNo, String location, String status, String classification) {
		 super(ID, name, description, unitPrice, invoiceNo, location, status, classification);
	}

	public HardwareItem() {
		super();
		// TODO Auto-generated constructor stub
	}
}