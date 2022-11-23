package application.controller;

import application.model.Course;

// currently not used
public class CourseCommonObjs {
	// singleton essentials
	private static CourseCommonObjs singleInstance = new CourseCommonObjs();

	private CourseCommonObjs() {
	};

	public static CourseCommonObjs getSingle() {
		return singleInstance;
	}

	private Course currentCourse;

	public Course getCurrentCourse() {
		return currentCourse;
	}

	public void setCurrentCourse(Course currentCourse) {
		this.currentCourse = currentCourse;
	}
	
	delete maybe
}
