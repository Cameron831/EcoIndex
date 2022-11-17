package application.model;

import java.sql.Connection;

public interface DBHandler {
	// place all common methods among databases
	public final static Connection connection = SqliteConnection.Connector();
}
