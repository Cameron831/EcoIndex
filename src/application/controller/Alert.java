package application.controller;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class Alert {

	@FXML
	StackPane warningPane;
	private static String text;
	private static String color;
	@FXML
	Label alertLabel;

	// required no-argument constructor. will receive error without it
	public Alert() {
	}

	public Alert(String s, Pane overlay) {
		// error alert (most common use)
		this(s, overlay, "#DC4C64");
	}

	// customize color
	public Alert(String s, Pane overlay, String c) {
		text = s;
		color = c;
		try {
			Pane pane = (Pane) FXMLLoader.load(getClass().getClassLoader().getResource("resources/view/Alert.fxml"));
			overlay.getChildren().add(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// pause/show alert for 2 seconds and fade away in 1 second
	private PauseTransition pt = new PauseTransition(Duration.millis(2000));
	private FadeTransition ft = new FadeTransition(Duration.millis(1000));
	private SequentialTransition st = new SequentialTransition(pt, ft);
	
	// define position of alert when loaded in
	private final static double posX = 250;
	private final static double posY = 25;

	public void initialize() {
		ft.setNode(warningPane);
		ft.setFromValue(1.0);
		ft.setToValue(0);
		st.play();

		alertLabel.setText(text);
		warningPane.setLayoutX(posX);
		warningPane.setLayoutY(posY);

		warningPane.setStyle("-fx-background-color:" + color);
	}
}
