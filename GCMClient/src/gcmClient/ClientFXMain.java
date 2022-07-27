package gcmClient;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class ClientFXMain extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane bp = new BorderPane();
		

		primaryStage.setTitle("Gaming Clan Manager");
		primaryStage.setScene(new Scene(bp, 800, 600));
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}


}
