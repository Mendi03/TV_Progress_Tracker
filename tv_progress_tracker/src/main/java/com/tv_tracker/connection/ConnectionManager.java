package com.tv_tracker.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
	
	private static Connection connection = null;

	private ConnectionManager(){};

	private static void makeConnection() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {

		Properties props = new Properties();

		//relative path to the config.properties file:
        props.load(new FileInputStream("src/main/resources/config.properties"));

        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

		//Might need to uncomment the following if getting class not found excepetion

        // Class t = Class.forName("com.mysql.cj.jdbc.Driver");
        // System.out.println(t);
		// Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(url, username, password);

		

	}

	public static Connection getConnection() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {

		if (ConnectionManager.connection == null) {
			makeConnection();
		}

		return connection;
	}

	public static void main(String[] args) {

		Connection conn = null;
		
		try {
			conn = ConnectionManager.getConnection();
			System.out.println("Connected");
		} catch (ClassNotFoundException e1) {
			System.out.println("Class not found");
			e1.printStackTrace();
		} catch (SQLException e1) {
			System.out.println("SQL exception, fix somewhere");
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IOException was thrown");
			e.printStackTrace();
		}

		try {
			conn.close();
			System.out.println("Closed connection");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

