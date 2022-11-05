package application.controller;

import application.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CoursesController implements Navigation {

	@FXML
	VBox courseDisplay;
	@FXML
	ScrollPane scrollDisplay;

	private User currentUser = commonOb.getCurrentUser();
	private CloneCourseInfo cloner = new CourseInfoController();

	@FXML
	Pane topPane;

	public void initialize() {
		if (currentUser != null)
			for (Course c : currentUser.getCourses())
				cloner.cloneCourse(courseDisplay, scrollDisplay, c);
	}

	private static CourseCommonObjs midlayer = CourseCommonObjs.getSingle();

	@FXML
	public void addCoursePressed() {
		midlayer.setTopPane(topPane);
		midlayer.setCourseDisplayPane(courseDisplay);
		midlayer.setScrollDisplayPane(scrollDisplay);
		popup("view/AddCoursePrompt.fxml", topPane);
	}

	void createCourse(String name) {
		cloner.cloneCourse(midlayer.getCourseDisplayPane(), midlayer.getScrollDisplayPane(),
				currentUser.addCourse(name));
	}

	@FXML
	public void logOutPressed() {
		commonOb.setCurrentUser(null);
		goToPage("view/Welcome.fxml");
	}
}
