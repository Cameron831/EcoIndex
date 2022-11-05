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

	@FXML
	public void goToCourses() {
		goToPage("view/Courses.fxml");
	}
}
