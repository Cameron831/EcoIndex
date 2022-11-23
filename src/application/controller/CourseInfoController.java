package application.controller;

import application.model.Course;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

public class CourseInfoController implements CloneCourseInfo, Navigation {

	// hold the info that we want to use for clone
	private Course currentCourse;

//	void setCurrentCourse(Course c) {
//		currentCourse = c;
//	}

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
		currentCourse = commonOb.getCurrentCourse();
		currentCourse.initializeCards();
		courseName.setText(currentCourse.getName());
		cardTotal.setText(String.valueOf(currentCourse.getNumCards()));
		cardTotalLearned.setText(String.valueOf(currentCourse.getLearnedTotal()));
	}

	@FXML
	public void goToCourse() {
		commonOb.setOpenedCourse(currentCourse);
		goToPage("view/CoursePageTemplate.fxml");
	}

	// show popup to edit course
	@FXML
	public void editCoursePressed() {
//		if (commonOb.getCurrentUser() != null) {
		commonOb.setTemporaryCourse(currentCourse);
		commonOb.setTemporaryCourseNameDisplay(courseName);
		popup("view/EditCoursePrompt.fxml", commonOb.getTopCoursePane());
//		} else { 
//			System.out.println("please login");
//		}
	}

	// remove the course in the view and update database
	@FXML
	public void deleteCoursePressed() {
		removeClone(commonOb.getCourseDisplayPane(), container, commonOb.getScrollDisplayCoursePane());
		if (commonOb.getCurrentUser() != null)
			commonOb.getCurrentUser().removeCourse(currentCourse);
	}
}
