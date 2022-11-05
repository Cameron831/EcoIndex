package application.controller;

import application.model.DBLogin;
import application.model.User;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AccountHandler {
	private DBLogin checkDatabase = DBLogin.getSingle();
	private User user;

	private String encrypt(String toEncrypt) {
		// encryption/decryption later
		return toEncrypt;
	}

	private String decrypt(String toDecrypt) {
		// encryption/decryption later
		return toDecrypt;
	}

	public String getEncrypt(String toEncrypt) {
		return encrypt(toEncrypt);
	}

	User getUser() {
		return user;
	}

	User getUser(String un) {
		getUserDB(un);
		return user;
	}

	private User getUserDB(String un) {
		user = checkDatabase.getUser(un);
		return user;
	}

	private boolean verification(PasswordField pw, String un) {
		getUserDB(un);
		String attemptPW = pw.getText();
		return attemptPW.equals(decrypt(user.getPassword()));
	}

	public boolean verificationStatus(PasswordField pw, String un) {
		return verification(pw, un);
	}

	public boolean verifyQuestionStatus(User tempUser, TextField attemptAnswer, PasswordField newPasswordField) {
		user = tempUser;
		return verifyQuestion(attemptAnswer, newPasswordField);
	}

	private boolean verifyQuestion(TextField attemptAnswer, PasswordField newPasswordField) {
		String correctAns = decrypt(user.getSecurityQuestionAnswer());
//		System.out.println(user.getSecurityQuestionAnswer())
		if (!correctAns.equals(attemptAnswer.getText()))
			return false;
		checkDatabase.updateUser(user, encrypt(newPasswordField.getText()));
		return true;
	}

}
