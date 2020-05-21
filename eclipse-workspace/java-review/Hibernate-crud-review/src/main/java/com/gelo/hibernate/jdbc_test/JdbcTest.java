package com.gelo.hibernate.jdbc_test;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcTest {

	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false";
		String user = "root";
		String password = "Password_1221"; 
		
		try {
			System.out.println("Connecting to database: " + url);
			
			Connection con = DriverManager.getConnection(url, user, password);
			
			System.out.println("Connection successful!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
