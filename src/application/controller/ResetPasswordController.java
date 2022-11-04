package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ResetPasswordController implements Navigation {
	
	// TODO grab security question and change database accordingly
	

	@FXML Label securityQuestionDisplay;

	@FXML public void goToLogin() {
		goToPage("view/Login.fxml");
	}

	@FXML public void changePasswordPressed() {
		PasswordHandler ph = new PasswordHandler();
	}
}
