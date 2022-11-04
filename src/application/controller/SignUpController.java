package application.controller;

import application.model.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController implements Navigation {

	@FXML
	MenuButton securityQuestionSelect;
	@FXML
	TextField usernameField;
	@FXML
	PasswordField passwordField;
	@FXML
	TextField securityAnswerField;

	private MenuItem selected;

	public void initialize() {

		// makes menu display current choice. we can create as many questions as we want
		// without manually creating and setting their action methods
		ObservableList<MenuItem> questions = securityQuestionSelect.getItems();
		for (MenuItem q : questions)
			q.setOnAction(e -> {
				securityQuestionSelect.setText(q.getText());
				selected = q;
			});

	}

	// navigating back to login page
	@FXML
	public void goToLogin() {
		goToPage("view/Login.fxml");
	}

	@FXML
	public void signUpButtonPressed() {
		if (selected == null) {
			System.out.println("pick a security question");
			return;
		}
		
		SignUpHandler handler = new SignUpHandler();
		User testCreate = handler.newUser(usernameField.getText(), passwordField, selected.getText(), securityAnswerField);
		
		if (testCreate != null) {
			commonOb.setCurrentUser(testCreate);
			goToPage("view/Courses.fxml");
		} 
		else
			System.out.println("sign up failed");
	}
}
