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

	// trans is the shown rotation. flipback is to return to original state
	private RotateTransition trans = new RotateTransition(Duration.millis(400));
	private RotateTransition flipback = new RotateTransition(Duration.millis(1));
	private final static Point3D rotateAxis = new Point3D(1, 0, 0);

	// define adjustments to card layout when revealing answer
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

		// todo: may change to directly pull learned total from value stored in course
		for (Card m : pool)
			if (m.isLearned())
				learnedTotal++;

		Collections.shuffle(pool);
		currentCard = pool.get(currentIndex);
		totalCount.setText(pool.size() + " Total");
		totalDisplay.setText("/" + pool.size());

		trans.setNode(cardDisplay);
		trans.setCycleCount(1);
		trans.setAxis(rotateAxis);

		flipback.setNode(cardDisplay);
		flipback.setCycleCount(1);
		flipback.setAxis(rotateAxis);
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
		// set card information
		questionDisplay.setText(currentCard.getQuestion());
		answerDisplay.setText(currentCard.getAnswer());
		learnedCheckbox.setSelected(currentCard.isLearned());
		
		// based on adjustment values in fields
		questionDisplay.setLayoutY(flipped ? questionLayoutYAfterFlip : questionLayoutYBeforeFlip);
		questionDisplay.setPrefHeight(flipped ? questionPrefHeightAfterFlip : questionPrefHeightBeforeFlip);
		
		// show answer if user revealed/flipped; hide answer if not flipped yet
		separator.setVisible(flipped);
		answerLabel.setVisible(flipped);
		answerDisplay.setVisible(flipped);
	}

	private void updateDisplay() {
		currentCountDisplay.setText(Integer.toString(currentIndex + 1));
	}

	@FXML
	public void nextCard() {
		if (currentIndex + 1 >= pool.size()) // reached the end index card
			return;
		currentCard = pool.get(++currentIndex);
		swapCard();
	}

	// reset and update for the next card
	private void swapCard() {
		updateDisplay();
		flipped = false;
		updateCardChildren();
	}

	@FXML
	public void previousCard() {
		if (currentIndex <= 0) // already at the beginning index card
			return;
		currentCard = pool.get(--currentIndex);
		swapCard();
	}

	private void updateLearned() {
		learnedCount.setText(learnedTotal + " Learned");
		learnedProgress.setProgress((double) learnedTotal / pool.size());
	}

	// update learned count stored in course and database	
	// todo?: maybe postpone update database to a window event listener on close or hidden
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
