package application.controller;

import application.model.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SettingsController implements Navigation {

	@FXML
	TextField usernameField;
	@FXML
	PasswordField passwordField;
	@FXML
	MenuButton securityQuestionSelect;
	@FXML
	TextField securityAnswerField;
	
	private MenuItem selected;

	private User currentUser = commonOb.getCurrentUser();

	public void initialize() {
		usernameField.setText(currentUser.getUsername());

		ObservableList<MenuItem> questions = securityQuestionSelect.getItems();
		for (MenuItem q : questions) {
			q.setOnAction(e -> {
				securityQuestionSelect.setText(q.getText());
				selected = q;
			});
			
			if (q.getText().equals(currentUser.getSecurityQuestion()))
				q.fire();
		}

	}

	@FXML
	public void saveChangesPressed() {
		AccountHandler ah = new AccountHandler();
		ah.modifyAccount(currentUser, usernameField.getText(), passwordField,selected.getText(),securityAnswerField);
		System.out.println("successfully changed settings");
	}

	@FXML
	public void goBackClicked() {
		goToPage("view/Courses.fxml");
	}

	@FXML
	public void deleteUser() {
		currentUser.deleteUser();
		goToPage("view/Welcome.fxml");
		commonOb.setCurrentUser(null);
	}

}
