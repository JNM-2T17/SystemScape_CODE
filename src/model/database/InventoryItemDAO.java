/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caista.model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import model.InventoryItem;
import model.ItemData;

/**
 *
 * @author Christian Gabriel
 */
public class InventoryItemDAO implements IDBCUD {

    public Iterator get() {
        ArrayList<InventoryItem> inventoryItem = new ArrayList<InventoryItem>();

        try {
            String query = "SELECT * FROM inventoryitem ii , itemdata id WHERE ii.itemdata = id.name";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ItemData itemData = new ItemData(rs.getString("name"), rs.getString("description"), rs.getFloat("unitPrice"));

                InventoryItem ii = new InventoryItem(itemData, rs.getString("status"), rs.getString("classification"), rs.getInt("ID"));

                inventoryItem.add(ii);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return inventoryItem.iterator();
    }

    public Object get(String key) {
        try {
            String query = "SELECT * FROM inventoryitem ii , itemdata id WHERE ii.itemdata = id.name AND ID = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(query);
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ItemData id = new ItemData(rs.getString("name"), rs.getString("description"), rs.getFloat("unitPrice"));
               
                InventoryItem ii = new InventoryItem( id, rs.getString("status"), rs.getString("classification"),rs.getInt("ID"));

                return ii;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return null;

    }

    public Iterator search(String searchStr) {
        ArrayList<InventoryItem> inventoryItem = new ArrayList<InventoryItem>();
        return inventoryItem.iterator();
    }

    public void add(Object obj) {

        InventoryItem ii = (InventoryItem) obj;

        try {

            String stmt = "INSERT INTO inventoryitem VALUES(?,?,?,?);";
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(stmt);
            ps.setInt(1, ii.getId());
            ps.setString(2, ii.getItemData().getName());
            ps.setString(3, ii.getStatus());
            ps.setString(4, ii.getClassification());
            ps.execute();

        } catch (SQLException se) {
            se.printStackTrace();
        }

    }

    public void update(Object obj, String key) {
    }

    public void delete(Object obj) {

        InventoryItem ii = (InventoryItem) obj;
        try {
            String stmt = "DELETE FROM inventoryitem WHERE ID = ?;";
            PreparedStatement ps = DBConnection.getConnection()
                    .prepareStatement(stmt);
            ps.setInt(1, ii.getId());
            ps.execute();
        } catch (SQLException se) {
            se.printStackTrace();
        }

    }

}
