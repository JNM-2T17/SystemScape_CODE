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
            String query = "SELECT p.date, p.no,  p.type, s.name, s.country, s.state, s.city, p.invoiceNo\n"
                    + "FROM purchaseorder p\n"
                    + "INNER JOIN supplier s\n"
                    + "ON s.name=p.supplier";
            preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Supplier s = new Supplier(resultSet.getString("name"), resultSet.getString("country"), resultSet.getString("state"), resultSet.getString("city"));
                PurchaseOrder purchaseorder = new PurchaseOrder(resultSet.getDate("date"), resultSet.getInt("no"), resultSet.getString("type"), s, resultSet.getString("invoiceNo"));

                String query2 = "SELECT i.name, i.description, i.unitPrice, pi.quantityOrdered\n"
                        + "FROM itemdata i\n"
                        + "INNER JOIN poitem pi\n"
                        + "ON pi.itemname=i.name AND  pi.type=\"" + resultSet.getString("type") + "\" AND pi.no=\"" + resultSet.getInt("no") + "\" ";
                PreparedStatement preparedStatement2 = con.prepareStatement(query2);
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                while (resultSet2.next()) {
                    purchaseorder.addItem(resultSet2.getString("name"), resultSet2.getString("description"), resultSet2.getFloat("unitPrice"), resultSet2.getInt("quantityOrdered"));
                }
                purchaseOrders.add(purchaseorder);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
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
            String[] keys = key.split(",");
            String query = "SELECT p.date, p.no,  p.type, s.name, s.country, s.state, s.city, p.invoiceNo\n"
                    + "FROM purchaseorder p\n"
                    + "INNER JOIN supplier s\n"
                    + "ON s.name=p.supplier\n"
                    + "WHERE p.no = ? AND p.type = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, keys[0]);
            preparedStatement.setString(2, keys[1]);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Supplier s = new Supplier(resultSet.getString("name"), resultSet.getString("country"), resultSet.getString("state"), resultSet.getString("city"));
                PurchaseOrder purchaseorder = new PurchaseOrder(resultSet.getDate("date"), resultSet.getInt("no"), resultSet.getString("type"), s, resultSet.getString("invoiceNo"));

                String query2 = "SELECT i.name, i.description, i.unitPrice, pi.quantityOrdered\n"
                        + "FROM itemdata i\n"
                        + "INNER JOIN poitem pi\n"
                        + "ON pi.itemname=i.name AND  pi.type=\"" + resultSet.getString("type") + "\" AND pi.no=\"" + resultSet.getInt("no") + "\" ";

                PreparedStatement preparedStatement2 = con.prepareStatement(query2);
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                while (resultSet2.next()) {
                    purchaseorder.addItem(resultSet2.getString("name"), resultSet2.getString("description"), resultSet2.getFloat("unitPrice"), resultSet2.getInt("quantity"));
                }

                try {
                    if(con!=null)
                        con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                return purchaseorder;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
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
            String query = "SELECT p.date, p.no,  p.type, s.name, s.country, s.state, s.city, p.invoiceNo\n"
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
                PurchaseOrder purchaseorder = new PurchaseOrder(resultSet.getDate("date"), resultSet.getInt("no"), resultSet.getString("type"), s, resultSet.getString("invoiceNo"));

                String query2 = "SELECT i.name, i.description, i.unitPrice, pi.quantityOrdered \n"
                        + "FROM itemdata i\n"
                        + "INNER JOIN poitem pi\n"
                        + "ON pi.itemname=i.name AND  pi.type=\"" + resultSet.getString("type") + "\" AND pi.no=\"" + resultSet.getInt("no") + "\" ";

                PreparedStatement preparedStatement2 = con.prepareStatement(query2);
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                while (resultSet2.next()) {
                    purchaseorder.addItem(resultSet2.getString("name"), resultSet2.getString("description"), resultSet2.getFloat("unitPrice"), resultSet2.getInt("quantity"));
                }
                purchaseOrders.add(purchaseorder);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
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
        QueryFilterDirector director = new QueryFilterDirector(new POFilterQueryBuilder());
        ArrayList<String> results = new ArrayList<String>();
        try {
            String query = director.getQuery(conditions);
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                results.add(resultSet.getString("no"));
                results.add(resultSet.getString("type"));
                results.add(resultSet.getString("supplier"));
                results.add(resultSet.getString("date"));
                results.add(resultSet.getString("Sum"));
            }
            return results.iterator();
        } catch (Exception exeption) {
            exeption.printStackTrace();
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }
        return null;
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
        }finally {
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
            String query = "INSERT INTO purchaseorder  VALUES (?,NULL,?,?,?);";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, purchaseorder.getType());
            preparedStatement.setDate(2, new java.sql.Date(purchaseorder.getDate().getTime()));
            preparedStatement.setString(3, purchaseorder.getSupplier().getName());

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
                preparedStatement.setInt(5, purchaseorder.getQuantity(itemData));//temppo
                preparedStatement.execute();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
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
        PurchaseOrder purchaseorder = (PurchaseOrder) object;
        Connection con = DBConnection.getConnection();
        Map.Entry es;
        Iterator iterator = purchaseorder.getItems();
        ItemData itemData;
        try {
            String query = /*"BEGIN transaction "
                    + */"UPDATE purchaseorder SET date = ?, no = ?, type = ?, supplier = ? "
                    + "WHERE no = ? ";
                    /*+ "UPDATE supplier SET name = ?, country = ?, state = ?, city = ? "
                    + "FROM purchaseorder p, supplier s "
                    + "WHERE p.supplier = s.supplier "
                    + "AND p.supplier = ? "
                    + "COMMIT";*/
            PreparedStatement preparedStatement = con.prepareStatement(query);
            java.sql.Date sqlDate = new java.sql.Date(purchaseorder.getDate().getTime());
            preparedStatement.setDate(1, sqlDate);
            preparedStatement.setInt(2, purchaseorder.getIdNo());
            preparedStatement.setString(3, purchaseorder.getType());
            preparedStatement.setString(4, purchaseorder.getSupplier().getName());
            preparedStatement.setString(5, key);
            /*preparedStatement.setString(6, purchaseorder.getSupplier().getName());
            preparedStatement.setString(7, purchaseorder.getSupplier().getCountry());
            preparedStatement.setString(8, purchaseorder.getSupplier().getState());
            preparedStatement.setString(9, purchaseorder.getSupplier().getCity());
            preparedStatement.setString(10, purchaseorder.getSupplier().getName());
            */preparedStatement.execute();
            
            while (iterator.hasNext()) {
                es = (Map.Entry) iterator.next();
                itemData = (ItemData) es.getKey();
                String stmt2 = "BEGIN transaction\n\n"
                        + "UPDATE poitem SET type = ?, no = ?, itemname = ?, quantity = ?\n"
                        + "FROM purchaseorder po, poitem pi\n"
                        + "WHERE po.type = pi.type AND po.no = po.type\n"
                        + "AND po.type = ? AND po.no = ?\n\n"
                        + "UPDATE itemdata SET name = ?, description = ?, unitPrice = ?\n"
                        + "FROM poitem p, itemdata i\n"
                        + "WHERE po.itemname = i.name\n"
                        + "AND i.name = ?\n\n"
                        + "COMMIT";

                PreparedStatement preparedStatement2 = con
                        .prepareStatement(stmt2);
                preparedStatement2.setString(1, purchaseorder.getType());
                preparedStatement2.setString(2, "" + purchaseorder.getIdNo());
                preparedStatement2.setString(3, itemData.getName());
                preparedStatement2.setString(4, "" + (Integer) es.getValue());
                preparedStatement2.setString(5, purchaseorder.getType());
                preparedStatement2.setString(6, "" + purchaseorder.getIdNo());
                preparedStatement2.setString(7, itemData.getName());
                preparedStatement2.setString(8, itemData.getDescription());
                preparedStatement2.setString(9, "" + itemData.getUnitPrice());
                preparedStatement2.setString(10, itemData.getName());
                preparedStatement2.execute();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
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
        }finally {
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
