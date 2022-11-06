package application.controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public interface Clonable {
	default void cloning(String template, Pane destination, Region sizeAdjustment) {
		try {
			// load template and append to parent node. formatting taken care by controller
			Pane pane = (Pane) FXMLLoader.load(getClass().getClassLoader().getResource(template));
			destination.getChildren().add(pane);

			// dynamic resizing the parent container when clone
			// only increases size is if it smaller than max height
			if (sizeAdjustment.getMaxHeight() > sizeAdjustment.getHeight())
				sizeAdjustment.setPrefHeight(sizeAdjustment.getPrefHeight() + pane.getPrefHeight());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	default void removeClone(Pane container, Pane toRemove, Region sizeAdjustment) {
		container.getChildren().remove(toRemove);
		sizeAdjustment.setPrefHeight(sizeAdjustment.getPrefHeight() - toRemove.getPrefHeight());
	}
}
