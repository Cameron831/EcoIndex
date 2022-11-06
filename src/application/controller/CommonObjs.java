package application.controller;

import application.model.User;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

// singleton class
public class CommonObjs {
	// singleton essentials
	private static CommonObjs singleInstance = new CommonObjs();
	
	private CommonObjs() {};
	
	public static CommonObjs getSingle() {
		return singleInstance;
	}
	
	// share the same stage
	private Stage rootStage;
	public void setRootStage(Stage primaryStage) {
		this.rootStage = primaryStage;
	}
	public Stage getRootStage() {
		return rootStage;
	}
	
	
	// share the current user
	private User currentUser = null;
	public User getCurrentUser() {
		return currentUser;
	}
	void setCurrentUser(User user) {
		this.currentUser = user;
	}
	
	
	// temporarily communicate the user to fetch security question
	private User tempUser_ResetPassword;
	User getTempUser() {
		return tempUser_ResetPassword;
	}
	void setTempUser(User user) {
		this.tempUser_ResetPassword = user;
	}
	
	
	// share pane for popups
	private Pane topCoursePane;
	Pane getTopCoursePane() {
		return topCoursePane;
	}
	void setTopCoursePane(Pane topPane) {
		this.topCoursePane = topPane;
	}

	
	// share vbox pane for showing courses
	private Pane courseDisplay;
	Pane getCourseDisplayPane() {
		return courseDisplay;
	}
	void setCourseDisplayPane(Pane p) {
		this.courseDisplay = p;
	}
	
	
	// share container for courses vbox pane
	private ScrollPane scrollDisplayCoursePane;
	ScrollPane getScrollDisplayCoursePane() {
		return scrollDisplayCoursePane;
	}
	void setScrollDisplayCoursePane(ScrollPane p) {
		this.scrollDisplayCoursePane = p;
	}
	
	
	// share course for editing
	private Course temporaryCourse;

	public Course getTemporaryCourse() {
		return temporaryCourse;
	}

	void setTemporaryCourse(Course temporaryCourseName) {
		this.temporaryCourse = temporaryCourseName;
	}
}
