package application.controller;

import javafx.fxml.FXML;

public class WelcomeController implements Navigation {
	@FXML
	public void goToLogin() {
		goToPage("view/Login.fxml");
	}

	@FXML
	public void goToSignUp() {
		goToPage("view/Signup.fxml");
	}
	
	// the no login/guest button. supposed to only provide limited functionality for app
	@FXML
	public void goToCourses() {
		goToPage("view/Courses.fxml");
	}
}
