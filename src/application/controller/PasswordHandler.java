package application.controller;

import application.model.DBLogin;
import application.model.User;
import javafx.scene.control.PasswordField;

public class PasswordHandler {
	private DBLogin checkDatabase = DBLogin.getSingle();
	private User user;
	private void encrypt() {
		// encryption/decryption later
	}
	
	private String decrypt() {
		user = checkDatabase.getUser();
		
		// encryption/decryption later
		return user.getPassword();
	}
	
	User getUser() {
		return user;
	}
	
	private boolean verification(PasswordField pw) {
		// TODO
		String attemptPW = pw.getText();
		return decrypt().equals(attemptPW);
	}
	
	public boolean verificationStatus(PasswordField pw) {
		return verification(pw);
	}
}
