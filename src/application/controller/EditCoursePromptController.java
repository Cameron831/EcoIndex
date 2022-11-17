package application.controller;

import application.model.Course;
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
		newDescriptionField.setText(currentCourse.getDescription());
	}
	
	@FXML public void exitPrompt() {
		remove(commonOb.getTopCoursePane(), promptPane);
	}
	
	@FXML public void saveChangesPressed() {
		currentCourse.setName(newNameField.getText());
		currentCourse.setDescription(newDescriptionField.getText());
		commonOb.getCurrentUser().updateDB();
		goToPage("view/Courses.fxml");
	}

}
