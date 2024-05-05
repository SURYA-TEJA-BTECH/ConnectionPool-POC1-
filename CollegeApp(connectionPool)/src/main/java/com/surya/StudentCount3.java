package com.surya;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

class StudentCount3 {

	private static final String STUDENT_COUNT_QUERY = "select count(*) from student;";

	public static void main(String[] args) {

		try {
			InputStream fs = new FileInputStream("src/main/java/com/surya/datasource.properties");

			Properties properties = new Properties();

			properties.load(fs);

			DataSource ds = createDsObject(properties);

			try (Connection con = ds.getConnection();
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

	public static DataSource createDsObject(Properties properties) {
		HikariDataSource config = new HikariDataSource();

		config.setJdbcUrl(properties.getProperty("jdbc.url"));
		config.setPassword(properties.getProperty("jdbc.username"));
		config.setUsername(properties.getProperty("jdbc.password"));
		config.setMinimumIdle(10);
		config.setMaximumPoolSize(20);
		
		return config;

	}

}
