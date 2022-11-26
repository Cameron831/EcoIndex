package application.model;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String username, password, securityQuestion, securityQuestionAnswer;
	private List<Course> courses = new ArrayList<>();
	private int ID;

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
	public void deleteCourse(Course course) {
		courses.remove(course);
		course.deleteCourse();
	}

	void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Course addCourse(Course c) {
		Course fin = c.addCourse(this);
		courses.add(fin);
		return fin;
	}

	public void initializeCourses() {
		CourseSQL db = CourseSQL.getSingle();
		courses = db.getAllCoursesFromUser(this);
	}

	public void deleteUser() {
		for (Course c : courses)
			c.deleteCourse();

		UserSQL db = UserSQL.getSingle();
		db.deleteUser(this);

	}

	public void updateUser(String un, String pw, String sq, String sqA) {
		this.username = un;
		this.password = pw;
		this.securityQuestion = sq;
		this.securityQuestionAnswer = sqA;
		UserSQL.getSingle().updateUser(this);
	}
}
