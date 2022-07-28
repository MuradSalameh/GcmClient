package gcmClient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;



public class FxmlDocumentController implements Initializable {
	@FXML
	private Label label;
	
	@FXML
	private BorderPane bpScreenView;
	
	@FXML
	private void handleHomeBtn(ActionEvent event) throws IOException {
		
		FxmlLoader screen = new FxmlLoader();
		Pane view = screen.getPage("HomeScreen");
		System.out.println("Pane view: " + view);
		
		bpScreenView.setLeft(view);		
		System.out.println("Home Button klicked");
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
