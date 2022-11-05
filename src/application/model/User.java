package application.model;

import java.util.ArrayList;
import java.util.List;

import application.controller.Course;

public class User {
	private String username, password, securityQuestion, securityQuestionAnswer;
	private List<Course> courses = new ArrayList<>();

	public User(String un, String pw, String sq, String sqA, String convertCourse) {
		this.username = un;
		this.password = pw;
		this.securityQuestion = sq;
		this.securityQuestionAnswer = sqA;

		for (String i : convertCourse.split(","))
			this.courses.add(new Course(i));
	}

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

	void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Course addCourse(Course c) {
		courses.add(c);
		updateDB();
		return c;
	}

	public Course addCourse(String c) {
		Course i = new Course(c);
		courses.add(i);
		updateDB();
		return i;
	}
	
	private void updateDB() {
		DBLogin db = DBLogin.getSingle();
		db.overwriteDB(this);
	}
}
