package application.controller;

import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public interface CloneCourseInfo extends Clonable {
	default void cloneCourse(Pane courseDisplay, Region sizeAdjustment, Course c) {
		CourseInfoController comm = new CourseInfoController();
		comm.setCurrentCourse(c);
		cloning("view/CourseInfoTemplate.fxml", courseDisplay, sizeAdjustment);
	}
}