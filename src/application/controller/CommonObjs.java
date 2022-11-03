package application.controller;

import application.model.User;
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
	private User currentUser;
	public User getCurrentUser() {
		return currentUser;
	}
	void setCurrentUser(User user) {
		this.currentUser = user;
	}
	
}
