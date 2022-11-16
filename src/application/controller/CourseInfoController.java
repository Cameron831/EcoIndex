package application.controller;

import java.io.IOException;

import application.model.Course;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

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
//		System.out.println("not implemented yet");
		createWindow("view/CoursePageTemplate.fxml");
	}
	
	private void createWindow(String url) {
		try {
			Pane pane = (Pane) FXMLLoader.load(getClass().getClassLoader().getResource(Navigation.fileDestination + url));
			Stage newWindow = new Stage();
			newWindow.setTitle(courseName.getText());
			newWindow.setScene(new Scene(pane));
			newWindow.show();
			newWindow.setX(newWindow.getX() + 30);
			newWindow.setY(newWindow.getY() + 35);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	// show popup to edit course
	@FXML
	public void editCoursePressed() {
		if (commonOb.getCurrentUser() != null) {
			commonOb.setTemporaryCourse(commonOb.getCurrentUser().searchCourse(courseName.getText()));
			popup("view/EditCoursePrompt.fxml", commonOb.getTopCoursePane());
		}
		else {
			System.out.println("please login");
		}
	}

	// remove the course in the view and update database
	@FXML
	public void deleteCoursePressed() {
		removeClone(commonOb.getCourseDisplayPane(), container, commonOb.getScrollDisplayCoursePane());
		if (commonOb.getCurrentUser() != null)
			commonOb.getCurrentUser().removeCourse(courseName.getText());
	}
}
