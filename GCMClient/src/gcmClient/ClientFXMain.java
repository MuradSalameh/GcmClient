package gcmClient;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ClientFXMain extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		VBox vb = new VBox(); // sidebar menu left side		
		vb.setId("vb-bgcolor");
		vb.getStyleClass().add("vb-bgcolor");
		
		
		Label logoTxt = new Label("GCM");
		logoTxt.setId("logo-txt");
		logoTxt.getStyleClass().add("logo-txt");
		
		vb.getChildren().add(logoTxt);
		vb.setPrefWidth(200);
		vb.setPrefHeight(600);
		AnchorPane ap = new AnchorPane(vb);	
		
		ap.setLeftAnchor(vb, 0.0);
		

		primaryStage.setTitle("Gaming Clan Manager");
		Scene scene = new Scene(ap, 900, 600);
		primaryStage.setScene(scene);
		scene.getStylesheets().add("style.css");
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}


}
