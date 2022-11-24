package application.controller;

import application.model.User;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AccountHandler {
	private CryptionHandler ch = new CryptionHandler();

	void modifyAccount(User u, String un, PasswordField pw, String sq, TextField sqA) {
		String encryptedPW = ch.encrypt(pw.getText());
		String encryptedSQA = ch.encrypt(sqA.getText());

		u.updateUser(un, encryptedPW, sq, encryptedSQA);

	}
}
