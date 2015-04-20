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
                    user = new User(resultSet.getString("username"), resultSet.getString("password"), true);
                } else {
                    user = new User(resultSet.getString("username"), resultSet.getString("password"), false);
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
    
    public Iterator getUsers() {
        ArrayList<User> users = new ArrayList();
        User user;

        try {
            String query = "SELECT * FROM user";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = new User(resultSet.getString("username"), resultSet.getString("password"), false);
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
                    user = new User(resultSet.getString("username"), resultSet.getString("password"), true);
                } else {
                    user = new User(resultSet.getString("username"), resultSet.getString("password"), false);
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
                    user = new User(resultSet.getString("username"), resultSet.getString("password"), true);
                } else {
                    user = new User(resultSet.getString("username"), resultSet.getString("password"), false);
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

            String query = "INSERT INTO user VALUES(?,?);";
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
           
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

}
