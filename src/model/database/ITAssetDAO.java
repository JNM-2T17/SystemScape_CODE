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
import model.ITAsset;
import model.ItemData;

/**
 *
 * @author Christian Gabriel
 */
public class ITAssetDAO implements IDBCUD {

    //returns all items in this table
    public Iterator get() {
        ArrayList<ITAsset> itemAssets = new ArrayList<ITAsset>();
        try {
            String query = "SELECT * FROM itasset ORDER BY 1";
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                ITAsset itAsset = new ITAsset(resultSet.getInt("ID"), resultSet.getInt("assetTag"), resultSet.getString("serviceTag"), resultSet.getDate("deliveryDate"));
                itemAssets.add(itAsset);

            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return itemAssets.iterator();

    }

    public Object get(String key) {
        ITAsset itAsset = null;
        try {

            String query = "SELECT * FROM itasset where ID =  ? ORDER  BY 1";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(key));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                itAsset = new ITAsset(resultSet.getInt("ID"), resultSet.getInt("assetTag"), resultSet.getString("serviceTag"), resultSet.getDate("deliveryDate"));
                return itAsset;
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;

    }

    public Iterator search(String searchStr) {

        ArrayList<ITAsset> itAssets = new ArrayList<ITAsset>();

        try {
            String query = "SELECT * FROM itasset where ID LIKE ? ORDER BY 1";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, "%" + searchStr + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                ITAsset itAsset = new ITAsset(resultSet.getInt("ID"), resultSet.getInt("assetTag"), resultSet.getString("serviceTag"), resultSet.getDate("deliveryDate"));
                itAssets.add(itAsset);

            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return itAssets.iterator();

    }

    public void add(Object object) {

        ITAsset itAsset = (ITAsset) object;

        try {
            String query = "INSERT INTO itasset VALUE (?, ?, ?, ?);";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, itAsset.getID());
            preparedStatement.setInt(2, itAsset.getAssetTag());
            preparedStatement.setString(3, itAsset.getServiceTag());
            preparedStatement.setDate(4, new java.sql.Date(itAsset.getDeliveryDate().getTime()));

            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void update(Object object, String key) {

        ITAsset itAsset = (ITAsset) object;

        try {

            String query = "UPDATE itasset SET ID = ?, assetTag = ?, serviceTag = ? WHERE deliveryDate = ?;";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, itAsset.getID());
            preparedStatement.setInt(2, itAsset.getAssetTag());
            preparedStatement.setString(3, itAsset.getServiceTag());
            preparedStatement.setDate(4, new java.sql.Date(itAsset.getDeliveryDate().getTime()));
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    public void delete(Object object) {
        ITAsset itAsset = (ITAsset) object;

        try {
            String query = "DELETE FROM itasset where ID = ?;";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, itAsset.getID());
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }
}
