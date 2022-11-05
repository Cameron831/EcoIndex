package application.controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public interface Clonable {
	default void cloning(String template, Pane destination, Region sizeAdjustment) {
		try {
			Pane pane = (Pane) FXMLLoader.load(getClass().getClassLoader().getResource(template));
			destination.getChildren().add(pane);

			// can maybe be optimized with flag, but do that later
			if (sizeAdjustment.getMaxHeight() > sizeAdjustment.getHeight())
				sizeAdjustment.setPrefHeight(sizeAdjustment.getPrefHeight() + pane.getPrefHeight());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
