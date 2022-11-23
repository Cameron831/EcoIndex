package application.controller;

import application.model.Course;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

//todo add description to be changable
public class EditCoursePromptController implements Navigation {

	@FXML
	StackPane promptPane;
	@FXML
	TextField newNameField;
	@FXML
	TextArea newDescriptionField;

	private Label oldCourseNameLabel = commonOb.getTemporaryCourseNameDisplay();

	private Course currentCourse = commonOb.getTemporaryCourse();

	public void initialize() {
		newNameField.setText(currentCourse.getName());
		newDescriptionField.setText(currentCourse.getDescription());
	}

	@FXML
	public void exitPrompt() {
		remove(commonOb.getTopCoursePane(), promptPane);
	}

	@FXML
	public void saveChangesPressed() {
		currentCourse.setName(newNameField.getText());
		currentCourse.setDescription(newDescriptionField.getText());

		oldCourseNameLabel.setText(newNameField.getText());

//		if (commonOb.getCurrentUser() != null)
			currentCourse.updateCourse();

		exitPrompt();

	}

}
