package application.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqliteConnection {
	// !!!!IMPORTANT (driver)
	
	public static Connection Connector(){
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:ecoIndex.db");
			return conn;
		} catch (Exception e) {
			return null;
		}
	}
}
