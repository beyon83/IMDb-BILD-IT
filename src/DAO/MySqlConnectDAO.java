package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnectDAO {
	
	final static String DRIVER = "com.mysql.jdbc.Driver";
	final static String LOCAL_HOST = "jdbc:mysql://localhost/imdb";
	final static String USER_NAME = "root";
	final static String PASSWORD = "";
	
	public static Connection mySqlConnect() {
		
		Connection connection = null;
		
		try {
			Class.forName(DRIVER);
			System.out.println("driver loaded");
			connection = DriverManager.getConnection(LOCAL_HOST, USER_NAME, PASSWORD);
			System.out.println("database connected");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
