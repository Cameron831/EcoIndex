package application.controller;

import application.model.Card;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public interface CloneCardInfo extends Clonable {
	default void cloneCard(Pane cardDisplay, Region sizeAdjustment, Card c) {
		CommonObjs.getSingle().setCurrentCard(c);
		cloning("view/IndexCardInfoTemplate.fxml", cardDisplay, sizeAdjustment);
	}
}
