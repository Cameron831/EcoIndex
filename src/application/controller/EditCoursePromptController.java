package application.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

//todo add description to be changable
public class EditCoursePromptController implements Navigation {

	@FXML StackPane promptPane;
	@FXML TextField newNameField;
	@FXML TextArea newDescriptionField;
	
	private Course currentCourse = commonOb.getTemporaryCourse();
	
	public void initialize() {
		newNameField.setText(currentCourse.getName());
	}
	
	@FXML public void exitPrompt() {
		remove(commonOb.getTopCoursePane(), promptPane);
	}
	
	@FXML public void saveChangesPressed() {
		currentCourse.setName(newNameField.getText());
		commonOb.getCurrentUser().updateDB();
		goToPage("view/Courses.fxml");
	}

}
