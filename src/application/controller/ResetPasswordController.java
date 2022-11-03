package application.controller;

import javafx.fxml.FXML;

public class ResetPasswordController implements Navigation {

	@FXML public void goToLogin() {
		goToPage("view/Login.fxml");
	}
	
}
