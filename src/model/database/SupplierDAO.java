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
import model.Supplier;
import model.builder.QueryFilterDirector;
import model.builder.SupplierFilterQueryBuilder;

/**
 *
 * @author Christian Gabriel
 */
public class SupplierDAO implements IDBCUD {

    public Iterator get() {
        Connection con = DBConnection.getConnection();
        ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
        try {
            String query = "SELECT * FROM supplier";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Supplier supplier = new Supplier(resultSet.getString("name"), resultSet.getString("country"), resultSet.getString("state"), resultSet.getString("city"));

                String query2 = "SELECT * FROM suppliercontact  WHERE supplier=\"" + resultSet.getString("name") + "\"";
                PreparedStatement preparedStatement2 = con.prepareStatement(query2);
                ResultSet resultSet2 = preparedStatement2.executeQuery();

                while (resultSet2.next()) {
                    supplier.addSupplierContact(resultSet2.getString("supplier"), resultSet2.getString("type"), resultSet2.getInt("value"));
                }
                suppliers.add(supplier);
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

        return suppliers.iterator();
    }

    public Object get(String key) {
        Connection con = DBConnection.getConnection();
        try {
            String query = "SELECT * FROM supplier WHERE name = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Supplier supplier = new Supplier(resultSet.getString("name"), resultSet.getString("country"), resultSet.getString("state"), resultSet.getString("city"));
                String query2 = "SELECT * FROM suppliercontact WHERE supplier=\"" + resultSet.getString("name") + "\"";
                PreparedStatement preparedStatement2 = con.prepareStatement(query2);
                ResultSet resultSet2 = preparedStatement2.executeQuery();

                while (resultSet2.next()) {
                    supplier.addSupplierContact(resultSet2.getString("supplier"), resultSet2.getString("type"), resultSet2.getInt("value"));
                }

                try {
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                return supplier;
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
        ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
        try {
            String query = "SELECT * FROM supplier WHERE name LIKE ? " + "ORDER BY 1";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, "%" + searchStr + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Supplier supplier = new Supplier(resultSet.getString("name"), resultSet.getString("country"), resultSet.getString("state"), resultSet.getString("city"));
                String query2 = "SELECT * FROM suppliercontact WHERE supplier=\"" + resultSet.getString("name") + "\"";
                PreparedStatement preparedStatement2 = con.prepareStatement(query2);
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                while (resultSet2.next()) {
                    supplier.addSupplierContact(resultSet2.getString("supplier"), resultSet2.getString("type"), resultSet2.getInt("value"));
                }
                suppliers.add(supplier);
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
        return suppliers.iterator();
    }

    public Iterator filter(Iterator conditions) {
        Connection con = DBConnection.getConnection();
        QueryFilterDirector director = new QueryFilterDirector(new SupplierFilterQueryBuilder());
        ArrayList<String> results = new ArrayList<String>();
        try {
            String query = director.getQuery(conditions);
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                results.add(resultSet.getString("name"));
                results.add(resultSet.getString("country") + " " + resultSet.getString("state") + " " + resultSet.getString("city"));
                results.add(resultSet.getString("value"));
            }
            return results.iterator();
        } catch (Exception Exception) {
            Exception.printStackTrace();
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
        Connection con = DBConnection.getConnection();
        ArrayList<String> results = new ArrayList<String>();
        try {
            String query = "SELECT DISTINCT " + string + " FROM supplier";
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
        Connection con = DBConnection.getConnection();
        Supplier supplier = (Supplier) object;
        try {

            String query = "INSERT INTO supplier VALUES(?,?,?,?);";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, supplier.getName());
            preparedStatement.setString(2, supplier.getCountry());
            preparedStatement.setString(3, supplier.getState());
            preparedStatement.setString(4, supplier.getCity());
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

    public void update(Object object, String origKey) {
        Connection con = DBConnection.getConnection();
        Supplier supplier = (Supplier) object;
        try {
            String query = "UPDATE supplier SET name = ?,country = ?, "
                    + "state= ?, city = ? WHERE name = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, supplier.getName());
            preparedStatement.setString(2, supplier.getCountry());
            preparedStatement.setString(3, supplier.getState());
            preparedStatement.setString(4, supplier.getCity());
            preparedStatement.setString(5, origKey);
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
        Supplier supplier = (Supplier) object;
        try {
            String query = "DELETE FROM supplier WHERE name = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, supplier.getName());
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
