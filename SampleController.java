package application.controller;

import java.io.IOException;
import java.net.URL;

import application.CommonObjs;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;




// all those comments are stuff i was trying with testing, they don't work


public class SampleController {
	
//	private AnchorPane rootPage = commonOb.getLoginPage();
//	@FXML AnchorPane loginPage;
//	private Stage rootStage = commonOb.getRootStage();
	
	
	private CommonObjs commonOb = CommonObjs.getSingle();
	@FXML public void goToSignUp() {
		URL url = getClass().getClassLoader().getResource("view/Signup.fxml");
		
		try {
			AnchorPane signUp = (AnchorPane) FXMLLoader.load(url);
			Stage rootStage = commonOb.getRootStage();
			Scene scene = new Scene(signUp);
			
			rootStage.setScene(scene);
			rootStage.show();
			
//			System.out.println(rootPage.getChildren().size());
//			rootPage.getChildren().remove(0);
//			rootPage.getChildren().add(signUp);
			
//			System.out.println(loginPage.getChildren().size());
//
//			loginPage.getChildren().remove(0);
//			loginPage.getChildren().add(signUp);
			
//			System.out.println()
			
			
			
		} catch (IOException e) {
//			 TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
