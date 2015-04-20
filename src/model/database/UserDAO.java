package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import model.Employee;
import model.User;

/**
 *
 * @author Laptop
 */
public class UserDAO implements IDBGet {

    public Iterator get() {
        ArrayList<User> users = new ArrayList();
        User user;

        try {
            String query = "SELECT * FROM user";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String query2 = "SELECT username FROM admin WHERE username = ?";
                PreparedStatement preparedStatement2 = DBConnection.getConnection().prepareStatement(query2);
                preparedStatement2.setString(1, resultSet.getString("username"));
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                if (resultSet2.next()) {
                    user = new User(resultSet.getString("username"), resultSet.getString("password"), true, resultSet.getInt("employeeID"));
                } else {
                    user = new User(resultSet.getString("username"), resultSet.getString("password"), false, resultSet.getInt("employeeID"));
                }
                users.add(user);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        try {
            DBConnection.getConnection().close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return users.iterator();
    }
    
    public String getID(String key){
    	String username = "";
    	try {
            String query = "SELECT username FROM user where password = ?";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                 username = resultSet.getString("username");
                
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        
        }
        return username;
    }
    
    public String getUserUsingID(String key){
    	String username = "";
    	try {
            String query = "SELECT username FROM user where employeeID = ?";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                 username = resultSet.getString("username");
                
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        
        }
        return username;
    }
    
    public Iterator getUsers() {
        ArrayList<User> users = new ArrayList();
        User user;

        try {
            String query = "SELECT * FROM user";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = new User(resultSet.getString("username"), resultSet.getString("password"), false, resultSet.getInt("employeeID"));
                users.add(user);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        try {
            DBConnection.getConnection().close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return users.iterator();
    }

    public Object get(String key) {
        User user;
        try {
            String query = "SELECT * FROM user WHERE username = ?";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String query2 = "SELECT username FROM admin WHERE username = ?";
                PreparedStatement preparedStatement2 = DBConnection.getConnection().prepareStatement(query2);
                preparedStatement2.setString(1, resultSet.getString("username"));
                ResultSet resultSet2 = preparedStatement2.executeQuery();

                if (resultSet2.next()) {
                    user = new User(resultSet.getString("username"), resultSet.getString("password"), true, resultSet.getInt("employeeID"));
                } else {
                    user = new User(resultSet.getString("username"), resultSet.getString("password"), false, resultSet.getInt("employeeID"));
                }

                try {
                    DBConnection.getConnection().close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                return user;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        try {
            DBConnection.getConnection().close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public Iterator search(String searchStr) {
        ArrayList<User> users = new ArrayList<User>();
        User user;

        try {
            String query = "SELECT * FROM user WHERE username LIKE ? " + "ORDER BY 1";
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + searchStr + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String query2 = "SELECT username FROM admin WHERE username = ?";
                PreparedStatement preparedStatement2 = DBConnection.getConnection().prepareStatement(query2);
                preparedStatement2.setString(1, resultSet.getString("username"));
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                if (resultSet2.next()) {
                    user = new User(resultSet.getString("username"), resultSet.getString("password"), true, resultSet.getInt("employeeID"));
                } else {
                    user = new User(resultSet.getString("username"), resultSet.getString("password"), false, resultSet.getInt("employeeID"));
                }
                users.add(user);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return users.iterator();

    }
    
    public void add(Object object) {

        User user = (User) object;
        try {

            String query = "INSERT INTO user VALUES(?,?,?);";
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getEmployeeID());
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }
    
    public void adminRights(String name) {

       
        try {

            String query = "INSERT INTO admin VALUES(?);";
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
           
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }
    

    public void updateNotificationDuration(String contractDuration, String warrantyDuration, String username){
        try {

            String query = "UPDATE admin SET VALUES warrantyDuration = ?, contractDuration = ? WHERE username = ?";
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, warrantyDuration);
            preparedStatement.setString(2, contractDuration);
            preparedStatement.setString(3, username);
           
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public Iterator getNotificationDuration(String username){
        ArrayList<String> durations = new ArrayList();
        try {
            String query = "SELECT warrantyDuration, contractDuration FROM admin WHERE username = ?";
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                durations.add(resultSet.getString("warrantyDuration"));
                durations.add(resultSet.getString("contractDuration"));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return durations.iterator();

    }
    
    public void update(Object object, String origKey) {
        User user = (User) object;

        try {
            String query = "UPDATE user SET password = ?, employeeID=?, type=? WHERE username = ?;";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            //preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(1, user.getPassword());//not sure bout the address format
            preparedStatement.setInt(2, user.getEmployeeID());
            
            preparedStatement.setString(3, origKey);
            preparedStatement.execute();
            
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }
    
    public void removeAdminRights(String user) {

        
        try {
            String query = "DELETE FROM admin WHERE username = ?;";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.execute();
            
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

}
