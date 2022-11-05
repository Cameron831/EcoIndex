package application.controller;

import application.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ResetPasswordController implements Navigation {
	
	// TODO grab security question and change database accordingly
	

	@FXML Label securityQuestionDisplay;
	@FXML TextField attemptAnswer;
	@FXML PasswordField newPasswordField;
	@FXML Label usernameDisplay;
	
	private User tempUser = commonOb.getTempUser();
	
	public void initialize() {
		usernameDisplay.setText("User: " + tempUser.getUsername());
		securityQuestionDisplay.setText(tempUser.getSecurityQuestion());
	}

	@FXML public void goToLogin() {
		goToPage("view/Login.fxml");
	}

	@FXML public void changePasswordPressed() {
		AccountHandler ah = new AccountHandler();
		if (ah.verifyQuestionStatus(tempUser, attemptAnswer, newPasswordField)) {
			commonOb.setCurrentUser(tempUser);
			commonOb.setTempUser(null);
			goToPage("view/Login.fxml");
		}
		else
			System.out.println("answer incorrect");
	}
}
