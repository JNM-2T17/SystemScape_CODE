package controller;

import model.ItemData;

public interface ItemDataInterface {
	public void addItem(ItemData item, int qty);
	public void editItem(ItemData item, int qty);
}
