package application.controller;

import application.model.User;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AccountHandler {
	private User user;
	private CryptionHandler ch = new CryptionHandler();

	AccountHandler(User u) {
		this.user = u;
	}

	String retrievePassword() {
		return ch.decrypt(user.getPassword());
	}

	String retrieveAnswer() {
		return ch.decrypt(user.getSecurityQuestionAnswer());
	}

	void modifyAccount(User u, String un, PasswordField pw, String sq, TextField sqA) {
		String encryptedPW = ch.encrypt(pw.getText());
		String encryptedSQA = ch.encrypt(sqA.getText());

		u.updateUser(un, encryptedPW, sq, encryptedSQA);
	}
}
