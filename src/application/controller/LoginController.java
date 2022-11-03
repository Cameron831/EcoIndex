package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

public class LoginController implements Navigation {

	@FXML PasswordField passwordField;

	@FXML
	public void goToSignUp() {
		goToPage("view/Signup.fxml");
	}

	@FXML public void goToResetPassword() {
		goToPage("view/ResetPassword.fxml");
	}

	@FXML public void loginPressed() {
		PasswordHandler ph = new PasswordHandler();
		if (ph.verificationStatus(passwordField)) {
			commonOb.setCurrentUser(ph.getUser());
			goToPage("view/Courses.fxml");
		}
		else
			System.out.println("login failed");
	}
}
