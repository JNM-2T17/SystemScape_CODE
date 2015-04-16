/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ItemData;
import model.PurchaseOrder;
import model.Supplier;
import model.builder.QueryFilterDirector;
import model.builder.POFilterQueryBuilder;

/**
 *
 * @author Christian Gabriel
 */
public class PurchaseOrderDAO implements IDBCUD {

    public Iterator get() {
        Connection con = DBConnection.getConnection();
        ArrayList<PurchaseOrder> purchaseOrders = new ArrayList();
        PreparedStatement preparedStatement;
        try {
            String query = "SELECT p.date, p.no,  p.type, s.name, s.country, s.state, s.city, p.invoiceNo, p.currency\n"
                    + "FROM purchaseorder p\n"
                    + "INNER JOIN supplier s\n"
                    + "ON s.name=p.supplier";
            preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Supplier s = new Supplier(resultSet.getString("name"), resultSet.getString("country"), resultSet.getString("state"), resultSet.getString("city"));
                PurchaseOrder purchaseorder = new PurchaseOrder(resultSet.getDate("date"), resultSet.getInt("no"), resultSet.getString("type"), s, resultSet.getString("invoiceNo"), resultSet.getString("currency"));

                String query2 = "SELECT i.name, i.description, i.unitPrice, pi.quantityOrdered, pi.quantityReceived\n"
                        + "FROM itemdata i\n"
                        + "INNER JOIN poitem pi\n"
                        + "ON pi.itemname=i.name AND  pi.type=\"" + resultSet.getString("type") + "\" AND pi.no=\"" + resultSet.getInt("no") + "\" ";
                PreparedStatement preparedStatement2 = con.prepareStatement(query2);
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                while (resultSet2.next()) {
                    purchaseorder.addItem(resultSet2.getString("name"), resultSet2.getString("description"), resultSet2.getFloat("unitPrice"), resultSet2.getInt("quantityOrdered"), resultSet2.getInt("quantityReceived"));
                }
                purchaseOrders.add(purchaseorder);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }

        return purchaseOrders.iterator();
    }

    public Object get(String key) {
        Connection con = DBConnection.getConnection();
        try {
            String query = "SELECT p.date, p.no,  p.type, s.name, s.country, s.state, s.city, p.invoiceNo, p.currency\n"
                    + "FROM purchaseorder p\n"
                    + "INNER JOIN supplier s\n"
                    + "ON s.name=p.supplier\n"
                    + "WHERE p.no = ? ";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Supplier s = new Supplier(resultSet.getString("name"), resultSet.getString("country"), resultSet.getString("state"), resultSet.getString("city"));
                PurchaseOrder purchaseorder = new PurchaseOrder(resultSet.getDate("date"), resultSet.getInt("no"), resultSet.getString("type"), s, resultSet.getString("invoiceNo"), resultSet.getString("currency"));

                String query2 = "SELECT i.name, i.description, i.unitPrice, pi.quantityOrdered, pi.quantityReceived\n"
                        + "FROM itemdata i\n"
                        + "INNER JOIN poitem pi\n"
                        + "ON pi.itemname=i.name AND  pi.type=\"" + resultSet.getString("type") + "\" AND pi.no=\"" + resultSet.getInt("no") + "\" ";

                PreparedStatement preparedStatement2 = con.prepareStatement(query2);
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                while (resultSet2.next()) {
                    purchaseorder.addItem(resultSet2.getString("name"), resultSet2.getString("description"), resultSet2.getFloat("unitPrice"), resultSet2.getInt("quantityOrdered"), resultSet2.getInt("quantityReceived"));
                }

                try {
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                return purchaseorder;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }

        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;

    }

    public Iterator search(String searchStr) {
        Connection con = DBConnection.getConnection();
        ArrayList<PurchaseOrder> purchaseOrders = new ArrayList<PurchaseOrder>();
        try {
            String query = "SELECT p.date, p.no,  p.type, s.name, s.country, s.state, s.city, p.invoiceNo, p.currency\n"
                    + "FROM purchaseorder p\n"
                    + "INNER JOIN supplier s\n"
                    + "ON s.name=p.supplier "
                    + "WHERE type LIKE ?\n"
                    + "OR no LIKE ?\n"
                    + "OR date LIKE ?\n"
                    + "OR supplier LIKE ?\n";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, "%" + searchStr + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Supplier s = new Supplier(resultSet.getString("name"), resultSet.getString("country"), resultSet.getString("state"), resultSet.getString("city"));
                PurchaseOrder purchaseorder = new PurchaseOrder(resultSet.getDate("date"), resultSet.getInt("no"), resultSet.getString("type"), s, resultSet.getString("invoiceNo"), resultSet.getString("currency"));

                String query2 = "SELECT i.name, i.description, i.unitPrice, pi.quantityOrdered, pi.quantityReceived \n"
                        + "FROM itemdata i\n"
                        + "INNER JOIN poitem pi\n"
                        + "ON pi.itemname=i.name AND  pi.type=\"" + resultSet.getString("type") + "\" AND pi.no=\"" + resultSet.getInt("no") + "\" ";

                PreparedStatement preparedStatement2 = con.prepareStatement(query2);
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                while (resultSet2.next()) {
                    purchaseorder.addItem(resultSet2.getString("name"), resultSet2.getString("description"), resultSet2.getFloat("unitPrice"), resultSet2.getInt("quantityOrdered"), resultSet2.getInt("quantityReceived"));
                }
                purchaseOrders.add(purchaseorder);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }
        return purchaseOrders.iterator();

    }

    public Iterator filter(Iterator conditions) {
        Connection con = DBConnection.getConnection();
        ArrayList<PurchaseOrder> purchaseOrders = new ArrayList();
        QueryFilterDirector director = new QueryFilterDirector(new POFilterQueryBuilder());
        ArrayList<String> results = new ArrayList<String>();
        try {
            String query = director.getQuery(conditions);
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String query2 = "SELECT * FROM supplier \n"
                        + "WHERE name = ? ";
                PreparedStatement preparedStatement2 = con.prepareStatement(query2);
                preparedStatement2.setString(1, resultSet.getString("supplier"));
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                resultSet2.next();
                Supplier s = new Supplier(resultSet2.getString("name"), resultSet2.getString("country"), resultSet2.getString("state"), resultSet2.getString("city"));
                PurchaseOrder purchaseorder = new PurchaseOrder(resultSet.getDate("date"), resultSet.getInt("no"), resultSet.getString("type"), s, resultSet.getString("invoiceNo"), "");

                String query3 = "SELECT i.name, i.description, i.unitPrice, pi.quantityOrdered, pi.quantityReceived\n"
                        + "FROM itemdata i\n"
                        + "INNER JOIN poitem pi\n"
                        + "ON pi.itemname=i.name AND  pi.type=\"" + resultSet.getString("type") + "\" AND pi.no=\"" + resultSet.getInt("no") + "\" ";

                PreparedStatement preparedStatement3 = con.prepareStatement(query3);
                ResultSet resultSet3 = preparedStatement3.executeQuery();
                while (resultSet3.next()) {
                    purchaseorder.addItem(resultSet3.getString("name"), resultSet3.getString("description"), resultSet3.getFloat("unitPrice"), resultSet3.getInt("quantityOrdered"), resultSet3.getInt("quantityReceived"));
                }
                purchaseOrders.add(purchaseorder);
            }
        } catch (Exception exeption) {
            exeption.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }
        return purchaseOrders.iterator();
    }

