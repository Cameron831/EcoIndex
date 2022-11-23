package application.controller;

import application.model.Card;
import application.model.Course;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.control.CheckBox;

public class AddCardPromptController implements Navigation, CloneCardInfo {

	@FXML
	StackPane promptPane;
	@FXML
	TextArea answerField;
	@FXML
	TextArea questionField;
	@FXML CheckBox learnedCheckbox;

	@FXML public void confirmButtonPressed() {
		Course currentCourse = commonOb.getOpenedCourse();
		Card newCard = new Card(questionField.getText(), answerField.getText(), learnedCheckbox.isSelected());
//		currentCourse.add
		cloneCard(commonOb.getIndexCardDisplayPane(), commonOb.getCardScrollDisplayPane(), currentCourse.addCard(newCard));
		exitPrompt();
	}

	@FXML
	public void exitPrompt() {
//		removeClone()
		remove(commonOb.getTopCoursePagePane(), promptPane);
	}

}
