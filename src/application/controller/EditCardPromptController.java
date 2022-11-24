package application.controller;

import application.model.Card;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;

public class EditCardPromptController implements Navigation {

	@FXML
	StackPane promptPane;
	@FXML
	TextArea answerField;
	@FXML
	TextArea questionField;
	@FXML
	CheckBox learnedCheckbox;

	private Card cardToEdit = commonOb.getCurrentCard();

	// index card on course page nodes
	private IndexCardTemplateController cardDisplay = commonOb.getCurrentCardEdit();

	public void initialize() {
		questionField.setText(cardToEdit.getQuestion());
		answerField.setText(cardToEdit.getAnswer());
		learnedCheckbox.setSelected(cardToEdit.isLearned());
	}

	@FXML
	public void saveChangesPressed() {
		cardToEdit.setQuestion(questionField.getText());
		cardToEdit.setAnswer(answerField.getText());
		if (cardToEdit.setLearned(learnedCheckbox.isSelected())) {
			commonOb.getOpenedCourse().updateLearnedSingle(cardToEdit.isLearned());
			commonOb.getOpenedCourse().updateCourse();
		}
		
		cardToEdit.updateCard();

		cardDisplay.renameCardQuestion(questionField.getText());
		cardDisplay.renameCardAnswer(answerField.getText());
		cardDisplay.setCheckboxState(learnedCheckbox.isSelected());

		exitPrompt();
	}

	@FXML
	public void exitPrompt() {
		remove(commonOb.getTopCoursePagePane(), promptPane);
	}

}
