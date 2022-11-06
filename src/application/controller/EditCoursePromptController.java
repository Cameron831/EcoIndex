package application.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class EditCoursePromptController implements Navigation {

	@FXML StackPane promptPane;
	@FXML TextField newNameField;
	@FXML TextArea newDescriptionField;
	
	@FXML public void exitPrompt() {
		remove(commonOb.getTopCoursePane(), promptPane);
	}
	
	@FXML public void saveChangesPressed() {
		// TODO
		exitPrompt();
	}

}
