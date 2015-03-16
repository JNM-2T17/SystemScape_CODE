package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private String driverName;
	private String database;
	private String address;
	private String username;
	private String password;
	
	private static Connection con = null;
	
	private DBConnection() {
		driverName = "com.mysql.jdbc.Driver";
		database = "caista";
		address = "jdbc:mysql://127.0.0.1:3306/";
		username = "root";
		password = "";
		
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection( address + database, username, password);
		} catch( SQLException sqle ) {
			sqle.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		if( con == null ) {
			new DBConnection();
		} 
		
		return con;
	}
	
	public static void close() {
		if( con != null ) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = null;
		}
	}
}
