package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import model.InventoryItem;

public class InventoryQuantityDAO implements IDBGet {

	@Override
	public Iterator get() {
		// TODO Auto-generated method stub
		ArrayList<InventoryItem> inventoryItems = new ArrayList<InventoryItem>();
		Connection con = DBConnection.getConnection();

		try {
			String query = "select * from `Count Inventory`";

			PreparedStatement preparedStatement = con.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				InventoryItem inventoryItem = new InventoryItem.InventoryItemBuilder()
						.addName(resultSet.getString("Item"))
						.addDescription(resultSet.getString("Description"))
						.addClassification(resultSet.getString("Type"))
						.addQuantity(resultSet.getInt("Quantity")).build();
				inventoryItems.add(inventoryItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(inventoryItems);
		return inventoryItems.iterator();
	}

	@Override
	public Object get(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator search(String searchStr) {
		// TODO Auto-generated method stub
		return null;
	}

}
