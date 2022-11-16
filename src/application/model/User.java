package application.model;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String username, password, securityQuestion, securityQuestionAnswer;
	private List<Course> courses = new ArrayList<>();

	// for database use if user has saved courses
//	public User(String un, String pw, String sq, String sqA, String convertCourse) {
//		this(un, pw, sq, sqA);
//
//		// initialize all courses saved on database
//		for (String i : convertCourse.split(","))
//			this.courses.add(new Course(i));
//	}

	// no saved courses
	public User(String un, String pw, String sq, String sqA) {
		this.username = un;
		this.password = pw;
		this.securityQuestion = sq;
		this.securityQuestionAnswer = sqA;
	}

	public String getUsername() {
		return username;
	}

	void setUsername(String username) {
		this.username = username;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getPassword() {
		return password;
	}

	void setPassword(String password) {
		this.password = password;
	}

	public String getSecurityQuestionAnswer() {
		return securityQuestionAnswer;
	}

	void setSecurityQuestionAnswer(String securityQuestionAnswer) {
		this.securityQuestionAnswer = securityQuestionAnswer;
	}

	public List<Course> getCourses() {
		return courses;
	}

	// remove a specific course
	public void removeCourse(String courseName) {
		// search for specific course, then remove it once found
		int counter = 0;
		while (!courseName.equals(courses.get(counter).getName()))
			counter++;
		courses.remove(counter);
		updateDB();
	}
	
	public Course searchCourse(String courseName) {
		for (Course c : courses)
			if (courseName.equals(c.getName()))
				return c;
		return null;
	}

	void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	// add a defined course
	public Course addCourse(Course c) {
		courses.add(c);
//		updateDB();
		CourseSQL db = CourseSQL.getSingle();
		db.addCourse(this, c);
		return c;
	}

	// add a new course from a string
	public Course addCourse(String c, String d) {
		Course i = new Course(c,d);
		return addCourse(i);
	}
	
	public void initializeCourses() {
		CourseSQL db = CourseSQL.getSingle();
		courses = db.getAllCoursesFromUser(this);
	}

	// update the database
	public void updateDB() {
//		CourseSQL db = CourseSQL.getSingle();
//		db.overwriteDB(this);
	}
}
