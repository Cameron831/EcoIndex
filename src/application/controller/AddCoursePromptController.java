package application.controller;

import application.model.Course;
import application.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class AddCoursePromptController implements CloneCourseInfo, Navigation {

	@FXML
	StackPane promptPane;
	@FXML
	TextField nameField;
	@FXML
	TextArea descriptionField;

	@FXML
	public void confirmButtonPressed() {

		User u = commonOb.getCurrentUser();
		Course tempStore = new Course(nameField.getText(), descriptionField.getText());

		// null check
		// save course to database if logged in. show it locally without saving if not
		Course toCreate = (u == null) ? tempStore : u.addCourse(tempStore);

		if (toCreate == null) {
			new Alert("Unable to add course", commonOb.getTopCoursePane());
			return;
		}

		cloneCourse(commonOb.getCourseDisplayPane(), commonOb.getScrollDisplayCoursePane(), toCreate);
		exitPrompt();
	}

	// rempve popup
	@FXML
	public void exitPrompt() {
		remove(commonOb.getTopCoursePane(), promptPane);
	}
}
