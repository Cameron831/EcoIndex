package application.controller;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

class CourseCommonObjs {
	// singleton essentials
	private static CourseCommonObjs singleInstance = new CourseCommonObjs();

	private CourseCommonObjs() {
	};

	public static CourseCommonObjs getSingle() {
		return singleInstance;
	}

	private Pane topPane;

	Pane getTopPane() {
		return topPane;
	}

	void setTopPane(Pane topPane) {
		this.topPane = topPane;
	}

	private Pane courseDisplay;

	Pane getCourseDisplayPane() {
		return courseDisplay;
	}

	void setCourseDisplayPane(Pane p) {
		this.courseDisplay = p;
	}

	private ScrollPane scrollDisplayPane;

	ScrollPane getScrollDisplayPane() {
		return scrollDisplayPane;
	}

	void setScrollDisplayPane(ScrollPane p) {
		this.scrollDisplayPane = p;
	}

	private String courseName;

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	private CoursesController cc = new CoursesController();

	void passSignal() {
		cc.createCourse(courseName);
	}

}
