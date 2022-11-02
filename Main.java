package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("view/Sample.fxml"));
			
			Scene scene = new Scene(root,1200,800);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("css/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			CommonObjs commonOb = CommonObjs.getSingle();
			commonOb.setRootPage(root);
			commonOb.setRootStage(primaryStage);

			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
