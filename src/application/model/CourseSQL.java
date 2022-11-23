package application.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			String query = "SELECT * FROM tbCourses WHERE userID = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, u.getID());
			ResultSet courseInfo = preparedStatement.executeQuery();

			while (courseInfo.next())
				courses.add(new Course(courseInfo.getString("courseName"), courseInfo.getString("courseDescription"),
						courseInfo.getString("cardNums"), courseInfo.getInt("ID")));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courses;
	}

	public Course addCourse(User u, Course c) {
		try {
			PreparedStatement preparedStatement;
			String query = "INSERT INTO tbCourses (userID,courseName,courseDescription,cardNums) VALUES (?,?,?,?)";
			preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, u.getID());
			preparedStatement.setString(2, c.getName());
			preparedStatement.setString(3, c.getDescription());
			preparedStatement.setInt(4, c.getNumCards());
			preparedStatement.executeUpdate();

			return new Course(c.getName(), c.getDescription(), c.getNumCards(),
					preparedStatement.getGeneratedKeys().getInt(1));

//			return preparedStatement.getGeneratedKeys().getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			System.out.println("name not unique");
			return null;
		}

	}

	// TODO need to be able to edit course
	public void updateCourse(Course c) {
		try {
			PreparedStatement preparedStatement;
			String query = "UPDATE tbCourses SET courseName = ?, courseDescription = ? WHERE ID = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, c.getName());
			preparedStatement.setString(2, c.getDescription());
			preparedStatement.setInt(3, c.getID());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// TODO need to add how to delete course
}
