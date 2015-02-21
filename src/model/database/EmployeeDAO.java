/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caista.model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import model.Employee;

/**
 *
 * @author Christian Gabriel
 */
public class EmployeeDAO implements IDBCUD {

    public Iterator get() {
        ArrayList<Employee> employees = new ArrayList();

        try {
            String query = "SELECT * FROM employee";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Employee e = new Employee(rs.getInt("ID"), rs.getString("name"));
                employees.add(e);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return employees.iterator();
    }

    public Object get(String key) {
        try {
            String query = "SELECT * FROM employee WHERE name = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(query);
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Employee e = new Employee(rs.getInt("ID"), rs.getString("name"));
                return e;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return null;
    }

    public Iterator search(String searchStr) {

        ArrayList<Employee> employees = new ArrayList<Employee>();
        try {

            String query = "SELECT * FROM employee WHERE name LIKE ? " + "ORDER BY 1";
            Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, "%" + searchStr + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee e = new Employee(rs.getInt("ID"), rs.getString("name"));
                employees.add(e);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return employees.iterator();
    }

    public void add(Object obj) {

        Employee e = (Employee) obj;
        try {

            String stmt = "INSERT INTO employee VALUES(?,?);";
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(stmt);
            ps.setInt(1, e.getID());
            ps.setString(2, e.getName());
            ps.execute();
        } catch (SQLException se) {
            se.printStackTrace();
        }

    }

    public void update(Object obj, String origKey) {
        Employee e = (Employee) obj;

        try {
            String stmt = "UPDATE employee SET ID = ?,name = ? WHERE ID = ?;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(stmt);
            ps.setInt(1, e.getID());
            ps.setString(2, e.getName());//not sure bout the address format
            ps.setString(3, origKey);
            ps.execute();
        } catch (SQLException se) {
            se.printStackTrace();
        }

    }

    public void delete(Object obj) {

        Employee s = (Employee) obj;
        try {
            String stmt = "DELETE FROM employee WHERE ID = ?;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(stmt);
            ps.setInt(1, s.getID());
            ps.execute();
        } catch (SQLException se) {
            se.printStackTrace();
        }

    }
}
