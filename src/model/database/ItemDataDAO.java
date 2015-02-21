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
import model.ItemData;

/**
 *
 * @author Christian Gabriel
 */
public class ItemDataDAO implements IDBCUD {

    //returns all items in this table
    public Iterator get() {
        ArrayList<ItemData> itemData = new ArrayList<ItemData>();
        try {
            String query = "SELECT * FROM ItemData ORDER BY 1";
            Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ItemData id = new ItemData(rs.getString("name"), rs.getString("description"), rs.getFloat("unitPrice"));
                itemData.add(id);

            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return itemData.iterator();

    }

    public Object get(String key) {
        ItemData itemData = null;

        try {

            String query = "SELECT * FROM ItemData where name =  ? ORDER  BY 1";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(query);
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ItemData id = new ItemData(rs.getString("name"), rs.getString("description"), rs.getFloat("unitPrice"));
                return id;
            }

        } catch (SQLException se) {
            se.printStackTrace();
        }

        return null;

    }

    public Iterator search(String searchStr) {

        ArrayList<ItemData> itemData = new ArrayList<ItemData>();

        try {
            String query = "SELECT * FROM ItemData where name LIKE ? ORDER BY 1";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(query);
            ps.setString(1, "%" + searchStr + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ItemData id = new ItemData(rs.getString("name"), rs.getString("description"), rs.getFloat("unitPrice"));
                itemData.add(id);

            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return itemData.iterator();

    }

    public void add(Object obj) {

        ItemData itemData = (ItemData) obj;

        try {
            String stmt = "INSERT INTO ItemData VALUE (?, ?, ?);";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(stmt);
            ps.setString(1, itemData.getName());
            ps.setString(2, itemData.getDescription());
            ps.setInt(3, Math.round(itemData.getUnitPrice()));
            ps.execute();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public void update(Object obj, String key) {

        ItemData itemData = (ItemData) obj;

        try {

            String stmt = "UPDATE ItemData SET name = ?, description = ?, unitPrice = ? WHERE name = ?;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(stmt);
            ps.setString(1, itemData.getName());
            ps.setString(2, itemData.getDescription());
            ps.setInt(3, Math.round(itemData.getUnitPrice()));
            ps.setString(4, key);
            ps.execute();
        } catch (SQLException se) {
            se.printStackTrace();
        }

    }

    public void delete(Object obj) {
        ItemData itemData = (ItemData) obj;

        try {
            String stmt = "DELETE FROM ItemData where name = ?;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(stmt);
            ps.setString(1, itemData.getName());
            ps.execute();
        } catch (SQLException se) {
            se.printStackTrace();
        }

    }
}
