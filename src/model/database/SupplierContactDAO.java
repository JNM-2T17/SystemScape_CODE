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
import model.SupplierContact;

/**
 *
 * @author Christian Gabriel
 */
public class SupplierContactDAO implements IDBCUD {

    public Iterator get() {
        ArrayList<SupplierContact> supplierContact = new ArrayList();
        try {
            String query = "SELECT * FROM SupplierContact ORDER BY 1";
            Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                SupplierContact sc = new SupplierContact(rs.getString("supplier"), rs.getString("type"), rs.getInt("value"));
                supplierContact.add(sc);

            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return supplierContact.iterator();

    }

    public Object get(String key) {
        SupplierContact supplierContact = null;

        try {
            String query = "SELECT * FROM SupplierContact where type =  ?, value =  ? ORDER  BY 1";
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                supplierContact = new SupplierContact(rs.getString("supplier"), rs.getString("type"), rs.getInt("value"));
                return supplierContact;

            }

        } catch (SQLException se) {
            se.printStackTrace();
        }

        return null;

    }

    public Iterator search(String searchStr) {

        ArrayList<SupplierContact> supplierContact = new ArrayList<SupplierContact>();

        try {
            String query = "SELECT * FROM SupplierContact where type LIKE ?, value LIKE ? ORDER BY 1";
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "%" + searchStr
                    + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                SupplierContact sc = new SupplierContact(rs.getString("supplier"), rs.getString("type"), rs.getInt("value"));
                supplierContact.add(sc);

            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return supplierContact.iterator();
    }

    public void add(Object obj) {

        SupplierContact supplierContact = (SupplierContact) obj;

        try {
            String stmt = "INSERT INTO suppliercontact VALUES(?,?,?);";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(stmt);
            ps.setString(1, supplierContact.getSupplier());
            ps.setString(2, supplierContact.getType());
            ps.setInt(3, supplierContact.getValue());
            ps.execute();
        } catch (SQLException se) {
            se.printStackTrace();
        }

    }

    public void update(Object obj, String key) {

        SupplierContact supplierContact = (SupplierContact) obj;

        try {

            String stmt = "UPDATE SupplierContact SET supplier= ?, type= ?, value= ? WHERE type= ? AND value = ?;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(stmt);
            ps.setString(1, supplierContact.getSupplier());
            ps.setString(2, supplierContact.getType());
            ps.setInt(3, supplierContact.getValue());
            ps.setString(4, key);
            ps.execute();
        } catch (SQLException se) {
            se.printStackTrace();
        }

    }

    public void delete(Object obj) {
        SupplierContact sc = (SupplierContact) obj;

        try {
            String stmt = "DELETE FROM SupplierContact WHERE type= ? AND value = ?;";
            PreparedStatement ps = DBConnection.getConnection()
                    .prepareStatement(stmt);
            ps.setString(1, sc.getType());
            ps.setInt(2, sc.getValue());
            ps.execute();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

}
