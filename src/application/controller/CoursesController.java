package application.controller;

import application.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class CoursesController implements Navigation {

	@FXML
	VBox courseDisplay;
	@FXML
	ScrollPane scrollDisplay;

	private User currentUser = commonOb.getCurrentUser();
	private CloneCourseInfo cloner = new CourseInfoController();

	public void initialize() {
		if (currentUser != null) 
			for (Course c : currentUser.getCourses()) 
				cloner.cloneCourse(courseDisplay, scrollDisplay, c);
	}

	@FXML
	public void addCoursePressed() {
		// TODO add popup for name of new course
		cloner.cloneCourse(courseDisplay, scrollDisplay, new Course("hello"));

	}

	@FXML public void logOutPressed() {
		commonOb.setCurrentUser(null);
		goToPage("view/Welcome.fxml");
	}

}
