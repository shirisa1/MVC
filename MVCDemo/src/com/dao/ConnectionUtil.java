package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {
	public static final String URL="jdbc:oracle:thin:@oracle.cccswnvlzm9u.us-east-2.rds.amazonaws.com:1521:orcl";
	public static final String DRIVER="oracle.jdbc.driver.OracleDriver";
	public static final String USERNAME="admin";
	public static final String PASSWORD="12345678";
//	public static Connection connection = null;
	public static Connection getConnection() {
//		if(connection!= null)
//			return connection;
//		else		
		Connection connection = null;
		try {
			Class.forName(DRIVER);
			System.out.println("load driver");
			connection = DriverManager.getConnection(URL);
			System.out.println("connection sucess");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return connection;
		
		
	}
	
	

}
