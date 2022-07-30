package gcmClient;

import java.io.IOException;
import java.util.Optional;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.Initializable;

import client.ButtonType;
import client.Wein;
import client.WeinDetailDialog;
import client.WeinFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

public class MembersScreenController implements Initializable{
	@FXML 
	public TableView membersTableView;
	
	@FXML
	public Button editDetailsBtn;
	
	
	@FXML
	private void handleEditDetailsBtn(ActionEvent event) throws IOException {
		WeinFX weinFX = new WeinFX(new Wein());
		Optional<ButtonType> r = new WeinDetailDialog(weinFX).showAndWait();
		if(r.isPresent() && r.get().getButtonData() == ButtonData.OK_DONE) {
			// neuer Wein wurde gespeichert, daher neue Weinliste vom Server holen
			leseWeinliste();
		}
		
		FxmlLoader screen = new FxmlLoader();
		Pane view = screen.getPage("MembersDetailDialog");
		System.out.println("Pane view: " + view);
		
		bpScreenView.setLeft(view);		
		System.out.println("MembersDetailsDialog Button klicked");
		
	}
	

	@Override
	public Initializable preInitialize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientConfig getConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
