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
import model.Employee;
import model.ITAsset;

/**
 *
 * @author Christian Gabriel
 */
public class AssignmentDAO implements IDBCUD {

    //returns all items in this table
    public Iterator get() {
        ArrayList<Assignment> assignments = new ArrayList<Assignment>();
        try {
            String query = "SELECT * FROM itemassignment IA, employee E WHERE IA.itemID = E.ID";
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

            	Assignment assignment = new Assignment(resultSet.getInt("itemID"), 
						new Employee( resultSet.getInt("employeeID"), resultSet.getString("name") ) );
                assignments.add(assignment);

            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return assignments.iterator();

    }

    public Object get(String key) {
        String searchStr[] = key.split(" ");
        try {
            
            String query = "SELECT * FROM itemassignment IA, employee E WHERE IA.employeeID = E.ID AND IA.itemID = ?";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(searchStr[0]));
            //preparedStatement.setInt(2, Integer.parseInt(searchStr[1]));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Assignment(resultSet.getInt("itemID"), 
						new Employee( resultSet.getInt("employeeID"), resultSet.getString("name") ) );
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;

    }

    public Iterator search(String searchStr) {

        ArrayList<Assignment> assignments = new ArrayList<Assignment>();

        try {
            String query = "SELECT * FROM itemassignment IA, employee E WHERE IA.employeeID = E.ID AND ( IA.itemID = ? OR IA.employeeID = ? )";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(searchStr) );
            preparedStatement.setInt(2, Integer.parseInt(searchStr) );
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                
                Assignment assignment = new Assignment(resultSet.getInt("itemID"), new Employee( resultSet.getInt("employeeID"), resultSet.getString("name") ) );
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
            String query = "INSERT INTO itemassignment VALUES (?, ?, null, null);";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, assignment.getID());
            preparedStatement.setInt(2, assignment.getEmployee().getID());
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void update(Object object, String key) {

        Assignment assignment = (Assignment) object;

        try {

            String query = "UPDATE itemassignment SET itemID = ?, employeeID = ? WHERE itemID = ?"; //assumes item can only be assigned to one employee at a time
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, assignment.getID());
            preparedStatement.setInt(2, assignment.getEmployee().getID());
            preparedStatement.setInt(3, assignment.getID());
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    public void delete(Object object) {
        Assignment assignment = (Assignment) object;

        try {
            String query = "DELETE FROM itemassignment where itemID = ? AND employeeID = ?";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, assignment.getID());
            preparedStatement.setInt(2, assignment.getEmployee().getID());
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }
}
