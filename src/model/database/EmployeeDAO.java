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

import model.Employee;
import model.builder.QueryFilterDirector;
import model.builder.SupplierFilterQueryBuilder;

/**
 *
 * @author Christian Gabriel
 */
public class EmployeeDAO implements IDBCUD {

    public Iterator get() {
        ArrayList<Employee> employees = new ArrayList();
        Date startDate, endDate;
        try {
            String query = "SELECT * FROM employee";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Employee employee = new Employee(resultSet.getInt("ID"), resultSet.getString("name"), resultSet.getString("status"));
                String query2 = "SELECT * from project WHERE employee =\"" + resultSet.getString("name") + "\"";
                PreparedStatement preparedStatement2 = DBConnection.getConnection().prepareStatement(query2);
                ResultSet resultSet2 = preparedStatement2.executeQuery();

                while (resultSet2.next()) {
                	startDate = new java.util.Date(resultSet.getDate("startDate").getTime());
                    endDate = new java.util.Date(resultSet.getDate("endDate").getTime());
                	employee.addProject(resultSet2.getString("name"), startDate, endDate, resultSet2.getString("employee"));
                }
                employees.add(employee);
                
            
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return employees.iterator();
    }

    public Object get(String key) {
    	Date startDate, endDate;
        try {
            String query = "SELECT * FROM employee WHERE ID = ?";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Employee employee = new Employee(resultSet.getInt("ID"), resultSet.getString("name"), resultSet.getString("status"));
                String query2 = "SELECT * from project WHERE employee =\"" + resultSet.getString("employee") + "\"";
                PreparedStatement preparedStatement2 = DBConnection.getConnection().prepareStatement(query2);
                ResultSet resultSet2 = preparedStatement2.executeQuery();

                while (resultSet2.next()) {
                	startDate = new java.util.Date(resultSet.getDate("startDate").getTime());
                    endDate = new java.util.Date(resultSet.getDate("endDate").getTime());
                	employee.addProject(resultSet2.getString("name"), startDate, endDate, resultSet2.getString("employee"));
                }
                return employee;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public Iterator search(String searchStr) {

        ArrayList<Employee> employees = new ArrayList<Employee>();
        Date startDate, endDate;
        try {

            String query = "SELECT * FROM employee WHERE ID LIKE ? " + "ORDER BY 1";
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + searchStr + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee(resultSet.getInt("ID"), resultSet.getString("name"), resultSet.getString("status"));
                String query2 = "SELECT * from project WHERE employee =\"" + resultSet.getString("employee") + "\"";
                PreparedStatement preparedStatement2 = DBConnection.getConnection().prepareStatement(query2);
                ResultSet resultSet2 = preparedStatement2.executeQuery();

                while (resultSet2.next()) {
                	startDate = new java.util.Date(resultSet.getDate("startDate").getTime());
                    endDate = new java.util.Date(resultSet.getDate("endDate").getTime());
                	employee.addProject(resultSet2.getString("name"), startDate, endDate, resultSet2.getString("employee"));
                }
                employees.add(employee);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return employees.iterator();
    }
    //under construction
//    public Iterator filter(Iterator conditions){
//        QueryFilterDirector director = new QueryFilterDirector(new SupplierFilterQueryBuilder());
//        ArrayList<String> results = new ArrayList<String>();
//        try {
//            String query = director.getQuery(conditions);
//            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                results.add(resultSet.getString("name"));
//                results.add(resultSet.getString("country"));
//                results.add(resultSet.getString("state"));
//                results.add(resultSet.getString("city"));
//                results.add(resultSet.getString("value"));
//            }
//            return results.iterator();
//        }catch(Exception exception){
//            exception.printStackTrace();
//        }
//        return null;
//   }
    
    public Iterator getDistinct(String string){
        ArrayList<String> results = new ArrayList<String>();
        try {
            String query = "SELECT DISTINCT "+string+" FROM employee";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                results.add(resultSet.getString(1));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return results.iterator();
    }
    
    public void add(Object object) {

        Employee employee = (Employee) object;
        try {

            String query = "INSERT INTO employee VALUES(?,?);";
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, employee.getID());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3, employee.getStatus());
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    public void update(Object object, String origKey) {
        Employee employee = (Employee) object;

        try {
            String query = "UPDATE employee SET ID = ?,name = ?, status=? WHERE ID = ?;";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, employee.getID());
            preparedStatement.setString(2, employee.getName());//not sure bout the address format
            preparedStatement.setString(3, employee.getStatus());
            preparedStatement.setString(4, origKey);
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    public void delete(Object object) {

        Employee employee = (Employee) object;
        try {
            String query = "DELETE FROM employee WHERE ID = ?;";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, employee.getID());
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }
}
