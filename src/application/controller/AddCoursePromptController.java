package application.controller;

import application.model.User;
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

	@FXML
	public void confirmButtonPressed() {
		addCourse(nameField.getText());
		exitPrompt();
	}

	private void addCourse(String name) {
		CloneCourseInfo cloner = new CourseInfoController();
		User u = commonOb.getCurrentUser();

		// save course to database if logged in. show it locally without saving if not
		cloner.cloneCourse(commonOb.getCourseDisplayPane(), commonOb.getScrollDisplayCoursePane(),
				(u == null) ? new Course(name) : u.addCourse(name));
	}

	// rempve popup
	@FXML
	public void exitPrompt() {
		remove(commonOb.getTopCoursePane(), promptPane);
	}
}
