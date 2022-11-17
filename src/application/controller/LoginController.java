package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Navigation {

	@FXML PasswordField passwordField;
	@FXML TextField usernameField;
	private AccountHandler ah = new AccountHandler();

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
		ah.getUser(ret);
		
		if (ah.getUser() == null) return;
		
		commonOb.setTempUser(ah.getUser());
		goToPage("view/ResetPassword.fxml");
	}

	
	@FXML public void loginPressed() {
		if (ah.verificationStatus(passwordField, usernameField.getText())) {
			// login successful
			commonOb.setCurrentUser(ah.getUser());
			commonOb.getCurrentUser().initializeCourses();
			goToPage("view/Courses.fxml");
		}
		else
			System.out.println("login failed");
	}
}
