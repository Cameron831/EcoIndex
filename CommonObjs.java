package application;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

// singleton class
public class CommonObjs {
	private static CommonObjs singleInstance = new CommonObjs();
	private CommonObjs() {};
	public static CommonObjs getSingle() {
		return singleInstance;
	}
	
	
	// share login page
	private AnchorPane loginPage;
	
	public AnchorPane getLoginPage() {
		return loginPage;
	}
	public void setRootPage(AnchorPane logPage) {
		this.loginPage = logPage;
	}
	
	// share the same stage
	private Stage rootStage;
	public void setRootStage(Stage primaryStage) {
		this.rootStage = primaryStage;
		
	}
	public Stage getRootStage() {
		return rootStage;
		
	}
	
	
}
