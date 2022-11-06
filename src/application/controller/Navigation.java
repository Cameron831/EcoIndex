package application.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

// handles swapping/showing pages, scenes, popups
public interface Navigation {
	public static final CommonObjs commonOb = CommonObjs.getSingle();
	// todo: all are stored in view folder, so can set as constant here

	// go to another page
	default void goToPage(String url) {
		try {
			Pane pane = (Pane) FXMLLoader.load(getClass().getClassLoader().getResource(url));
			Stage rootStage = commonOb.getRootStage();
			Scene scene = new Scene(pane);
			rootStage.setScene(scene);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// show popup
	default void popup(String url, Pane overlay) {
		try {
			Pane pane = (Pane) FXMLLoader.load(getClass().getClassLoader().getResource(url));
			overlay.getChildren().add(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// remove method (mostly used to remove popups)
	default void remove(Pane container, Pane toRemove) {
		container.getChildren().remove(toRemove);
	}
}
