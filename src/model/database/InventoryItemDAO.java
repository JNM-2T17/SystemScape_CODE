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
import java.util.Date;
import java.util.Iterator;

import model.HardwareItem;
import model.ITAsset;
import model.InventoryItem;
import model.ItemData;
import model.NonITAsset;
import model.SoftwareItem;
import model.builder.QueryFilterDirector;
import model.builder.InventoryItemFilterQueryBuilder;

/**
 *
 * @author Christian Gabriel
 */
public class InventoryItemDAO implements IDBCUD {

    public Iterator get() {
        ArrayList<InventoryItem> inventoryItems = new ArrayList<InventoryItem>();
        Connection con = DBConnection.getConnection();

        try {
            String query = "SELECT * FROM inventory";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                InventoryItem inventoryItem = null;
                String itemClass = resultSet.getString("classification");

                if (itemClass.equalsIgnoreCase("Non-IT")) {
                    inventoryItem = new NonITAsset(resultSet.getInt("ID"), resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getFloat("unitPrice"),
                            resultSet.getString("invoiceNo"),
                            resultSet.getString("location"),
                            resultSet.getString("status"),
                            resultSet.getString("classification"),
                            resultSet.getDate("Warranty Start"),
                            resultSet.getDate("Warranty End"));
                } else if (itemClass.equalsIgnoreCase("Soft")) {
                    inventoryItem = new SoftwareItem(resultSet.getInt("ID"), resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getFloat("unitPrice"),
                            resultSet.getString("invoiceNo"),
                            resultSet.getString("location"),
                            resultSet.getString("status"),
                            resultSet.getString("classification"),
                            resultSet.getString("licenseKey"));
                } else if (itemClass.equalsIgnoreCase("IT")) {
                    inventoryItem = new ITAsset(resultSet.getInt("ID"), resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getFloat("unitPrice"),
                            resultSet.getString("invoiceNo"),
                            resultSet.getString("location"),
                            resultSet.getString("status"),
                            resultSet.getString("classification"),
                            resultSet.getInt("assetTag"),
                            resultSet.getString("serviceTag"),
                            resultSet.getDate("deliveryDate"),
                            resultSet.getDate("Warranty Start"),
                            resultSet.getDate("Warranty End"),
                            resultSet.getDate("Contract Start"),
                            resultSet.getDate("Contract End"),
                            resultSet.getFloat("maintenanceCost"));
                } else {
                    inventoryItem = new InventoryItem(resultSet.getInt("ID"), resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getFloat("unitPrice"),
                            resultSet.getString("invoiceNo"),
                            resultSet.getString("location"),
                            resultSet.getString("status"),
                            resultSet.getString("classification"));
                }
                inventoryItems.add(inventoryItem);
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

        return inventoryItems.iterator();
    }

    public Object get(String key) {
        Connection con = DBConnection.getConnection();
        try {
            String query = "SELECT * FROM inventoryitem WHERE ID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            InventoryItem inventoryItem = null;
            if (resultSet.next()) {
                String query2 = "SELECT * FROM itemdata id WHERE id.name = \"" + resultSet.getString("itemData") + "\"";
                PreparedStatement preparedStatement2 = con.prepareStatement(query2);
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                if (resultSet2.next()) {
                    String query3 = "SELECT * FROM hardwareitem WHERE ID = \"" + resultSet.getString("ID") + "\"";
                    PreparedStatement preparedStatement3 = con.prepareStatement(query3);
                    ResultSet resultSet3 = preparedStatement3.executeQuery();

                    if (resultSet3.next()) {//IF hardware
                        String query4 = "SELECT * FROM itasset WHERE ID = \"" + resultSet.getString("ID") + "\"";
                        PreparedStatement preparedStatement4 = con.prepareStatement(query4);
                        ResultSet resultSet4 = preparedStatement4.executeQuery();

                        if (resultSet4.next()) {//IF ITAsset
                            String query6 = "SELECT * FROM warranty WHERE hardware = \"" + resultSet.getString("ID") + "\"";
                            PreparedStatement preparedStatement6 = con.prepareStatement(query6);
                            ResultSet resultSet6 = preparedStatement6.executeQuery();

                            String query7 = "SELECT * FROM contract WHERE hardware = \"" + resultSet.getString("ID") + "\"";
                            PreparedStatement preparedStatement7 = con.prepareStatement(query7);
                            ResultSet resultSet7 = preparedStatement7.executeQuery();

                            if (resultSet7.next() && resultSet6.next()) {
                                inventoryItem = new ITAsset(resultSet.getInt("ID"),
                                        resultSet2.getString("name"), resultSet2.getString("description"),
                                        resultSet2.getFloat("unitPrice"), resultSet.getString("invoiceNo"),
                                        resultSet.getString("location"), resultSet.getString("status"),
                                        resultSet.getString("classification"), resultSet4.getInt("assetTag"), resultSet4.getString("serviceTag"),
                                        resultSet4.getDate("deliveryDate"), resultSet6.getDate("startDate"), resultSet6.getDate("endDate"),
                                        resultSet7.getDate("startDate"), resultSet7.getDate("endDate"), resultSet7.getFloat("maintenanceCost"));
                            }
                        } else {//Else NonITAsset
                            String query6 = "SELECT * FROM warranty WHERE hardware = \"" + resultSet.getString("ID") + "\"";
                            PreparedStatement preparedStatement6 = con.prepareStatement(query6);
                            ResultSet resultSet6 = preparedStatement6.executeQuery();
                            if (resultSet6.next()) {
                                inventoryItem = new NonITAsset(resultSet.getInt("ID"), resultSet2.getString("name"),
                                        resultSet2.getString("description"), resultSet2.getFloat("unitPrice"),
                                        resultSet.getString("invoiceNo"), resultSet.getString("location"),
                                        resultSet.getString("status"), resultSet.getString("classification"),
                                        resultSet6.getDate("startDate"), resultSet6.getDate("endDate"));
                            }
                        }
                    } else {//Else not hardware
                        String query5 = "SELECT * FROM softwareitem WHERE ID = \"" + resultSet.getString("ID") + "\"";
                        PreparedStatement preparedStatement5 = con.prepareStatement(query5);
                        ResultSet resultSet5 = preparedStatement5.executeQuery();
                        if (resultSet5.next()) {//if software
                            inventoryItem = new SoftwareItem(resultSet.getInt("ID"), resultSet2.getString("name"),
                                    resultSet2.getString("description"), resultSet2.getFloat("unitPrice"),
                                    resultSet.getString("invoiceNo"), resultSet.getString("location"),
                                    resultSet.getString("status"), resultSet.getString("classification"),
                                    resultSet5.getString("licenseKey"));
                        } else {//if general
                            inventoryItem = new InventoryItem(resultSet.getInt("ID"), resultSet2.getString("name"),
                                    resultSet2.getString("description"), resultSet2.getFloat("unitPrice"),
                                    resultSet.getString("invoiceNo"), resultSet.getString("location"),
                                    resultSet.getString("status"), resultSet.getString("classification"));
                        }
                    }

                    try {
                        con.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } finally {
                        try {
                            if (con != null) {
                                con.close();
                            }
                        } catch (SQLException sqlee) {
                            sqlee.printStackTrace();
                        }
                    }

                    return inventoryItem;
                }
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

        return null;
    }

    public Iterator search(String searchStr) {//to be edited
        ArrayList<InventoryItem> inventoryItems = new ArrayList<InventoryItem>();
        try {
            String query = "SELECT * FROM inventoryitem WHERE ID LIKE ? " + "ORDER BY 1";
            Connection con = DBConnection.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, "%" + searchStr + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String query2 = "SELECT * FROM itemdata id WHERE id.name = \"" + resultSet.getString("itemData") + "\"";
                PreparedStatement preparedStatement2 = con.prepareStatement(query2);
                ResultSet resultSet2 = preparedStatement2.executeQuery();

                InventoryItem inventoryItem = new InventoryItem(resultSet.getInt("ID"), resultSet2.getString("name"), resultSet2.getString("description"), resultSet2.getFloat("unitPrice"), resultSet.getString("invoiceNo"), resultSet.getString("location"), resultSet.getString("status"), resultSet.getString("classification"));
                inventoryItems.add(inventoryItem);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return inventoryItems.iterator();
    }

    public Iterator filter(Iterator conditions) {
        QueryFilterDirector director = new QueryFilterDirector(new InventoryItemFilterQueryBuilder());
        ArrayList<InventoryItem> results = new ArrayList<InventoryItem>();
        Connection con = DBConnection.getConnection();
        try {
            String query = director.getQuery(conditions);
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                InventoryItem inventoryItem = (InventoryItem) get(resultSet.getString("ID"));
                results.add(inventoryItem);
            }
            return results.iterator();
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
        return null;
    }

    public Iterator getDistinct(String key) {
        ArrayList<InventoryItem> items = new ArrayList();
        Connection con = DBConnection.getConnection();
        try {
            String query = "SELECT * FROM inventoryitem GROUP BY "+key;
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            InventoryItem inventoryItem = null;
            while (resultSet.next()) {
                String query2 = "SELECT * FROM itemdata id WHERE id.name = \"" + resultSet.getString("itemData") + "\"";
                PreparedStatement preparedStatement2 = con.prepareStatement(query2);
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                if (resultSet2.next()) {
                    String query3 = "SELECT * FROM hardwareitem WHERE ID = \"" + resultSet.getString("ID") + "\"";
                    PreparedStatement preparedStatement3 = con.prepareStatement(query3);
                    ResultSet resultSet3 = preparedStatement3.executeQuery();

                    if (resultSet3.next()) {//IF hardware
                        String query4 = "SELECT * FROM itasset WHERE ID = \"" + resultSet.getString("ID") + "\"";
                        PreparedStatement preparedStatement4 = con.prepareStatement(query4);
                        ResultSet resultSet4 = preparedStatement4.executeQuery();

                        if (resultSet4.next()) {//IF ITAsset
                            String query6 = "SELECT * FROM warranty WHERE hardware = \"" + resultSet.getString("ID") + "\"";
                            PreparedStatement preparedStatement6 = con.prepareStatement(query6);
                            ResultSet resultSet6 = preparedStatement6.executeQuery();

                            String query7 = "SELECT * FROM contract WHERE hardware = \"" + resultSet.getString("ID") + "\"";
                            PreparedStatement preparedStatement7 = con.prepareStatement(query7);
                            ResultSet resultSet7 = preparedStatement7.executeQuery();

                            if (resultSet7.next() && resultSet6.next()) {
                                inventoryItem = new ITAsset(resultSet.getInt("ID"),
                                        resultSet2.getString("name"), resultSet2.getString("description"),
                                        resultSet2.getFloat("unitPrice"), resultSet.getString("invoiceNo"),
                                        resultSet.getString("location"), resultSet.getString("status"),
                                        resultSet.getString("classification"), resultSet4.getInt("assetTag"), resultSet4.getString("serviceTag"),
                                        resultSet4.getDate("deliveryDate"), resultSet6.getDate("startDate"), resultSet6.getDate("endDate"),
                                        resultSet7.getDate("startDate"), resultSet7.getDate("endDate"), resultSet7.getFloat("maintenanceCost"));
                            }
                        } else {//Else NonITAsset
                            String query6 = "SELECT * FROM warranty WHERE hardware = \"" + resultSet.getString("ID") + "\"";
                            PreparedStatement preparedStatement6 = con.prepareStatement(query6);
                            ResultSet resultSet6 = preparedStatement6.executeQuery();
                            if (resultSet6.next()) {
                                inventoryItem = new NonITAsset(resultSet.getInt("ID"), resultSet2.getString("name"),
                                        resultSet2.getString("description"), resultSet2.getFloat("unitPrice"),
                                        resultSet.getString("invoiceNo"), resultSet.getString("location"),
                                        resultSet.getString("status"), resultSet.getString("classification"),
                                        resultSet6.getDate("startDate"), resultSet6.getDate("endDate"));
                            }
                        }
                    } else {//Else not hardware
                        String query5 = "SELECT * FROM softwareitem WHERE ID = \"" + resultSet.getString("ID") + "\"";
                        PreparedStatement preparedStatement5 = con.prepareStatement(query5);
                        ResultSet resultSet5 = preparedStatement5.executeQuery();
                        if (resultSet5.next()) {//if software
                            inventoryItem = new SoftwareItem(resultSet.getInt("ID"), resultSet2.getString("name"),
                                    resultSet2.getString("description"), resultSet2.getFloat("unitPrice"),
                                    resultSet.getString("invoiceNo"), resultSet.getString("location"),
                                    resultSet.getString("status"), resultSet.getString("classification"),
                                    resultSet5.getString("licenseKey"));
                        } else {//if general
                            inventoryItem = new InventoryItem(resultSet.getInt("ID"), resultSet2.getString("name"),
                                    resultSet2.getString("description"), resultSet2.getFloat("unitPrice"),
                                    resultSet.getString("invoiceNo"), resultSet.getString("location"),
                                    resultSet.getString("status"), resultSet.getString("classification"));
                        }
                    }
                    items.add(inventoryItem);
                }
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

        return items.iterator();
    }

    public void add(Object object) {
        InventoryItem inventoryItem = null;
        InventoryItem inventoryStarter = (InventoryItem) object;

        String query;
        Connection con = DBConnection.getConnection();
        PreparedStatement preparedStatement;
        try {
            query = "SHOW TABLE STATUS LIKE 'inventoryitem'";
            preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt("Auto_increment");

            query = "INSERT INTO inventoryitem \n"
                    + "VALUES (NULL, ?, ?, ?, ?, ?)";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, inventoryStarter.getName());
            preparedStatement.setString(2, inventoryStarter.getStatus());
            preparedStatement.setString(3, inventoryStarter.getClassification());
            preparedStatement.setString(4, inventoryStarter.getInvoiceNo());
            preparedStatement.setString(5, inventoryStarter.getLocation());
            preparedStatement.execute();

            if (object instanceof NonITAsset || object instanceof ITAsset) {
                query = "INSERT INTO hardwareitem \n"
                        + "VALUES (?)";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1, id);
                preparedStatement.execute();

                if (object instanceof ITAsset) {
                    inventoryItem = (ITAsset) object;

                    query = "INSERT INTO itasset \n"
                            + "VALUES (?, ?, ?, ?)";
                    preparedStatement = con.prepareStatement(query);
                    preparedStatement.setInt(1, id);
                    preparedStatement.setInt(2, ((ITAsset) inventoryItem).getAssetTag());
                    preparedStatement.setString(3, ((ITAsset) inventoryItem).getServiceTag());
                    preparedStatement.setDate(4, new java.sql.Date(((ITAsset) inventoryItem).getDeliveryDate().getTime()));
                    preparedStatement.execute();

                    query = "INSERT INTO warranty \n"
                            + "VALUES (?, ?, ?)";
                    preparedStatement = con.prepareStatement(query);
                    preparedStatement.setInt(1, id);
                    preparedStatement.setDate(2, new java.sql.Date(((ITAsset) inventoryItem).getWarrantyStartDate().getTime()));
                    preparedStatement.setDate(3, new java.sql.Date(((ITAsset) inventoryItem).getWarrantyEndDate().getTime()));
                    preparedStatement.execute();

                    query = "INSERT INTO contract \n"
                            + "VALUES (?, ?, ?, ?)";
                    preparedStatement = con.prepareStatement(query);
                    preparedStatement.setInt(1, id);
                    preparedStatement.setDate(2, new java.sql.Date(((ITAsset) inventoryItem).getContractStartDate().getTime()));
                    preparedStatement.setDate(3, new java.sql.Date(((ITAsset) inventoryItem).getContractEndDate().getTime()));
                    preparedStatement.setFloat(4, ((ITAsset) inventoryItem).getContractMaintenanceCost());
                    preparedStatement.execute();
                } else if (object instanceof NonITAsset) {
                    inventoryItem = (NonITAsset) object;
                    query = "INSERT INTO warranty \n"
                            + "VALUES (?, ?, ?)";
                    preparedStatement = con.prepareStatement(query);
                    preparedStatement.setInt(1, id);
                    preparedStatement.setDate(2, new java.sql.Date(((NonITAsset) inventoryItem).getWarrantyStartDate().getTime()));
                    preparedStatement.setDate(3, new java.sql.Date(((NonITAsset) inventoryItem).getWarrantyEndDate().getTime()));
                    preparedStatement.execute();
                }
            } else if (object instanceof SoftwareItem) {
                inventoryItem = (SoftwareItem) object;
                query = "INSERT INTO softwareitem \n"
                        + "VALUES (?, ?)";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, ((SoftwareItem) inventoryItem).getLicenseKey());
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

    public int getID() {
        int id = 0;
        try {
            String query = "SELECT MAX(ID) FROM inventoryitem";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("MAX(id)");

            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        }
        return id;
    }

    public void update(Object object, String origKey) {
        InventoryItem inventoryItem = (InventoryItem) object;
        Connection con = DBConnection.getConnection();
        try {
            int id = Integer.parseInt(origKey);
            InventoryItem previous = (InventoryItem) get(origKey);
            InventoryItem current = (InventoryItem) object;

            DAO.getInstance().update("itemdata", new ItemData(current.getName(), current.getDescription(), current.getUnitPrice()), previous.getName());
            String query = "UPDATE inventoryitem SET itemData = ?, "
                    + "status= ?, classification = ?, invoiceNo=?, location=? WHERE ID = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            //preparedStatement.setInt(1, current.getID());
            preparedStatement.setString(1, current.getName());
            preparedStatement.setString(2, current.getStatus());
            preparedStatement.setString(3, current.getClassification());
            preparedStatement.setString(4, current.getInvoiceNo());
            preparedStatement.setString(5, current.getLocation());
            preparedStatement.setInt(6, previous.getID());
            preparedStatement.execute();

            String type = previous.getClassification();
            if (type.equalsIgnoreCase("Non-IT") || type.equalsIgnoreCase("IT")) {
                query = "DELETE FROM hardwareitem WHERE ID = ?";
                System.out.println(query + "\n");
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1, previous.getID());
                preparedStatement.execute();
            } else {
                if (type.equalsIgnoreCase("Soft")) {
                    query = "DELETE FROM softwareitem WHERE ID = ?";
                    System.out.println(query + "\n");
                    preparedStatement = con.prepareStatement(query);
                    preparedStatement.setInt(1, previous.getID());
                    preparedStatement.execute();
                }
            }

            if (object instanceof NonITAsset || object instanceof ITAsset) {
                query = "INSERT INTO hardwareitem \n"
                        + "VALUES (?)";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1, id);
                preparedStatement.execute();

                if (object instanceof ITAsset) {
                    inventoryItem = (ITAsset) object;
                    query = "INSERT INTO itasset \n"
                            + "VALUES (?, ?, ?, ?)";
                    preparedStatement = con.prepareStatement(query);
                    preparedStatement.setInt(1, id);
                    preparedStatement.setInt(2, ((ITAsset) inventoryItem).getAssetTag());
                    preparedStatement.setString(3, ((ITAsset) inventoryItem).getServiceTag());
                    preparedStatement.setDate(4, new java.sql.Date(((ITAsset) inventoryItem).getDeliveryDate().getTime()));
                    preparedStatement.execute();

                    query = "DELETE FROM warranty WHERE hardware = ?";
                    preparedStatement = con.prepareStatement(query);
                    preparedStatement.setInt(1, id);
                    preparedStatement.execute();

                    query = "DELETE FROM contract WHERE hardware = ?";
                    preparedStatement = con.prepareStatement(query);
                    preparedStatement.setInt(1, id);
                    preparedStatement.execute();

                    query = "INSERT INTO warranty \n"
                            + "VALUES (?, ?, ?)";
                    preparedStatement = con.prepareStatement(query);
                    preparedStatement.setInt(1, id);
                    preparedStatement.setDate(2, new java.sql.Date(((ITAsset) inventoryItem).getWarrantyStartDate().getTime()));
                    preparedStatement.setDate(3, new java.sql.Date(((ITAsset) inventoryItem).getWarrantyEndDate().getTime()));
                    preparedStatement.execute();

                    query = "INSERT INTO contract \n"
                            + "VALUES (?, ?, ?, ?)";
                    preparedStatement = con.prepareStatement(query);
                    preparedStatement.setInt(1, id);
                    preparedStatement.setDate(2, new java.sql.Date(((ITAsset) inventoryItem).getContractStartDate().getTime()));
                    preparedStatement.setDate(3, new java.sql.Date(((ITAsset) inventoryItem).getContractEndDate().getTime()));
                    preparedStatement.setFloat(4, ((ITAsset) inventoryItem).getContractMaintenanceCost());
                    preparedStatement.execute();

                } else if (object instanceof NonITAsset) {
                    inventoryItem = (NonITAsset) object;
                    query = "INSERT INTO warranty \n"
                            + "VALUES (?, ?, ?)";
                    preparedStatement = con.prepareStatement(query);
                    preparedStatement.setInt(1, id);
                    preparedStatement.setDate(2, new java.sql.Date(((NonITAsset) inventoryItem).getWarrantyStartDate().getTime()));
                    preparedStatement.setDate(3, new java.sql.Date(((NonITAsset) inventoryItem).getWarrantyEndDate().getTime()));
                    preparedStatement.execute();
                }
            } else if (object instanceof SoftwareItem) {
                inventoryItem = (SoftwareItem) object;
                query = "INSERT INTO softwareitem \n"
                        + "VALUES (?, ?)";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, ((SoftwareItem) inventoryItem).getLicenseKey());
                preparedStatement.execute();
            }

            query = "UPDATE itemdata SET name = ?, "
                    + "description= ?, unitPrice = ? WHERE name = ?;";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, current.getName());
            preparedStatement.setString(2, current.getDescription());
            preparedStatement.setFloat(3, current.getUnitPrice());
            preparedStatement.setString(4, current.getName());
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

    public void delete(Object object) {
        Connection con = DBConnection.getConnection();
        InventoryItem inventoryItem = (InventoryItem) object;
        try {
            String query = "DELETE FROM inventoryitem WHERE ID = ?;";
            PreparedStatement preparedStatement = con
                    .prepareStatement(query);
            preparedStatement.setInt(1, inventoryItem.getID());
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

    /*@Override
     public void update(Object object, String origKey) {
     // TODO Auto-generated method stub
		
     }*/
}
