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
import model.PurchaseOrder;
import model.Supplier;

/**
 *
 * @author Christian Gabriel
 */
public class PurchaseOrderDAO implements IDBCUD {

    public Iterator get() {
        ArrayList<PurchaseOrder> purchaseOrders = new ArrayList();
        //DBCineDirector dbcd = new DBCineDirector();

        try {
            String query = "SELECT * FROM purchaseorder";
            PreparedStatement ps = DBConnection.getConnection()
                    .prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String[] date = rs.getString("date").split(",");
                //Date dateobj = new Date(date[0], date[1], date[2]);

                String query2 = "SELECT * FROM purchaseorder p,supplier s, WHERE s.name = p.supplier ";
                PreparedStatement ps2 = DBConnection.getConnection().prepareStatement(query2);
                ResultSet rs2 = ps2.executeQuery();
                String address = rs2.getString("city") + ", " + rs2.getString("state") + ", " + rs2.getString("country");
                //Supplier s = new Supplier(rs2.getString("name"), rs2.getString("address"));

                PurchaseOrder po = new PurchaseOrder(rs.getDate("date"), rs.getString("no"), rs.getString("type"), rs2.getString("name"));

                String query3 = "SELECT * FROM purchaseorder po, poitem pi, itemdata i WHERE po.type ="
                        + "pi.type AND po.no = pi.no AND pi.itemname= i.name "; //this query statement may need work
                PreparedStatement ps3 = DBConnection.getConnection()
                        .prepareStatement(query3);
                ResultSet rs3 = ps3.executeQuery();
                ItemData id;
                while (rs3.next()) {
                    po.addItem(rs3.getString("name"), rs3.getString("description"), rs3.getFloat("unitPrice"), rs3.getInt("quantity"));
                }
                purchaseOrders.add(po);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return purchaseOrders.iterator();
    }

    public Object get(String key) {
        try {
            String query = "SELECT * FROM purchaseorder";
            PreparedStatement ps = DBConnection.getConnection()
                    .prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String[] date = rs.getString("date").split(",");
                //Date dateobj = new Date(date[0], date[1], date[2]);

                String query2 = "SELECT * FROM purchaseorder p,supplier sWHERE s.name = p.supplier ";
                PreparedStatement ps2 = DBConnection.getConnection().prepareStatement(query2);
                ResultSet rs2 = ps2.executeQuery();
                String address = rs2.getString("city") + ", " + rs2.getString("state") + ", " + rs2.getString("country");
                Supplier s = new Supplier(rs2.getString("name"), address);

                PurchaseOrder po = new PurchaseOrder(rs.getDate("date"), rs.getString("no"), rs.getString("type"), rs2.getString("name"));
                String query3 = "SELECT * FROM purchaseorder po, poitem pi,itemdata iWHERE po.type = pi.type AND po.no = pi.no AND pi.itemname= i.name "; //this query statement may need work
                PreparedStatement ps3 = DBConnection.getConnection().prepareStatement(query3);
                ResultSet rs3 = ps3.executeQuery();
                ItemData id;
                while (rs3.next()) {
                    po.addItem(rs3.getString("name"), rs3.getString("description"), rs3.getFloat("unitPrice"), rs3.getInt("quantity"));
                }
                return po;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return null;

    }

    public Iterator search(String searchStr) {
        ArrayList<PurchaseOrder> purchaseOrders = new ArrayList<PurchaseOrder>();
        try {
            String query = "SELECT * FROM purchaseorder WHERE  LIKE ? ORDER BY 1";
            Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, "%" + searchStr + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] date = rs.getString("date").split(",");
                //Date dateobj = new Date(date[0], date[1], date[2]);

                String query2 = "SELECT * FROM purchaseorder p,supplier sWHERE s.name = p.supplier ";
                PreparedStatement ps2 = DBConnection.getConnection().prepareStatement(query2);
                ResultSet rs2 = ps2.executeQuery();
                String address = rs2.getString("city") + ", " + rs2.getString("state") + ", " + rs2.getString("country");
                Supplier s = new Supplier(rs2.getString("name"), address);

                PurchaseOrder po = new PurchaseOrder(rs.getDate("date"), rs.getString("no"), rs.getString("type"), rs2.getString("name"));
                String query3 = "SELECT * FROM purchaseorder po, poitem pi,itemdata iWHERE po.type = pi.type AND po.no = pi.no AND pi.itemname= i.name "; //this query statement may need work
                PreparedStatement ps3 = DBConnection.getConnection().prepareStatement(query3);
                ResultSet rs3 = ps3.executeQuery();
                ItemData id;
                while (rs3.next()) {
                    po.addItem(rs3.getString("name"), rs3.getString("description"), rs3.getFloat("unitPrice"), rs3.getInt("quantity"));
                }
                purchaseOrders.add(po);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return purchaseOrders.iterator();

    }

    public void add(Object obj) {

        PurchaseOrder po = (PurchaseOrder) obj;
        String date = "test";

        try {
            String stmt = "INSERT INTO purchaseorder VALUES(?,?,?,?);";
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(stmt);
            ps.setString(1, po.getType());
            ps.setInt(2, po.getIdNo());
            ps.setDate(3, new java.sql.Date(po.getDate().getTime()));
            ps.setString(4, po.getSupplier());
            ps.execute();
        }catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public void update(Object obj, String key) {
    }

    public void delete(Object obj) {

        PurchaseOrder po = (PurchaseOrder) obj;
        try {
            String stmt = "DELETE FROM purchaseorder WHERE type = ? AND no = ? ;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(stmt);
            ps.setString(1, po.getType());
            ps.setInt(2, po.getIdNo());
            ps.execute();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
