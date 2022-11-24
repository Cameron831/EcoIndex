package application.controller;

import application.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ResetPasswordController implements Navigation {

	@FXML
	Label securityQuestionDisplay;
	@FXML
	TextField attemptAnswer;
	@FXML
	PasswordField newPasswordField;
	@FXML
	Label usernameDisplay;

	// user to fetch security question from
	private User tempUser = commonOb.getTempUser();

	public void initialize() {
		usernameDisplay.setText("User: " + tempUser.getUsername());
		securityQuestionDisplay.setText(tempUser.getSecurityQuestion());
	}

	@FXML
	public void goToLogin() {
		goToPage("view/Login.fxml");
	}

	@FXML
	public void changePasswordPressed() {
		VerificationHandler vh = new VerificationHandler();
		if (vh.verifyQuestionStatus(tempUser, attemptAnswer, newPasswordField)) {
			// verified tempUser, setting as the current user
			commonOb.setCurrentUser(tempUser);
			commonOb.setTempUser(null);
			goToPage("view/Login.fxml");
		} 
		else
			System.out.println("answer incorrect");
	}
}
