package gcmClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import fxClasses.PartnerFX;
import gcmClasses.Partner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import serviceFunctions.PartnerServiceFunctions;

public class PartnerScreenController {

	@FXML
	private ObservableList<PartnerFX> olPartners = FXCollections.observableArrayList();
	@FXML private AnchorPane partnersAnchor;
	@FXML private TableView<PartnerFX> partnersTableView;
	@FXML private TableColumn<PartnerFX,Integer> idColumn;	
	@FXML private TableColumn<PartnerFX,String> companyNameColumn;
	@FXML private TableColumn<PartnerFX,String> contactPersonNameColumn;
	@FXML private TableColumn<PartnerFX,String> contactPersonPhoneColumn;
	@FXML private TableColumn<PartnerFX,String> contactPersonMailColumn;
	@FXML private TableColumn<PartnerFX,String> firstNameColumn;
	@FXML private TableColumn<PartnerFX,String> lastNameColumn;
	@FXML private TableColumn<PartnerFX,String> adressStreetColumn;
	@FXML private TableColumn<PartnerFX,String> adressNumberColumn;
	@FXML private TableColumn<PartnerFX,String> adressPostCodeColumn;
	@FXML private TableColumn<PartnerFX,String> adressCityColumn;
	@FXML private TableColumn<PartnerFX,String> countryColumn;
	@FXML private TableColumn<PartnerFX,String> emailColumn;
	@FXML private TableColumn<PartnerFX,String> phoneNumberColumn;




	@FXML
	public Button editDetailsBtn;

	
	@FXML
	private void handleEditDetailsBtn(ActionEvent event) throws IOException {
		FxmlLoader loader = new FxmlLoader();
		DialogPane dialogPane = FXMLLoader.load(getClass().getResource("PartnersDetailDialog.fxml"));
		Dialog dialog = new Dialog();
		dialog.setDialogPane(dialogPane);
		dialog.showAndWait();

		/*
		Optional<ButtonType> r = new WeinDetailDialog(PartnerFX).showAndWait();
		if(r.isPresent() && r.get().getButtonData() == ButtonData.OK_DONE) {
			// neuer Partner wurde gespeichert, daher neue Weinliste vom Server holen
			//lesePartnerliste();
			System.out.println("Aktualisiere Partner Liste");
		}
		 */	
		System.out.println("PartnersDetailsDialog Button klicked");
	}



	public void updateTable() {		
		// load Data
		if(partnersTableView != null) {
				partnersTableView.getItems().addAll(olPartners);
		}
	}



	public void readPartnersList() {
		olPartners.clear();

		List<Partner> xmlPartners = new ArrayList<Partner>();
		xmlPartners = PartnerServiceFunctions.getPartners();			

		for(Partner einM : xmlPartners) {
			olPartners.add(new PartnerFX(einM));
			System.out.println("CLIENT------------" + "\n" + einM);
		}
	}


	public  void initializeColumns() {
		
		if(idColumn != null) {
			idColumn.setCellValueFactory(new PropertyValueFactory<PartnerFX, Integer>("id"));
			companyNameColumn.setCellValueFactory(new PropertyValueFactory<PartnerFX, String>("companyName"));
			contactPersonNameColumn.setCellValueFactory(new PropertyValueFactory<PartnerFX, String>("contactPersonName"));
			contactPersonPhoneColumn.setCellValueFactory(new PropertyValueFactory<PartnerFX, String>("contactPersonPhone"));
			contactPersonMailColumn.setCellValueFactory(new PropertyValueFactory<PartnerFX, String>("contactPersonMail"));
			firstNameColumn.setCellValueFactory(new PropertyValueFactory<PartnerFX, String>("firstName"));
			lastNameColumn.setCellValueFactory(new PropertyValueFactory<PartnerFX, String>("lastName"));
			adressStreetColumn.setCellValueFactory(new PropertyValueFactory<PartnerFX, String>("adressStreet"));
			adressNumberColumn.setCellValueFactory(new PropertyValueFactory<PartnerFX, String>("adressNumber"));
			adressPostCodeColumn.setCellValueFactory(new PropertyValueFactory<PartnerFX, String>("adressPostCode"));
			adressCityColumn.setCellValueFactory(new PropertyValueFactory<PartnerFX, String>("adressCity"));
			countryColumn.setCellValueFactory(new PropertyValueFactory<PartnerFX, String>("country"));
			emailColumn.setCellValueFactory(new PropertyValueFactory<PartnerFX, String>("email"));
			phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<PartnerFX, String>("phoneNumber"));
		
		}
	}


		
	public void initialize() {
		readPartnersList();
		initializeColumns();		
		updateTable();
	}


}