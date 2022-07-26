package gcmClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fxClasses.PartnerFX;
import gcmClasses.Partner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
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
    @FXML
    private AnchorPane partnersAnchor;
    @FXML
    private TableView<PartnerFX> partnersTableView;
    @FXML
    private TableColumn<PartnerFX, Integer> idColumn;
    @FXML
    private TableColumn<PartnerFX, String> companyNameColumn;
    @FXML
    private TableColumn<PartnerFX, String> contactPersonNameColumn;
    @FXML
    private TableColumn<PartnerFX, String> contactPersonPhoneColumn;
    @FXML
    private TableColumn<PartnerFX, String> contactPersonMailColumn;
    @FXML
    private TableColumn<PartnerFX, String> firstNameColumn;
    @FXML
    private TableColumn<PartnerFX, String> lastNameColumn;
    @FXML
    private TableColumn<PartnerFX, String> adressStreetColumn;
    @FXML
    private TableColumn<PartnerFX, String> adressNumberColumn;
    @FXML
    private TableColumn<PartnerFX, String> adressPostCodeColumn;
    @FXML
    private TableColumn<PartnerFX, String> adressCityColumn;
    @FXML
    private TableColumn<PartnerFX, String> countryColumn;
    @FXML
    private TableColumn<PartnerFX, String> emailColumn;
    @FXML
    private TableColumn<PartnerFX, String> phoneNumberColumn;

    @FXML
    public Button editDetailsBtn;
    @FXML
    public Button addNewBtn;

    // add new partner button
    @FXML
    private void handleAddNewBtn(ActionEvent event) throws IOException {

	FXMLLoader loader = new FXMLLoader(getClass().getResource("PartnerAddNewDialog.fxml"));
	DialogPane dialogPane = loader.load();

	Dialog dialog = new Dialog();
	dialog.setDialogPane(dialogPane);
	dialog.setResizable(true);

	PartnerAddNewDialogController edand = loader.getController();

	ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

	dialog.getDialogPane().getButtonTypes().set(0, saveBtn);
	dialog.getDialogPane().getButtonTypes().set(1, cancelBtn);

	Optional<ButtonType> result = dialog.showAndWait();

	if (!result.isPresent()) {

	    // alert is exited, no button has been pressed.

	} else if (result.get() == saveBtn) {

	    Partner m = edand.updatePartner();
	    int idPartner = m.getId();
	    PartnerServiceFunctions.addPartner(m);

	    partnersTableView.getItems().clear();

	    readPartnersList();

	    updateTable();
	    partnersTableView.refresh();

	} else if (result.get() == cancelBtn) {
	    System.out.println("Cancel Button Pressed");
	}

    }

    // edit partner details button
    @FXML
    private void handleEditDetailsBtn(ActionEvent event) throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("PartnerDetailDialog.fxml"));

	PartnerFX getPartner = partnersTableView.getSelectionModel().getSelectedItem();

	if (getPartner == null) {
	    return;
	}

	int id = getPartner.getId();
	ControllerCommunicator cc = new ControllerCommunicator(id);

	DialogPane dialogPane = loader.load();

	Dialog dialog = new Dialog();
	dialog.setDialogPane(dialogPane);
	dialog.setResizable(true);

	PartnerDetailDialogController eddc = loader.getController();

	ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

	dialog.getDialogPane().getButtonTypes().set(0, saveBtn);
	dialog.getDialogPane().getButtonTypes().set(1, cancelBtn);

	Optional<ButtonType> result = dialog.showAndWait();

	if (!result.isPresent()) {

	    // alert is exited, no button has been pressed.

	} else if (result.get() == saveBtn) {

	    Partner m = eddc.updatePartner();
	    int idPartner = m.getId();
	    PartnerServiceFunctions.updatePartner(idPartner, m);

	    partnersTableView.getItems().clear();

	    readPartnersList();

	    updateTable();
	    partnersTableView.refresh();

	} else if (result.get() == cancelBtn) {
	    System.out.println("Cancel Button Pressed");
	}
    }

    // delete partner button
    @FXML
    private void handleDeleteBtn() {
	Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setTitle("WARNING - DELETING PARTNER");
	alert.setHeaderText("THIS CAN NOT BE UNDONE");
	alert.setContentText("DO YOU REALLY WANT TO DELETE THIS PARTNER?");

	// get ID from item in table view
	PartnerFX partner = partnersTableView.getSelectionModel().getSelectedItem();

	if (partner == null) {
	    return;
	}

	Optional<ButtonType> result = alert.showAndWait();
	if (result.get() == ButtonType.OK) {

	    int id = partner.getId();
	    // delete from database
	    PartnerServiceFunctions.deletePartner(id);

	    // remove from Tableview
	    partnersTableView.getItems().removeAll(partnersTableView.getSelectionModel().getSelectedItem());

	    partnersTableView.refresh();
	}
    }

    // update partnersTableView
    public void updateTable() {
	// load Data
	if (partnersTableView != null) {
	    partnersTableView.getItems().addAll(olPartners);
	}
    }

    // read list of all partners
    public void readPartnersList() {
	olPartners.clear();

	List<Partner> xmlPartners = new ArrayList<Partner>();
	xmlPartners = PartnerServiceFunctions.getPartners();

	if (xmlPartners != null) {
	    for (Partner einM : xmlPartners) {
		olPartners.add(new PartnerFX(einM));
	    }
	}

    }

    // initialize partnersTableView columns
    public void initializeColumns() {

	if (idColumn != null) {
	    idColumn.setCellValueFactory(new PropertyValueFactory<PartnerFX, Integer>("id"));
	    companyNameColumn.setCellValueFactory(new PropertyValueFactory<PartnerFX, String>("companyName"));
	    contactPersonNameColumn
		    .setCellValueFactory(new PropertyValueFactory<PartnerFX, String>("contactPersonName"));
	    contactPersonPhoneColumn
		    .setCellValueFactory(new PropertyValueFactory<PartnerFX, String>("contactPersonPhone"));
	    contactPersonMailColumn
		    .setCellValueFactory(new PropertyValueFactory<PartnerFX, String>("contactPersonMail"));
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

    // initialize methods when PartnersScreen.fxml is loaded
    public void initialize() {
	readPartnersList();
	initializeColumns();
	updateTable();
    }

}
