package application.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseSQL implements DBHandler {
//	private Connection connection;

	// singleton essentials
	private static CourseSQL singleInstance = new CourseSQL();

	private CourseSQL() {
//		connection = SqliteConnection.Connector();
//		if (connection == null)
//			System.exit(1);
	};

	public static CourseSQL getSingle() {
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

	public List<Course> getAllCoursesFromUser(User u) {
		List<Course> courses = new ArrayList<>();
		try {
			PreparedStatement preparedStatement;
			String query = "SELECT courseName, courseDescription, cardNums FROM tbCourses WHERE userName = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, u.getUsername());
			ResultSet courseInfo = preparedStatement.executeQuery();

			while (courseInfo.next())
				courses.add(new Course(courseInfo.getString("courseName"), courseInfo.getString("courseDescription"),
						courseInfo.getString("cardNums")));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courses;
	}

	public void addCourse(User u, Course c) {
		try {
			PreparedStatement preparedStatement;
			String query = "INSERT INTO tbCourses (userName,courseName,courseDescription,cardNums) VALUES (?,?,?,?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, u.getUsername());
			preparedStatement.setString(2, c.getName());
			preparedStatement.setString(3, c.getDescription());
			preparedStatement.setInt(4, c.getNumCards());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			System.out.println("name not unique");
		}

	}

	// TODO: maybe dont need
	private ResultSet searchCourse(User u, Course c) throws SQLException {
		PreparedStatement ps;
		String query = "SELECT * FROM tbCourses WHERE userName = ? AND courseName = ?";
		ps = connection.prepareStatement(query);
		ps.setString(1, u.getUsername());
		ps.setString(2, c.getName());
		ResultSet test = ps.executeQuery();
		if (!test.next())
			throw new SQLException("course not found");
		return test;
	}

	// general purpose method to overwrite/update info
	private void overwriteColumn(String user, String column, String newData) throws SQLException {
		PreparedStatement preparedStatement;
//		String query = "UPDATE tbUsers set " + column + " = ? WHERE " + username + " = ?";
		String query = "UPDATE tbCourses set " + column + " = ? WHERE userName = ? AND";
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, newData);
		preparedStatement.setString(2, user);
		preparedStatement.executeUpdate();
	}

	// TODO need to be able to edit course
	public void updateCourse(User u, Course c) {
		try {
			PreparedStatement preparedStatement;
//			String query = "UPDATE tbCourses set  = ? WHERE userName = ? AND";
//			preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setString(1, newData);
//			preparedStatement.setString(2, user);
//			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	// TODO need to add how to delete course

	public static void main(String[] args) {
		User test = new User("test", "pw", "q", "a");
//		CourseSQL x = new CourseSQL();
		List<Course> t = getSingle().getAllCoursesFromUser(test);
		for (Course c : t)
			System.out.println(c);
		System.out.println(getSingle().isDbConnected());

		getSingle().addCourse(test, new Course("abct", "desc"));
		System.out.println("work");

	}
}
