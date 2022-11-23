package application.controller;

import application.model.Card;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;

public class IndexCardTemplateController implements Navigation {

	@FXML Label questionLabel;
	@FXML Label answerLabel;
	
	private Card currentCard;
	@FXML CheckBox learnedCheckbox;
	
	public void initialize() {
		currentCard = commonOb.getCurrentCard();
		questionLabel.setText(currentCard.getQuestion());
		answerLabel.setText(currentCard.getAnswer());
		learnedCheckbox.setSelected(currentCard.isLearned());
	}

	@FXML public void toggleLearned() {
		currentCard.setLearned(learnedCheckbox.isSelected());
	}

	@FXML public void editCard() {
		System.out.println("modify button pressed");
	}

	@FXML public void deleteCard() {
		System.out.println("delete button pressed");
	}
}
