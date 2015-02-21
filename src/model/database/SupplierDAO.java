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
import model.Supplier;

/**
 *
 * @author Christian Gabriel
 */
public class SupplierDAO implements IDBCUD {

    public Iterator get() {
        ArrayList<Supplier> suppliers = new ArrayList<Supplier>();

        try {
            String query = "SELECT * FROM supplier";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Supplier s = new Supplier(rs.getString("name"), rs.getString("address"));

                String query2 = "SELECT * FROM supplier S,supplierContacts CS WHERE S.name = SC.supplier";
                PreparedStatement ps2 = DBConnection.getConnection().prepareStatement(query2);
                ResultSet rs2 = ps2.executeQuery();

                while (rs2.next()) {
                    s.addSupplierContact(rs2.getString("supplier"), rs2.getString("type"), rs2.getInt("value"));
                }
                suppliers.add(s);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return suppliers.iterator();
    }

    public Object get(String key) {
        try {
            String query = "SELECT * FROM supplier WHERE supplier = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(query);
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Supplier s = new Supplier(rs.getString("name"), rs.getString("address"));
                String query2 = "SELECT * FROM supplier S,supplierContact CS WHERE S.name = CS.supplier";
                PreparedStatement ps2 = DBConnection.getConnection().prepareStatement(query2);
                ResultSet rs2 = ps2.executeQuery();

                while (rs2.next()) {
                    s.addSupplierContact(rs2.getString("supplier"), rs2.getString("type"), rs2.getInt("value"));
                }
            return s;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return null;
    }

    public Iterator search(String searchStr) {

        ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
        try {

            String query = "SELECT * FROM supplier WHERE name LIKE ? "+ "ORDER BY 1";
            Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, "%" + searchStr + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Supplier s = new Supplier(rs.getString("name"),rs.getString("address"));

                String query2 = "SELECT * FROM supplier S,supplierContacts CS WHERE S.name= SC.supplier";
		PreparedStatement ps2 = DBConnection.getConnection().prepareStatement(query2);
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    s.addSupplierContact(rs2.getString("supplier"),
                            rs2.getString("type"), rs2.getInt("value"));
                }

                suppliers.add(s);

            }
        }catch( SQLException se ){
		se.printStackTrace();
	}

            return suppliers.iterator();
        }



    public void add(Object obj) {

        Supplier s = (Supplier) obj;
        String address[] = s.getAddress().split(",");

        try {

            String stmt = "INSERT INTO supplier VALUES(?,?,?,?);";
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(stmt);
            ps.setString(1, s.getName());
            ps.setString(2, address[0]);
            ps.setString(3, address[1]);
            ps.setString(4, address[2]); //not sure bout the address format
            ps.execute();
        } catch( SQLException se ){
		se.printStackTrace();
	}
        
    }

    public void update(Object obj, String origKey) {
        Supplier s = (Supplier) obj;
        String address[] = s.getAddress().split(",");

        try {
            String stmt = "UPDATE supplier SET name = ?,country = ?, "
                    + "state= ?, city = ? WHERE name = ?;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(stmt);
            ps.setString(1, s.getName());
            ps.setString(2, address[0]);
            ps.setString(3, address[1]);
            ps.setString(4, address[2]); //not sure bout the address format
            ps.setString(5, origKey);
            ps.execute();
        } catch (SQLException se) {
            se.printStackTrace();
        }

    }

    public void delete(Object obj) {

        Supplier s = (Supplier) obj;
        try {
            String stmt = "DELETE FROM supplier WHERE name = ?;";
            PreparedStatement ps = DBConnection.getConnection()
                    .prepareStatement(stmt);
            ps.setString(1, s.getName());
            ps.execute();
        } catch (SQLException se) {
            se.printStackTrace();
        }

    }

}
