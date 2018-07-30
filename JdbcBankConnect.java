package com.revature.jdbcbankconnect;

import java.io.FileInputStream;
import java.sql.Connection;
import java.io.InputStream;
import java.sql.DriverManager;
import java.util.Properties;

public class JdbcBankConnect {
	private JdbcBankConnect(){
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = JdbcBankConnect.getConnection();
		 System.out.println(conn);
	}
	
	 public static Connection getConnection() {
		 InputStream in = null;
		 
		 
		 try {
			 	Properties props = new Properties();
			 	in = new FileInputStream("src/main/resources/jdbcbankconnect.properties");
			 	props.load(in);
			 
			 
			 	Class.forName("oracle.jdbc.driver.OracleDriver");
			 
			 	Connection conn = null;

			 	String endpoint = props.getProperty("jdbc.url");
			 	String username = props.getProperty("jdbc.username");
			 	String password = props.getProperty("jdbc.password");

			 	conn = DriverManager.getConnection(endpoint, username, password);

			 	return conn;
		
			 
		 }catch(Exception e){
			 e.getMessage();
		 }
		 return null;
		 
	 }

}
