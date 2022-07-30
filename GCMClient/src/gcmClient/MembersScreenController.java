package gcmClient;

import java.io.IOException;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.Initializable;

import gcmClasses.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class MembersScreenController  extends Dialog implements Initializable {
	@FXML 
	public TableView membersTableView;

	@FXML
	public Button editDetailsBtn;

	@FXML
	public Dialog dialog = new Dialog();

	@FXML
	private void handleEditDetailsBtn(ActionEvent event) throws IOException {
		FxmlLoader loader = new FxmlLoader();
		DialogPane dialogPane = FXMLLoader.load(getClass().getResource("/MembersDetailDialog.fxml"));

		dialog.setDialogPane(dialogPane);
		dialog.showAndWait();

		MemberFX memberFX = new MemberFX(new Member());

		/*
		Optional<ButtonType> r = new WeinDetailDialog(weinFX).showAndWait();
		if(r.isPresent() && r.get().getButtonData() == ButtonData.OK_DONE) {
			// neuer Wein wurde gespeichert, daher neue Weinliste vom Server holen
			//leseMemberliste();
			System.out.println("Aktuaisiere Member Liste");
		}
		 */		

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
