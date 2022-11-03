package application.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class SignUpController implements Navigation {

	@FXML MenuButton securityQuestionSelect;
	
	public void initialize() {
		
		// makes menu display current choice. we can create as many questions as we want without manually creating and setting their action methods
		ObservableList<MenuItem> questions = securityQuestionSelect.getItems();
		for (MenuItem q : questions) 
			q.setOnAction(e -> securityQuestionSelect.setText(q.getText()));
		
	}
	
	// TODO save username, password, security question/answer to database
	
	
	// navigating back to login page
	@FXML public void goToLogin() {
		goToPage("view/Login.fxml");
	}
}
