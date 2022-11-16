package application.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

// handles swapping/showing pages, scenes, popups
public interface Navigation {
	public static final CommonObjs commonOb = CommonObjs.getSingle();
	
	public static final String fileDestination = "resources/";

	// go to another page
	default Pane goToPage(String url) {
		try {
			Pane pane = (Pane) FXMLLoader.load(getClass().getClassLoader().getResource(fileDestination + url));
			Stage rootStage = commonOb.getRootStage();
			Scene scene = new Scene(pane);
			rootStage.setScene(scene);
			return pane;

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("broke");
			return null;
		}
	}
	
	// show popup
	default Pane popup(String url, Pane overlay) {
		try {
			Pane pane = (Pane) FXMLLoader.load(getClass().getClassLoader().getResource(fileDestination + url));
			overlay.getChildren().add(pane);
			return pane;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// remove method (mostly used to remove popups)
	default void remove(Pane container, Pane toRemove) {
		container.getChildren().remove(toRemove);
	}
}
