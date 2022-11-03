package application.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public interface Navigation {
	public static final CommonObjs commonOb = CommonObjs.getSingle();

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
}
