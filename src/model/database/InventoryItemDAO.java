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

                if (itemClass.equalsIgnoreCase("hardware")) {
                    inventoryItem = new HardwareItem(resultSet.getInt("ID"), resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getFloat("unitPrice"),
                            resultSet.getString("invoiceNo"),
                            resultSet.getString("location"),
                            resultSet.getString("status"),
                            resultSet.getString("classification"));
                } else if (itemClass.equalsIgnoreCase("software")) {
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

            if (resultSet.next()) {
                InventoryItem inventoryItem;
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
                            String query6 = "SELECT * FROM warranty WHERE ID = \"" + resultSet.getString("ID") + "\"";
                            PreparedStatement preparedStatement6 = con.prepareStatement(query6);
                            ResultSet resultSet6 = preparedStatement4.executeQuery();

                            String query7 = "SELECT * FROM contract WHERE ID = \"" + resultSet.getString("ID") + "\"";
                            PreparedStatement preparedStatement7 = con.prepareStatement(query7);
                            ResultSet resultSet7 = preparedStatement7.executeQuery();

                            inventoryItem = new ITAsset(resultSet.getInt("ID"),
                                    resultSet2.getString("name"), resultSet2.getString("description"),
                                    resultSet2.getFloat("unitPrice"), resultSet.getString("invoiceNo"),
                                    resultSet.getString("location"), resultSet.getString("status"),
                                    resultSet.getString("classification"), resultSet4.getInt("assetTag"), resultSet4.getString("serviceTag"),
                                    resultSet4.getDate("deliveryDate"), resultSet6.getDate("startDate"), resultSet6.getDate("endDate"),
                                    resultSet7.getDate("startDate"), resultSet7.getDate("endDate"), resultSet7.getFloat("maintenanceCost"));
                        } else {//Else NonITAsset
                            inventoryItem = new NonITAsset(resultSet.getInt("ID"), resultSet2.getString("name"),
                                    resultSet2.getString("description"), resultSet2.getFloat("unitPrice"),
                                    resultSet.getString("invoiceNo"), resultSet.getString("location"),
                                    resultSet.getString("status"), resultSet.getString("classification"));
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
        ArrayList<String> results = new ArrayList<String>();
        Connection con = DBConnection.getConnection();
        try {
            String query = director.getQuery(conditions);
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                results.add(resultSet.getString("itemdata"));
                results.add(resultSet.getString("description"));
                results.add(resultSet.getString("status"));
                results.add(resultSet.getString("classification"));
                results.add(resultSet.getString("unitprice"));
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

    public Iterator getDistinct(String string) {
        ArrayList<String> results = new ArrayList<String>();
        Connection con = DBConnection.getConnection();
        try {
            String query = "SELECT DISTINCT " + string + " FROM inventoryitem";
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
                }
            } else if (object instanceof SoftwareItem) {
                inventoryItem = (SoftwareItem) object;
                query = "INSERT INTO softwareitem \n"
                        + "VALUES (?, ?)";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, ((SoftwareItem) inventoryItem).getLicenseKey());
                System.out.println("License Key in DAO: " + ((SoftwareItem) inventoryItem).getLicenseKey());
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

    public void update(Object object) {
        InventoryItem inventoryItem = (InventoryItem) object;
        Connection con = DBConnection.getConnection();
        try {
            int id = ((InventoryItem) object).getID();
            InventoryItem previous = (InventoryItem) get(String.valueOf(id));
            InventoryItem current = (InventoryItem) object;

            String query = "UPDATE inventoryitem SET itemData = ?, "
                    + "status= ?, classification = ?, invoiceNo=?, location=? WHERE ID = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            //preparedStatement.setInt(1, current.getID());
            preparedStatement.setString(1, current.getName());
            preparedStatement.setString(2, current.getStatus());
            preparedStatement.setString(3, current.getClassification());
            preparedStatement.setString(4, current.getInvoiceNo());
            preparedStatement.setString(5, current.getLocation());
            preparedStatement.setInt(6, current.getID());
            preparedStatement.execute();

            String type = "";
            if (previous.getClassification().equalsIgnoreCase("hardware") || previous.getClassification().equalsIgnoreCase("IT")) {
                type = type + "hardwareitem";
            } else if (previous.getClassification().equalsIgnoreCase("software")) {
                type = type + "softwareitem";
            }
            query = "DELETE FROM " + type + " WHERE ID = ?;";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, previous.getID());
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
                }
            } else if (object instanceof SoftwareItem) {
                inventoryItem = (SoftwareItem) object;
                query = "INSERT INTO softwareitem \n"
                        + "VALUES (?, ?)";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, ((SoftwareItem) inventoryItem).getLicenseKey());
                System.out.println("License Key in DAO: " + ((SoftwareItem) inventoryItem).getLicenseKey());
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

}
