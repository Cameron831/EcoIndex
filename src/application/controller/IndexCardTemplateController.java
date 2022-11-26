package application.controller;

import application.model.Card;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class IndexCardTemplateController implements Navigation, CloneCardInfo {

	@FXML
	Label questionLabel;
	@FXML
	Label answerLabel;

	private Card currentCard;
	@FXML
	CheckBox learnedCheckbox;
	@FXML
	AnchorPane cardPane;

	void renameCardQuestion(String newName) {
		questionLabel.setText(newName);
	}

	void renameCardAnswer(String newName) {
		answerLabel.setText(newName);
	}

	void setCheckboxState(boolean b) {
		learnedCheckbox.setSelected(b);
	}

	public void initialize() {
		currentCard = commonOb.getCurrentCard();
		questionLabel.setText(currentCard.getQuestion());
		answerLabel.setText(currentCard.getAnswer());
		learnedCheckbox.setSelected(currentCard.isLearned());
	}

	@FXML
	public void toggleLearned() {
		// update learned count
		if (currentCard.setLearned(learnedCheckbox.isSelected())) {
			commonOb.getOpenedCourse().updateLearnedSingle(currentCard.isLearned());
			commonOb.getOpenedCourse().updateCourse();
		}
		currentCard.updateCard();
	}

	@FXML
	public void editCard() {
		commonOb.setCurrentCard(currentCard);
		commonOb.setCurrentCardEdit(this);
		popup("view/EditCardPrompt.fxml", commonOb.getTopCoursePagePane());
	}

	@FXML
	public void deleteCard() {
		// delete from view and update database
		commonOb.getOpenedCourse().deleteCard(currentCard);
		removeClone(commonOb.getIndexCardDisplayPane(), cardPane, commonOb.getCardScrollDisplayPane());
	}
}
