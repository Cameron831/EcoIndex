package application.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseSQL implements DBHandler {
	// singleton essentials
	private static CourseSQL singleInstance = new CourseSQL();

	private CourseSQL() {
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
						courseInfo.getInt("cardNums"), courseInfo.getInt("learnedNums"), courseInfo.getInt("ID")));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courses;
	}

	public Course addCourse(User u, Course c) {
		try {
			PreparedStatement preparedStatement;
			String query = "INSERT INTO tbCourses (userID,courseName,courseDescription,cardNums,learnedNums) VALUES (?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, u.getID());
			preparedStatement.setString(2, c.getName());
			preparedStatement.setString(3, c.getDescription());
			preparedStatement.setInt(4, c.getNumCards());
			preparedStatement.setInt(5, c.getLearnedTotal());
			preparedStatement.executeUpdate();

			return new Course(c.getName(), c.getDescription(), c.getNumCards(), c.getLearnedTotal(),
					preparedStatement.getGeneratedKeys().getInt(1));

		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("name not unique");
			return null;
		}

	}

	public boolean updateCourse(Course c) {
		try {
			PreparedStatement preparedStatement;
			String query = "UPDATE tbCourses SET courseName = ?, courseDescription = ?, cardNums = ?, learnedNums = ? WHERE ID = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, c.getName());
			preparedStatement.setString(2, c.getDescription());
			preparedStatement.setInt(3, c.getNumCards());
			preparedStatement.setInt(4, c.getLearnedTotal());
			preparedStatement.setInt(5, c.getID());
			preparedStatement.executeUpdate();
			return true;

		} catch (SQLException e) {
			return false;
		}

	}

	public void deleteCourse(Course c) {
		try {
			PreparedStatement preparedStatement;
			String query = "DELETE FROM tbCourses WHERE ID = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, c.getID());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
