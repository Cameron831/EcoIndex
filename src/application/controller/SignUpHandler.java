package application.controller;

import application.model.DBLogin;
import application.model.User;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpHandler {

	private DBLogin checkDatabase = DBLogin.getSingle();
	private PasswordHandler ph = new PasswordHandler();

	User newUser(String un, PasswordField pw, String sq, TextField sqA) {
		String testPW = pw.getText();
		String testSQA = sqA.getText();
		if (un.isEmpty() || testPW.isEmpty() || sq.isEmpty() || testSQA.isEmpty())
			return null;
		return checkDatabase.createNewUser(un, ph.getEncrypt(testPW), sq, ph.getEncrypt(testSQA));
	}
}
