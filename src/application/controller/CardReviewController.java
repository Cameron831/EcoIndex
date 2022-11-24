package application.controller;

import java.util.Collections;
import java.util.List;

import application.model.Card;
import application.model.Course;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class CardReviewController {

	@FXML
	AnchorPane cardDisplay;

	@FXML
	Label questionDisplay;

	@FXML
	Separator separator;

	@FXML
	Label answerLabel;

	@FXML
	Pane nextCardButton;

	@FXML
	Pane previousCardButton;

	@FXML
	Label answerDisplay;

	@FXML
	Button showAnswerButton;

	private boolean flipped = false;
	private boolean animationRunning = false;

	private RotateTransition trans = new RotateTransition(Duration.millis(400));
	private RotateTransition flipback = new RotateTransition(Duration.millis(1));

	private final static double questionLayoutYBeforeFlip = 115;
	private final static double questionLayoutYAfterFlip = 60;
	private final static double questionPrefHeightBeforeFlip = 400;
	private final static double questionPrefHeightAfterFlip = 227;

	private List<Card> pool = CommonObjs.getSingle().getReviewCards();
//	private ListIterator<Card> iterate;
	private int currentIndex = 0;
	private Card currentCard;

	private int learnedTotal = 0;
	
	private Course course;

	@FXML
	CheckBox learnedCheckbox;

	@FXML
	ProgressIndicator learnedProgress;

	@FXML
	Label learnedCount;

	@FXML
	Label totalCount;

	@FXML
	Label currentCountDisplay;

	@FXML
	Label totalDisplay;

	public void initialize() {	
		course = CommonObjs.getSingle().getOpenedCourse();
		
		for (Card m : pool)
			if (m.isLearned())
				learnedTotal++;
		
		Collections.shuffle(pool);
		currentCard = pool.get(currentIndex);
		totalCount.setText(pool.size() + " Total");

		trans.setNode(cardDisplay);
		trans.setCycleCount(1);
		trans.setAxis(new Point3D(1, 0, 0));

		flipback.setNode(cardDisplay);
		flipback.setCycleCount(1);
		flipback.setAxis(new Point3D(1, 0, 0));
		flipback.setByAngle(-180);

		trans.setOnFinished(e -> {
			flipback.play();
			updateCardChildren();
		});

		flipback.setOnFinished(e -> {
			for (Node n : cardDisplay.getChildren())
				n.setOpacity(1);
			animationRunning = false;
		});
		updateDisplay();
		updateLearned();
		updateCardChildren();
	}

	@FXML
	public void animate() {
		if (!animationRunning) {
			animationRunning = true;
			for (Node n : cardDisplay.getChildren())
				n.setOpacity(0);

			toggle();
			trans.play();
		}
	}

	private void toggle() {
		trans.setByAngle(flipped ? 180 : -180);
		flipped = !flipped;
	}

	private void updateCardChildren() {
		questionDisplay.setText(currentCard.getQuestion());
		answerDisplay.setText(currentCard.getAnswer());
		learnedCheckbox.setSelected(currentCard.isLearned());
		questionDisplay.setLayoutY(flipped ? questionLayoutYAfterFlip : questionLayoutYBeforeFlip);
		questionDisplay.setPrefHeight(flipped ? questionPrefHeightAfterFlip : questionPrefHeightBeforeFlip);
		separator.setVisible(flipped);
		answerLabel.setVisible(flipped);
		answerDisplay.setVisible(flipped);
	}

	private void updateDisplay() {
		currentCountDisplay.setText(Integer.toString(currentIndex + 1));
		totalDisplay.setText("/" + pool.size());
	}

	@FXML
	public void nextCard() {
		if (currentIndex + 1 >= pool.size()) {
			System.out.println("reached the end");
			return;
		}
		currentCard = pool.get(++currentIndex);
		swapCard();
	}

	private void swapCard() {
		updateDisplay();
		flipped = false;
		updateCardChildren();
	}

	@FXML
	public void previousCard() {
		if (currentIndex <= 0) {
			System.out.println("already at the beginning");
			return;
		}
		currentCard = pool.get(--currentIndex);
		swapCard();
	}

	private void updateLearned() {
		learnedCount.setText(learnedTotal + " Learned");
		learnedProgress.setProgress((double) learnedTotal / pool.size());
	}

	@FXML
	public void learnedToggled() {
		learnedTotal += currentCard.isLearned() ? -1 : 1;
		currentCard.setLearned(learnedCheckbox.isSelected());
		currentCard.updateCard();
		course.updateLearnedSingle(currentCard.isLearned());
		course.updateCourse();
		updateLearned();
	}
}
