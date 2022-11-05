package application.controller;

import application.model.User;
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
//	private User currentUser = new User("Guest","","","");
	private User currentUser = null;
	public User getCurrentUser() {
		return currentUser;
	}
	void setCurrentUser(User user) {
		this.currentUser = user;
	}
	
	
	// share top level course pane
	private Pane coursePane;

	public Pane getCoursePane() {
		return coursePane;
	}

	public void setCoursePane(Pane coursePane) {
		this.coursePane = coursePane;
	}
	
	
	private User tempUser_ResetPassword;
	User getTempUser() {
		return tempUser_ResetPassword;
	}
	void setTempUser(User user) {
		this.tempUser_ResetPassword = user;
	}
	
	
	
	
}
