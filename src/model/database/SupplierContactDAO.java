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
import model.SupplierContact;

/**
 *
 * @author Christian Gabriel
 */
public class SupplierContactDAO implements IDBCUD {

    public Iterator get() {
        Connection con = DBConnection.getConnection();
        ArrayList<SupplierContact> supplierContacts = new ArrayList();
        try {
            String query = "SELECT * FROM SupplierContact ORDER BY 1";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                SupplierContact supplierContact = new SupplierContact(resultSet.getString("supplier"), resultSet.getString("type"), resultSet.getInt("value"));
                supplierContacts.add(supplierContact);

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
        return supplierContacts.iterator();

    }

    public Object get(String key) {
        Connection con = DBConnection.getConnection();
        SupplierContact supplierContacts = null;

        try {
            String query = "SELECT * FROM SupplierContact where value = ? ORDER  BY 1";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                supplierContacts = new SupplierContact(resultSet.getString("supplier"), resultSet.getString("type"), resultSet.getInt("value"));

                try {
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException sqlee) {
                    sqlee.printStackTrace();
                }

                return supplierContacts;

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

    public Iterator search(String searchStr) {
        Connection con = DBConnection.getConnection();
        ArrayList<SupplierContact> supplierContacts = new ArrayList<SupplierContact>();

        try {
            String query = "SELECT * FROM SupplierContact where type LIKE ?, value LIKE ? ORDER BY 1";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, "%" + searchStr
                    + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                SupplierContact supplierContact = new SupplierContact(resultSet.getString("supplier"), resultSet.getString("type"), resultSet.getInt("value"));
                supplierContacts.add(supplierContact);

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
        return supplierContacts.iterator();
    }

    public void add(Object object) {
        Connection con = DBConnection.getConnection();
        SupplierContact supplierContacts = (SupplierContact) object;

        try {
            String query = "INSERT INTO suppliercontact VALUES(?,?,?);";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, supplierContacts.getSupplier());
            preparedStatement.setString(2, supplierContacts.getType());
            preparedStatement.setInt(3, supplierContacts.getValue());
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

    public void update(Object object, String key) {
        Connection con = DBConnection.getConnection();
        SupplierContact supplierContacts = (SupplierContact) object;

        try {

            String query = "UPDATE SupplierContact SET supplier= ?, type= ?, value= ? WHERE type= ? AND value = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, supplierContacts.getSupplier());
            preparedStatement.setString(2, supplierContacts.getType());
            preparedStatement.setInt(3, supplierContacts.getValue());
            preparedStatement.setString(4, key);
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

    public void delete(Object object) {
        Connection con = DBConnection.getConnection();
        //SupplierContact supplierContact = (SupplierContact) object;
        String supplier = (String)object;
        try {
            String query = "DELETE FROM SupplierContact WHERE supplier = ?;";
            PreparedStatement preparedStatement = con
                    .prepareStatement(query);
            //preparedStatement.setString(1, supplierContact.getType());
            //preparedStatement.setInt(2, supplierContact.getValue());
            preparedStatement.setString(1, supplier);
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
