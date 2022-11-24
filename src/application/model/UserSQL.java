package application.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserSQL implements DBHandler {
//	private Connection connection;

	// singleton essentials
	private static UserSQL singleInstance = new UserSQL();

	private UserSQL() {
//		connection = SqliteConnection.Connector();
//		if (connection == null)
//			System.exit(1);
	};

	public static UserSQL getSingle() {
		return singleInstance;
	}

	public boolean isDbConnected() {
		try {
			return !connection.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("broke");
		}
	}

	// constants to reference sqlite column names
	private final static String username = "userName";
	private final static String password = "passwd";
	private final static String securityQuestion = "securityQuestion";
	private final static String securityAnswer = "securityQuestionAnswer";
	private final static String ID = "ID";

	// general purpose method to overwrite/update info
	private void overwriteColumn(String user, String column, String newData) throws SQLException {
		PreparedStatement preparedStatement;
//		String query = "UPDATE tbUsers set " + column + " = ? WHERE " + username + " = ?";
		String query = "UPDATE tbUsers set " + column + " = ? WHERE userName = ?";
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, newData);
		preparedStatement.setString(2, user);
		preparedStatement.executeUpdate();
	}

	public boolean resetPassword(User u, String pw) {
		u.setPassword(pw);
		try {
			overwriteColumn(u.getUsername(), password, pw);
			System.out.println("password successfully reset");
			return true;
		} catch (SQLException e) {
			System.out.println("password could not be reset");
			return false;
		}
	}

	public boolean resetUsername(User u, String un) {
		try {
			overwriteColumn(u.getUsername(), username, un);
			System.out.println("username changed");
			u.setUsername(un);
			return false;
		} catch (SQLException e) {
			System.out.println("username could not be changed");
			return false;
		}
	}

	private ResultSet searchUsername(String un) throws SQLException {
		PreparedStatement preparedStatement;
//		String query = "SELECT * FROM tbusers WHERE " + username + " = ?";
		String query = "SELECT * FROM tbusers WHERE userName = ?";
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, un);
		ResultSet test = preparedStatement.executeQuery();
		if (!test.next())
			throw new SQLException("user not found");
		return test;
	}

	private User compactToUser(String un) throws SQLException {
		ResultSet user = searchUsername(un);
		return new User(user.getString(username), user.getString(password), user.getString(securityQuestion),
				user.getString(securityAnswer), user.getInt(ID));
	}

	// publicly called by outside
	public User getUser(String un) {
		try {
			return compactToUser(un);
		} catch (SQLException e) {
			System.out.println("could not find user");
			return null;
		}
	}

	// publicly called by outside
	public User createNewUser(String un, String pw, String sq, String sqA) {
		try {
			return writeNewUser(un, pw, sq, sqA);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("was not able to create user. make sure username is unique");
			return null;
		}
	}

	private User writeNewUser(String un, String pw, String sq, String sqA) throws SQLException {
		PreparedStatement preparedStatement;
		String query = "INSERT INTO tbUsers(userName,passwd,securityQuestion,securityQuestionAnswer) VALUES(?,?,?,?)";
		preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, un);
		preparedStatement.setString(2, pw);
		preparedStatement.setString(3, sq);
		preparedStatement.setString(4, sqA);
		if (preparedStatement.executeUpdate() <= 0)
			throw new SQLException("could not add user to database");

		ResultSet justInserted = preparedStatement.getGeneratedKeys();
		return new User(un, pw, sq, sqA, justInserted.getInt(1));
	}

	public void deleteUser(User u) {
		try {
			PreparedStatement preparedStatement;
			String query = "DELETE FROM tbUsers WHERE ID = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, u.getID());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateUser(User u) {
		try {
			PreparedStatement preparedStatement;
			String query = "UPDATE tbUsers SET userName = ?, passwd = ?, securityQuestion = ?, securityQuestionAnswer = ? WHERE ID = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, u.getUsername());
			preparedStatement.setString(2, u.getPassword());
			preparedStatement.setString(3, u.getSecurityQuestion());
			preparedStatement.setString(4, u.getSecurityQuestionAnswer());
			preparedStatement.setInt(5, u.getID());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
