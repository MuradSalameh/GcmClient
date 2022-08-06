package gcmClient;

import java.io.IOException;
import java.time.LocalDate;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;


import fxClasses.MemberFX;
import gcmClasses.Member;
import gcmClasses.MemberList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MembersScreenController  extends Dialog implements Initializable {
	
	
	private ObservableList<MemberFX> olMembers = FXCollections.observableArrayList();
	@FXML private AnchorPane membersAnchor;
	@FXML private TableView<MemberFX> membersTableView;
	
	
	@FXML private TableColumn<MemberFX,Integer> idColumn;	
	@FXML private TableColumn<MemberFX,String> clanIdColumn;
	@FXML private TableColumn<MemberFX,String> clanNameColumn;
	@FXML private TableColumn<MemberFX,String> realNameColumn;
	@FXML private TableColumn<MemberFX,String> addressColumn;
	@FXML private TableColumn<MemberFX,String> addressPostcodeColumn;
	@FXML private TableColumn<MemberFX,String> addressCityColumn;
	@FXML private TableColumn<MemberFX,String> countryColumn;
	@FXML private TableColumn<MemberFX,String> emailColumn;
	@FXML private TableColumn<MemberFX,String> phoneNumberColumn;
	@FXML private TableColumn<MemberFX,LocalDate> birthdayColumn;

	@FXML
	public Button editDetailsBtn;

	public Dialog dialog = new Dialog();
	
	

	@FXML
	private void handleEditDetailsBtn(ActionEvent event) throws IOException {
		FxmlLoader loader = new FxmlLoader();
		DialogPane dialogPane = FXMLLoader.load(getClass().getResource("/MembersDetailDialog.fxml"));

		dialog.setDialogPane(dialogPane);
		dialog.showAndWait();

		MemberFX memberFX = new MemberFX(new Member());

		/*
		Optional<ButtonType> r = new WeinDetailDialog(MemberFX).showAndWait();
		if(r.isPresent() && r.get().getButtonData() == ButtonData.OK_DONE) {
			// neuer Member wurde gespeichert, daher neue Weinliste vom Server holen
			//leseMemberliste();
			System.out.println("Aktualisiere Member Liste");
		}
		 */		

		System.out.println("MembersDetailsDialog Button klicked");

	}


	@Override
	public Initializable preInitialize() {
		
		// initialize columns
		idColumn.setCellValueFactory(new PropertyValueFactory<MemberFX, Integer>("id"));
		clanNameColumn.setCellValueFactory(new PropertyValueFactory<MemberFX, String>("clanName"));
		clanIdColumn.setCellValueFactory(new PropertyValueFactory<MemberFX, String>("clanId"));
		realNameColumn.setCellValueFactory(new PropertyValueFactory<MemberFX, String>("realName"));
		addressColumn.setCellValueFactory(new PropertyValueFactory<MemberFX, String>("address"));
		addressPostcodeColumn.setCellValueFactory(new PropertyValueFactory<MemberFX, String>("addressPostCode"));
		addressCityColumn.setCellValueFactory(new PropertyValueFactory<MemberFX, String>("addressCity"));
		countryColumn.setCellValueFactory(new PropertyValueFactory<MemberFX, String>("country"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<MemberFX, String>("email"));
		phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<MemberFX, String>("phoneNumber"));
		birthdayColumn.setCellValueFactory(new PropertyValueFactory<MemberFX, LocalDate>("birthday"));
		
		
		// load Data
		membersTableView.setItems(olMembers);
		
		return null;
	}

	@Override
	public ClientConfig getConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private void readMembersList() {
		olMembers.clear();
		ServiceFunctionsReturnData<MemberList> sfr = MemberServiceFunctions.getMembers();
		if(sfr.isRc()) {
			MemberList ml = sfr.getData();
			if(ml.getMembers() != null) {
				for(Member einM : ml.getMembers()) {
					olMembers.add(new MemberFX(einM));
				}
			}
		}
		else {
			new Alert(AlertType.ERROR, sfr.getMeldung()).showAndWait();
		}


	}


}
