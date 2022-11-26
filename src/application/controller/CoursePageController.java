package application.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import application.model.Card;
import application.model.Course;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

public class CoursePageController implements Navigation, CloneCardInfo {

	@FXML
	Label courseTitle;
	@FXML
	Label courseDescription;

	private Course currentCourse;
	@FXML
	Label reviewSelection;
	@FXML
	ToolBar toolBar;
	@FXML
	VBox indexCardDisplay;
	@FXML
	AnchorPane topPane;
	@FXML
	ScrollPane cardScrollDisplay;

	private List<Card> reviewPool;

	private enum CourseType {
		ALL, LEARNED, NOTLEARNED;
	}

	private CourseType courseType = CourseType.ALL;
	@FXML
	StackPane warningPane;

	public void initialize() {
		// share containers
		commonOb.setIndexCardDisplayPane(indexCardDisplay);
		commonOb.setCardScrollDisplayPane(cardScrollDisplay);
		commonOb.setTopCoursePagePane(topPane);

		currentCourse = commonOb.getOpenedCourse();
		courseTitle.setText("Course " + currentCourse.getName());
		if (!currentCourse.getDescription().isEmpty()) // otherwise shows the default "No description available"
			courseDescription.setText(currentCourse.getDescription());

		ObservableList<Node> buttons = toolBar.getItems();
		// update view of what category is to be reviewed
		for (Node q : buttons)
			q.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> reviewSelection.setText(q.getAccessibleText()));

		updateCardPane();
	}

	private void updateCardPane() {
		indexCardDisplay.getChildren().clear();
		reviewPool = setPool(courseType);
		for (Card c : reviewPool)
			cloneCard(indexCardDisplay, cardScrollDisplay, c);
	}

	private List<Card> setPool(CourseType courseType) {
		List<Card> pool = new ArrayList<>();
		
		// todo: maybe add learned total to be checked here

		switch (courseType) {
		case ALL:
			pool = currentCourse.getCards();
			break;

		case LEARNED:
			for (Card c : currentCourse.getCards())
				if (c.isLearned())
					pool.add(c);
			break;

		case NOTLEARNED:
			for (Card c : currentCourse.getCards())
				if (!c.isLearned())
					pool.add(c);
			break;
		}

		return pool;
	}

	@FXML
	public void goBackClicked() {
		goToPage("view/Courses.fxml");
	}

	private void createWindow(String url) {
		try {
			Pane pane = (Pane) FXMLLoader
					.load(getClass().getClassLoader().getResource(Navigation.fileDestination + url));
			Stage newWindow = new Stage();
			newWindow.setTitle("Reviewing " + courseTitle.getText());
			newWindow.setScene(new Scene(pane));
			newWindow.show();
			newWindow.setX(newWindow.getX() + 30);
			newWindow.setY(newWindow.getY() + 35);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void reviewButtonPressed() {
		if (reviewPool.size() <= 0) {
			new Alert("No available cards to review", topPane);
			return;
		}
		
		// pass a cloned copy to be randomized during review
		commonOb.setReviewCards(new ArrayList<>(reviewPool));
		createWindow("view/CardReview.fxml");
		warningPane.setVisible(true);
	}

	@FXML
	public void addCardPressed() {
		popup("view/AddCardPrompt.fxml", topPane);
	}

	@FXML
	public void allCardsSelected() {
		courseType = CourseType.ALL;
		updateCardPane();
	}

	@FXML
	public void notLearnedSelected() {
		courseType = CourseType.NOTLEARNED;
		updateCardPane();
	}

	@FXML
	public void learnedSelected() {
		courseType = CourseType.LEARNED;
		updateCardPane();
	}

	@FXML
	public void refresh() {
		goToPage("view/CoursePageTemplate.fxml");
	}

}
