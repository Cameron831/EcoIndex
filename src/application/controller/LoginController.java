package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginController implements Navigation {

	@FXML
	PasswordField passwordField;
	@FXML
	TextField usernameField;
	private VerificationHandler vh = new VerificationHandler();
	@FXML
	AnchorPane topPane;

	@FXML
	public void goToSignUp() {
		goToPage("view/Signup.fxml");
	}

	@FXML
	public void goToResetPassword() {
		// fetch user using username field in login page
		String ret = usernameField.getText();
		if (ret.isEmpty()) {
			resetPasswordPrompt();
			return;
		}
		vh.getUser(ret);

		if (vh.getUser() == null) {
			resetPasswordPrompt();
			return;
		}

		commonOb.setTempUser(vh.getUser());
		goToPage("view/ResetPassword.fxml");
	}
	
	private void resetPasswordPrompt() {
		new Alert("User does not exist or is empty", topPane);
	}

	@FXML
	public void loginPressed() {

		if (vh.verificationStatus(passwordField, usernameField.getText())) {
			// login successful
			commonOb.setCurrentUser(vh.getUser());
			commonOb.getCurrentUser().initializeCourses();
			goToPage("view/Courses.fxml");
		} else
			new Alert("Login failed. Please try again", topPane);
	}
}
