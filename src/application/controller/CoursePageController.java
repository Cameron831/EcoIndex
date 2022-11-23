package application.controller;

import java.io.IOException;

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

public class CoursePageController implements Navigation,CloneCardInfo {

	@FXML
	Label courseTitle;
	@FXML
	Label courseDescription;

	private Course currentCourse;
	@FXML
	Label reviewSelection;
	@FXML
	ToolBar toolBar;
	@FXML VBox indexCardDisplay;
	@FXML AnchorPane topPane;
	@FXML ScrollPane cardScrollDisplay;

	public void initialize() {
		commonOb.setIndexCardDisplayPane(indexCardDisplay);
		commonOb.setCardScrollDisplayPane(cardScrollDisplay);
		commonOb.setTopCoursePagePane(topPane);
		
		
		
		
		
		currentCourse = commonOb.getOpenedCourse();
		courseTitle.setText("Course " + currentCourse.getName());
		if (!currentCourse.getDescription().isEmpty())
			courseDescription.setText(currentCourse.getDescription());

		ObservableList<Node> buttons = toolBar.getItems();
		for (Node q : buttons)
//			q.setOnAction(e -> {
//				securityQuestionSelect.setText(q.getText());
//				selected = q;
//			});
			q.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
				reviewSelection.setText(q.getAccessibleText());
			});
		
		
		for (Card c : currentCourse.getCards())
			cloneCard(indexCardDisplay, cardScrollDisplay,c);
		
		
		
		
		
//		Card a = new Card("a", "a", false);
//		Card b = new Card("ab", "ab", false);
//		Card c = new Card("ac", "ac", true);
//		Card d = new Card("ad", "ad", false);
//		Card l = new Card("ae", "ae", true);
//		Card f = new Card("af", "af", false);
//		Card g = new Card("ag", "ag", false);
//
//		List<Card> list = new ArrayList<>();
//		list.add(a);
//		list.add(b);
//		list.add(c);
//		list.add(d);
//		list.add(l);
//		list.add(f);
//		list.add(g);
		

//		try {
//			for (Card i : list) {
//				commonOb.setCurrentCard(i);
//				Pane pane = (Pane) FXMLLoader
//						.load(getClass().getClassLoader().getResource("resources/view/IndexCardInfoTemplate.fxml"));
//				indexCardDisplay.getChildren().add(pane);
//			}
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

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
		createWindow("view/CardReview.fxml");
	}

	@FXML public void addCardPressed() {
		popup("view/AddCardPrompt.fxml", topPane);
	}

}
