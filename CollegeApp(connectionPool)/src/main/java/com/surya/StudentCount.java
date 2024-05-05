package com.surya;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class StudentCount {

	private static final String STUDENT_COUNT_QUERY = "select count(*) from student;";
	private static final String URL = "jdbc:mysql://localhost:3305/college";
	private static final String PASSWORD = "root";
	private static final String USERNAME = "root";

	public static void main(String[] args) {

		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(STUDENT_COUNT_QUERY)) {
			rs.next();

			int count = rs.getInt(1);

			System.out.println(count == 0 ? "no students are there " : " total availble students are " + count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
