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
import model.ItemData;

/**
 *
 * @author Christian Gabriel
 */
public class ItemDataDAO implements IDBCUD {

    //returns all items in this table
    public Iterator get() {
        Connection con = DBConnection.getConnection();
        ArrayList<ItemData> itemDatas = new ArrayList<ItemData>();
        try {
            String query = "SELECT * FROM ItemData ORDER BY 1";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                ItemData itemData = new ItemData(resultSet.getString("name"), resultSet.getString("description"), resultSet.getFloat("unitPrice"));
                itemDatas.add(itemData);

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

        return itemDatas.iterator();

    }

    public Object get(String key) {
        Connection con = DBConnection.getConnection();
        ItemData itemData = null;

        try {

            String query = "SELECT * FROM ItemData where name =  ? ORDER  BY 1";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                itemData = new ItemData(resultSet.getString("name"), resultSet.getString("description"), resultSet.getFloat("unitPrice"));

                try {
                    if(con!=null)
                        con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                return itemData;
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

        return null;

    }

    public Iterator search(String searchStr) {
        Connection con = DBConnection.getConnection();
        ArrayList<ItemData> itemDatas = new ArrayList<ItemData>();

        try {
            String query = "SELECT * FROM ItemData where name LIKE ? ORDER BY 1";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, "%" + searchStr + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                ItemData itemData = new ItemData(resultSet.getString("name"), resultSet.getString("description"), resultSet.getFloat("unitPrice"));
                itemDatas.add(itemData);

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
        return itemDatas.iterator();

    }

    public void add(Object object) {
        Connection con = DBConnection.getConnection();
        ItemData itemData = (ItemData) object;
        Object check = get(itemData.getName());
        if(check==null){
            try {
                String query = "INSERT INTO itemdata (name, description, unitPrice)\n" + 
                                       "VALUES(?,?,?)";
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, itemData.getName());
                preparedStatement.setString(2, itemData.getDescription());
                preparedStatement.setFloat(3, itemData.getUnitPrice());
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
        }else{
            update(itemData, itemData.getName());
        }

    }

    public void update(Object object, String key) {
        Connection con = DBConnection.getConnection();
        ItemData itemData = (ItemData) object;

        try {

            String query = "UPDATE ItemData SET name = ?, description = ?, unitPrice = ? WHERE name = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, itemData.getName());
            preparedStatement.setString(2, itemData.getDescription());
            preparedStatement.setFloat(3, itemData.getUnitPrice());
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
        ItemData itemData = (ItemData) object;

        try {
            String query = "DELETE FROM ItemData where name = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, itemData.getName());
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
