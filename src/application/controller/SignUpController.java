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

		// makes menu display current choice. no need for individual action methods
		ObservableList<MenuItem> questions = securityQuestionSelect.getItems();
		for (MenuItem q : questions)
			q.setOnAction(e -> {
				securityQuestionSelect.setText(q.getText());
				selected = q;
			});

	}

	@FXML
	public void goToLogin() {
		goToPage("view/Login.fxml");
	}

	@FXML
	public void signUpButtonPressed() {
		// require user to pick security question
		if (selected == null) {
			System.out.println("pick a security question");
			return;
		}

		SignUpHandler handler = new SignUpHandler();
		User testCreate = handler.newUser(usernameField.getText(), passwordField, selected.getText(),
				securityAnswerField);

		// password or security answer was empty, or username is taken
		if (testCreate != null) {
			commonOb.setCurrentUser(testCreate);
			goToPage("view/Courses.fxml");
		} 
	}
}
