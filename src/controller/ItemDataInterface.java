package controller;

import model.ItemData;

public interface ItemDataInterface {
	public void addItem(ItemData item, int qty, int qtyRcvd);
	public void editItem(ItemData item, int qty, ItemData key);
}
