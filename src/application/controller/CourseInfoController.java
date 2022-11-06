package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

public class CourseInfoController implements CloneCourseInfo, Navigation {

	// hold the info that we want to use for clone
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
	@FXML
	TilePane container;

	// set the info to their corresponding spots
	public void initialize() {
		courseName.setText(currentCourse.getName());
		cardTotal.setText(String.valueOf(currentCourse.getNumCards()));
		cardTotalLearned.setText(String.valueOf(currentCourse.getLearnedTotal()));
	}

	@FXML
	public void goToCourse() {
		// todo
		System.out.println("not implemented yet");
	}

	// show popup to edit course
	// todo: need to retrieve and update
	@FXML
	public void editCoursePressed() {
		popup("view/EditCoursePrompt.fxml", commonOb.getTopCoursePane());
	}
	
	// remove the course in the view and update database
	@FXML
	public void deleteCoursePressed() {
		removeClone(commonOb.getCourseDisplayPane(), container, commonOb.getScrollDisplayCoursePane());
		if (commonOb.getCurrentUser() != null)
			commonOb.getCurrentUser().removeCourse(courseName.getText());
	}
}
