package model.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance = null;

    private String driverName;
    private String url;
    private String database;
    private String username;
    private String password;

    /**
     * constructor for connection
     *
     * @param dN driver name
     * @param url URL
     * @param db database to access
     * @param un username
     * @param pw password
     */
    private DBConnection() {
        driverName = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/";
        database = "caista";
        username = "root";
        BufferedReader br = null;
        try {
        	br = new BufferedReader(new FileReader(new File("config.txt")));
            password = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        
    }
     
    /**
     * returns an instance of the Database Connection
     *
     * @return instance of the Database Connection
     */
    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }

        return instance;
    }

    /**
     * returns a connection to database
     *
     * @return connection to database
     */
    public static Connection getConnection() {
        if (instance == null) {
            instance = new DBConnection();
        }

        try {
            return DriverManager.getConnection(instance.getUrl()
                    + instance.getDatabase(),
                    instance.getUsername(),
                    instance.getPassword());
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return null;
    }

    /**
     * returns database URL
     *
     * @return database URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * returns database name
     *
     * @return database name
     */
    public String getDatabase() {
        return database;
    }

    /**
     * returns username
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    private String getPassword() {
        return password;
    }

    /**
     * returns whether password is correct or not
     *
     * @param password password to checkPassword
     * @return whether password is correct or not
     */
    public boolean isCorrectPassword(String password) {
        return password.equals(this.password);
    }
}
