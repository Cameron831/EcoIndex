package application.model;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String username, password, securityQuestion, securityQuestionAnswer;
	private List<Course> courses = new ArrayList<>();
	private int ID;

	// for database use if user has saved courses
//	public User(String un, String pw, String sq, String sqA, String convertCourse) {
//		this(un, pw, sq, sqA);
//
//		// initialize all courses saved on database
//		for (String i : convertCourse.split(","))
//			this.courses.add(new Course(i));
//	}

	// no saved courses
	public User(String un, String pw, String sq, String sqA, int ID) {
		this.username = un;
		this.password = pw;
		this.securityQuestion = sq;
		this.securityQuestionAnswer = sqA;
		this.ID = ID;
	}

	int getID() {
		return ID;
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
	public void removeCourse(Course course) {
		// search for specific course, then remove it once found
//		int counter = 0;
//		while (!courses.get(counter).equals(courseName))
//			counter++;
//		courses.remove(counter);
		courses.remove(course);
		updateDB();
	}

	public Course searchCourse(Course course) {
		for (Course c : courses)
			if (c.equals(course))
				return c;
		return null;
	}

	void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	// add a defined course
	public Course addCourse(Course c) {
//		CourseSQL db = CourseSQL.getSingle();
		Course fin = c.addCourse(this);
				
//				db.addCourse(this, c);
		courses.add(fin);
		return fin;
	}

	// add a new course from a string
	public Course addCourse(String c, String d) {
		Course i = new Course(c, d);
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
