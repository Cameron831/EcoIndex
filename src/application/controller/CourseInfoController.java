package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CourseInfoController implements CloneCourseInfo {

	private static Course currentCourse;

	void setCurrentCourse(Course c) {
		currentCourse = c;
	}

	@FXML
	Label courseName;
	@FXML
	Label cardTotal;
	@FXML
	Label cardTotalLearned;

	public void initialize() {
		courseName.setText(currentCourse.getName());
		cardTotal.setText(String.valueOf(currentCourse.getNumCards()));
		cardTotalLearned.setText(String.valueOf(currentCourse.getLearnedTotal()));
	}

	@FXML
	public void viewCoursePressed() {
		System.out.println("nope");
	}

	@FXML
	public void editCoursePressed() {
		// TODO delete and rename
	}
}
