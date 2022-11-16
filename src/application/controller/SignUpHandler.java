package application.controller;

import application.model.User;
import application.model.UserSQL;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

class SignUpHandler {

	private UserSQL checkDatabase = UserSQL.getSingle();
	private AccountHandler ph = new AccountHandler();

	User newUser(String un, PasswordField pw, String sq, TextField sqA) {
		String testPW = pw.getText();
		String testSQA = sqA.getText();
		
		// edge case check
		if (un.isEmpty() || testPW.isEmpty() || sq.isEmpty() || testSQA.isEmpty())
			return null;
		return checkDatabase.createNewUser(un, ph.getEncrypt(testPW), sq, ph.getEncrypt(testSQA));
	}
}
