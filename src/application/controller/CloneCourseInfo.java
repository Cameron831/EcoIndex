package application.controller;

import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

// intermediary and customize Clonable for courses
public interface CloneCourseInfo extends Clonable {
	default void cloneCourse(Pane courseDisplay, Region sizeAdjustment, Course c) {
		CourseInfoController comm = new CourseInfoController();
		comm.setCurrentCourse(c);
		cloning("view/CourseInfoTemplate.fxml", courseDisplay, sizeAdjustment);
	}
	
	@Override
	default void removeClone(Pane container, Pane toRemove, Region sizeAdjustment) {
		double height = container.getHeight() + toRemove.getHeight();
		container.getChildren().remove(toRemove);
		if (height < sizeAdjustment.getHeight())
			sizeAdjustment.setPrefHeight(sizeAdjustment.getPrefHeight() - toRemove.getPrefHeight());
	}
}
