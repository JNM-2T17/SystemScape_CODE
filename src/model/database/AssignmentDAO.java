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
import model.Assignment;
import model.ITAsset;
import model.ItemData;

/**
 *
 * @author Christian Gabriel
 */
public class AssignmentDAO implements IDBCUD {

    //returns all items in this table
    public Iterator get() {
        ArrayList<Assignment> assignments = new ArrayList<Assignment>();
        try {
            String query = "SELECT * FROM  assetassignment UNION SELECT * FROM  softwareassignment ORDER BY 1";
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Assignment assignment = new Assignment(resultSet.getInt("ID"), resultSet.getString("employee"), resultSet.getString("project"));
                assignments.add(assignment);

            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return assignments.iterator();

    }

    public Object get(String key) {
        ITAsset itAsset = null;
        try {

            String query = "SELECT * FROM softwareassignment where ID =  ? ORDER  BY 1";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(key));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("employee");
            }
            else{
                query = "SELECT * FROM assetassignment where ID =  ? ORDER  BY 1";
                preparedStatement = DBConnection.getConnection().prepareStatement(query);
                preparedStatement.setInt(1, Integer.parseInt(key));
                resultSet = preparedStatement.executeQuery();
                
                if (resultSet.next()) {
                    return resultSet.getInt("employee");
                }
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;

    }

    public Iterator search(String searchStr) {

        ArrayList<Assignment> assignments = new ArrayList<Assignment>();

        try {
            String query = "SELECT * FROM  assetassignment UNION SELECT * "
                         + "FROM  softwareassignment WHERE ID LIKE ? ORDER BY 1";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, "%" + searchStr + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Assignment assignment = new Assignment(resultSet.getInt("ID"), resultSet.getString("employee"), resultSet.getString("project"));
                assignments.add(assignment);

            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return assignments.iterator();

    }

    public void add(Object object) {

        Assignment assignment = (Assignment) object;

        try {
            String query = "INSERT INTO assignment VALUE (?, ?, ?);";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, assignment.getID());
            preparedStatement.setString(2, assignment.getEmployee());
            preparedStatement.setString(3, assignment.getProject());

            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void update(Object object, String key) {

        Assignment assignment = (Assignment) object;

        try {

            String query = "UPDATE assignment SET ID = ?, employee = ?, project = ? WHERE deliveryDate = ?;";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, assignment.getID());
            preparedStatement.setString(2, assignment.getEmployee());
            preparedStatement.setString(3, assignment.getProject());
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    public void delete(Object object) {
        Assignment assignment = (Assignment) object;

        try {
            String query = "DELETE FROM assignment where ID = ?;";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, assignment.getID());
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }
}
