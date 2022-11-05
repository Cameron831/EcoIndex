package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class AddCoursePromptController implements Navigation {

	@FXML
	StackPane promptPane;
	@FXML
	TextField nameField;
	@FXML
	TextArea descriptionField;

	private CourseCommonObjs midlayer = CourseCommonObjs.getSingle();

	@FXML
	public void confirmButtonPressed() {
		midlayer.setCourseName(nameField.getText());
		exitPrompt();
		midlayer.passSignal();
	}

	@FXML public void exitPrompt() {
		remove(midlayer.getTopPane(), promptPane);
	}
}
