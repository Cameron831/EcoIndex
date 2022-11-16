package application.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseSQL implements DBHandler {
	private Connection connection;

	// singleton essentials
	private static CourseSQL singleInstance = new CourseSQL();

	private CourseSQL() {
		connection = SqliteConnection.Connector();
		if (connection == null)
			System.exit(1);
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
	
	
	public List<Course> getAllCoursesFromUser(User u){
		List<Course> courses = new ArrayList<>();
		try {
			PreparedStatement preparedStatement;
			String query = "SELECT courseName, courseDescription, cardNums FROM tbCourses WHERE userName = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, u.getUsername());
			ResultSet courseInfo = preparedStatement.executeQuery();
			
//			System.out.println(courseInfo);
			
			while (courseInfo.next())
				courses.add(new Course(courseInfo.getString("courseName"),
						courseInfo.getString("courseDescription"),
						courseInfo.getString("cardNums")
						));
			
			courseInfo.close();
			preparedStatement.close();
//			connection.close();
//			connection.o
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courses;
	}
	
	public void addCourse(User u, Course c) {
		try {
			PreparedStatement preparedStatement;
//			PreparedStatement ps;
			String query = "INSERT INTO tbCourses (userName,courseName,courseDescription,cardNums) VALUES (?,?,?,?)";
//			String query = "INSERT INTO tbUsers(userName,passwd,securityQuestion,securityQuestionAnswer) VALUES(?,?,?,?)";
//			ps = connection.prepareStatement(query);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, u.getUsername());
			preparedStatement.setString(2, c.getName());
			preparedStatement.setString(3, c.getDescription());
			preparedStatement.setInt(4, c.getNumCards());
			preparedStatement.executeUpdate();
//			ps.setString(1, u.getUsername());
//			ps.setString(2, c.getName());
//			ps.setString(3, c.getDescription());
//			ps.setInt(4, 0);
//			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			System.out.println("name not unique");
		}
		
	}
	
	public static void main(String[] args) {
		User test = new User("test", "pw","q","a");
//		CourseSQL x = new CourseSQL();
		List<Course> t = getSingle().getAllCoursesFromUser(test);
		for (Course c : t)
			System.out.println(c);
		System.out.println(getSingle().isDbConnected());
		
		getSingle().addCourse(test, new Course("abct","desc"));
		System.out.println("work");
		
	}
}
