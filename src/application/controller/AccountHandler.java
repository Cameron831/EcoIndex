package application.controller;

import application.model.User;
import application.model.UserSQL;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AccountHandler {
	
	private PassUtil passUtil = new PassUtil();

	private UserSQL checkDatabase = UserSQL.getSingle();
	private User user;

	private String encrypt(String toEncrypt) {
		return passUtil.encrypt(toEncrypt);
//		return toEncrypt;
	}

	private String decrypt(String toDecrypt) {
		return passUtil.decrypt(toDecrypt);
//		return toDecrypt;
	}

	public String getEncrypt(String toEncrypt) {
		return encrypt(toEncrypt);
	}

	// user has been saved into the private variable. we read from it
	User getUser() {
		return user;
	}

	User getUser(String un) {
		getUserDB(un);
		return user;
	}

	// search and fetch user from the database
	private User getUserDB(String un) {
		user = checkDatabase.getUser(un);
		return user;
	}

	private boolean verification(PasswordField pw, String un) {
		getUserDB(un);
		if (user == null) return false;
		
		String attemptPW = pw.getText();
		return attemptPW.equals(decrypt(user.getPassword()));
	}

	// public method for logging in
	public boolean verificationStatus(PasswordField pw, String un) {
		return verification(pw, un);
	}

	// public method for resetting password
	public boolean verifyQuestionStatus(User tempUser, TextField attemptAnswer, PasswordField newPasswordField) {
		user = tempUser;
		return verifyQuestion(attemptAnswer, newPasswordField);
	}

	private boolean verifyQuestion(TextField attemptAnswer, PasswordField newPasswordField) {
		String correctAns = decrypt(user.getSecurityQuestionAnswer());
		if (!correctAns.equals(attemptAnswer.getText()))
			return false;
		checkDatabase.resetPassword(user, encrypt(newPasswordField.getText()));
		return true;
	}

}
