package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Navigation {

	@FXML PasswordField passwordField;
	@FXML TextField usernameField;

	@FXML
	public void goToSignUp() {
		goToPage("view/Signup.fxml");
	}

	@FXML public void goToResetPassword() {
		goToPage("view/ResetPassword.fxml");
	}

	
	@FXML public void loginPressed() {
		PasswordHandler ph = new PasswordHandler();
		if (ph.verificationStatus(passwordField, usernameField.getText())) {
			commonOb.setCurrentUser(ph.getUser());
			goToPage("view/Courses.fxml");
		}
		else
			System.out.println("login failed");
	}
}