    public Iterator getDistinct(String string) {
        Connection con = DBConnection.getConnection();
        ArrayList<String> results = new ArrayList<String>();
        try {
            String query = "SELECT DISTINCT " + string + " FROM purchaseorder";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                results.add(resultSet.getString(1));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }

        return results.iterator();
    }

    public void add(Object object) {
        PurchaseOrder purchaseorder = (PurchaseOrder) object;
        Connection con = DBConnection.getConnection();
        try {
            String query = "INSERT INTO purchaseorder  VALUES (?,NULL,?,?,?,?);";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, purchaseorder.getType());
            preparedStatement.setDate(2, new java.sql.Date(purchaseorder.getDate().getTime()));
            preparedStatement.setString(3, purchaseorder.getSupplier().getName());
            preparedStatement.setString(5, purchaseorder.getCurrency());

            //
            String queryE = "SELECT MAX(no) FROM purchaseorder";
            PreparedStatement preparedStatementE = con.prepareStatement(queryE);
            ResultSet resultSetE = preparedStatementE.executeQuery();
            resultSetE.next();
            //

            preparedStatement.setString(4, "iv" + resultSetE.getInt(1));
            preparedStatement.execute();

            Iterator items = purchaseorder.getItems();

            while (items.hasNext()) {
                query = "SELECT MAX(no) FROM purchaseorder";
                preparedStatement = con.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();

                ItemData itemData = (ItemData) items.next();
                query = "INSERT INTO poitem  VALUES(?,?,?,?,?);";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, purchaseorder.getType());
                preparedStatement.setInt(2, resultSet.getInt(1));
                preparedStatement.setString(3, itemData.getName());
                preparedStatement.setInt(4, purchaseorder.getQuantity(itemData));
                preparedStatement.setInt(5, purchaseorder.getQuantityRcvd(itemData));//temppo
                preparedStatement.execute();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }
    }

    public void update(Object object, String key) {
        PurchaseOrder currentPO = (PurchaseOrder) object;
        Connection con = DBConnection.getConnection();
        Map.Entry es;
        Iterator currItems = currentPO.getItemData();
        ItemData itemData;

        try {
            String query = "UPDATE purchaseorder SET date = ?, type = ?, supplier = ?, currency = ? "
                    + "WHERE no = ? ";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            java.sql.Date sqlDate = new java.sql.Date(currentPO.getDate().getTime());
            preparedStatement.setDate(1, sqlDate);
            preparedStatement.setString(2, currentPO.getType());
            preparedStatement.setString(3, currentPO.getSupplier().getName());
            preparedStatement.setString(4, currentPO.getCurrency());
            preparedStatement.setString(5, key);
            preparedStatement.execute();

            query = "DELETE FROM poitem WHERE type = ? AND no = ? ;";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, currentPO.getType());
            preparedStatement.setInt(2, currentPO.getIdNo());
            preparedStatement.execute();

            while (currItems.hasNext()) {
                es = (Map.Entry) currItems.next();
                itemData = (ItemData) es.getKey();
                System.out.println("ADDING TO POITEM: " + itemData.getName());
                query = "INSERT INTO poitem  VALUES(?,?,?,?,?);";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, currentPO.getType());
                preparedStatement.setInt(2, currentPO.getIdNo());
                preparedStatement.setString(3, itemData.getName());
                preparedStatement.setInt(4, currentPO.getQuantity(itemData));
                preparedStatement.setInt(5, currentPO.getQuantityRcvd(itemData));
                preparedStatement.execute();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }
        System.out.println("update shouldve happened");
    }

    public void delete(Object object) {
        Connection con = DBConnection.getConnection();
        PurchaseOrder purchaseorder = (PurchaseOrder) object;
        try {
            String query = "DELETE FROM purchaseorder WHERE type = ? AND no = ? ;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, purchaseorder.getType());
            preparedStatement.setInt(2, purchaseorder.getIdNo());
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }
    }
}
