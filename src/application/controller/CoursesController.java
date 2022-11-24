package application.controller;

import application.model.Course;
import application.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CoursesController implements CloneCourseInfo, Navigation {

	@FXML
	VBox courseDisplay;
	@FXML
	ScrollPane scrollDisplay;

	private User currentUser = commonOb.getCurrentUser();

	@FXML
	Pane topPane;

	// load in all coursed saved to the logged in user
	public void initialize() {
		if (currentUser != null)
			for (Course c : currentUser.getCourses())
				cloneCourse(courseDisplay, scrollDisplay, c);

		// share the containers going to be used to place courses and popups
		commonOb.setTopCoursePane(topPane);
		commonOb.setCourseDisplayPane(courseDisplay);
		commonOb.setScrollDisplayCoursePane(scrollDisplay);
		
		commonOb.getRootStage().setTitle("Courses - EcoIndex");
	}

	@FXML
	public void addCoursePressed() {
		popup("view/AddCoursePrompt.fxml", topPane);
	}

	@FXML
	public void logOutPressed() {
		commonOb.setCurrentUser(null);
		commonOb.getRootStage().setTitle("EcoIndex");
		goToPage("view/Welcome.fxml");
	}

	@FXML public void goToSettings() {
		if (currentUser == null) {
			System.out.println("login to change settings");
			return;
		}
		goToPage("view/Settings.fxml");
	}
}
