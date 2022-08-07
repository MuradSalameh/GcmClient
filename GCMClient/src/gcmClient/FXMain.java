package gcmClient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class FXMain extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		
		Parent root = FXMLLoader.load(getClass().getResource("startScene.fxml")); 
		
		System.out.println("root: "+ root);
		primaryStage.setTitle("Gaming Clan Manager");
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		//primaryStage.setResizable(false);
		
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
