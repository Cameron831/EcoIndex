package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Navigation {

	@FXML PasswordField passwordField;
	@FXML TextField usernameField;
	private VerificationHandler vh = new VerificationHandler();

	@FXML
	public void goToSignUp() {
		goToPage("view/Signup.fxml");
	}

	@FXML public void goToResetPassword() {
		// fetch user using username field in login page
		String ret = usernameField.getText();
		if (ret.isEmpty()) {
			System.out.println("enter a username");
			return;
		}
		vh.getUser(ret);
		
		if (vh.getUser() == null) return;
		
		commonOb.setTempUser(vh.getUser());
		goToPage("view/ResetPassword.fxml");
	}

	
	@FXML public void loginPressed() {
		if (vh.verificationStatus(passwordField, usernameField.getText())) {
			// login successful
			commonOb.setCurrentUser(vh.getUser());
			commonOb.getCurrentUser().initializeCourses();
			goToPage("view/Courses.fxml");
		}
		else
			System.out.println("login failed");
	}
}
