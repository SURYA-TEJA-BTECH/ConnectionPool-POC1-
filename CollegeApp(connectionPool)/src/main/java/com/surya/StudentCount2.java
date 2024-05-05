package com.surya;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

class StudentCount2 {

	private static final String STUDENT_COUNT_QUERY = "select count(*) from student;";

	public static void main(String[] args) {

		try {
			InputStream fs = new FileInputStream("src/main/java/com/surya/datasource.properties");

			Properties properties = new Properties();

			properties.load(fs);

			try (Connection con = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.username"),
					properties.getProperty("jdbc.password"));
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(STUDENT_COUNT_QUERY)) {
				rs.next();

				int count = rs.getInt(1);

				System.out.println(count == 0 ? "no students are there " : " total availble students are " + count);
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
