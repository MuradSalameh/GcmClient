package gcmClient;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import fxClasses.MemberFX;
import gcmClasses.Member;
import gcmClasses.Social;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import serviceFunctions.MemberServiceFunctions;
import serviceFunctions.SocialServiceFunctions;

public class MembersScreenController implements Initializable {

    @FXML
    private ObservableList<MemberFX> olMembers = FXCollections.observableArrayList();
    @FXML
    private AnchorPane membersAnchor;
    @FXML
    private TableView<MemberFX> membersTableView;
    @FXML
    private TableColumn<MemberFX, Integer> idColumn;
    @FXML
    private TableColumn<MemberFX, String> clanIdColumn;
    @FXML
    private TableColumn<MemberFX, String> clanNameColumn;
    @FXML
    private TableColumn<MemberFX, String> realNameColumn;
    @FXML
    private TableColumn<MemberFX, String> addressColumn;
    @FXML
    private TableColumn<MemberFX, String> addressPostcodeColumn;
    @FXML
    private TableColumn<MemberFX, String> addressCityColumn;
    @FXML
    private TableColumn<MemberFX, String> countryColumn;
    @FXML
    private TableColumn<MemberFX, String> emailColumn;
    @FXML
    private TableColumn<MemberFX, String> phoneNumberColumn;
    @FXML
    private TableColumn<MemberFX, LocalDate> birthdayColumn;

    @FXML
    public Button editDetailsBtn;
    @FXML
    public Button editDetailsBtn2;
    @FXML
    public Button addNewBtn;

    // edit member details button
    @FXML
    private void handleEditDetailsBtn(ActionEvent event) throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("MembersDetailDialog.fxml"));

	MemberFX member = membersTableView.getSelectionModel().getSelectedItem();

	if (member == null) {
	    return;
	}

	int id = member.getId();
	ControllerCommunicator cc = new ControllerCommunicator(id);

	DialogPane dialogPane = loader.load();

	Dialog dialog = new Dialog();
	dialog.setDialogPane(dialogPane);
	dialog.setResizable(true);

	MembersDetailsEditController mddc = loader.getController();

	ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

	dialog.getDialogPane().getButtonTypes().set(0, saveBtn);
	dialog.getDialogPane().getButtonTypes().set(1, cancelBtn);

	Optional<ButtonType> result = dialog.showAndWait();

	if (!result.isPresent()) {

	    // alert is exited, no button has been pressed.

	} else if (result.get() == saveBtn) {

	    Member m = mddc.updateMemberDetails();
	    int idMember = m.getId();
	    MemberServiceFunctions.updateMember(idMember, m);

	    membersTableView.getItems().clear();

	    readMembersList();

	    updateTable();
	    membersTableView.refresh();

	    Social soc = mddc.updateSocial();
	    int idSocial = soc.getId();
	    SocialServiceFunctions.updateSocial(idSocial, soc);

	} else if (result.get() == cancelBtn) {

	    System.out.println("Cancel Button Pressed");

	}
    }

    // delete member button
    @FXML
    private void handleDeleteBtn() {

	Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setTitle("WARNING - DELETING MEMBER");
	alert.setHeaderText("THIS CAN NOT BE UNDONE");
	alert.setContentText("DO YOU REALLY WANT TO DELETE THIS MEMBER?");

	MemberFX member = membersTableView.getSelectionModel().getSelectedItem();

	if (member == null) {
	    return;
	}

	Optional<ButtonType> result = alert.showAndWait();
	if (result.get() == ButtonType.OK) {

	    // get ID from item in table view

	    int id = member.getId();

	    // first delete connections to other objects, then delete from database
	    // MemberServiceFunctions.deleteRolesFromMember(id);
	    MemberServiceFunctions.deleteMemberFromRoles(id);
	    MemberServiceFunctions.deleteMemberFromGames(id);
	    MemberServiceFunctions.deleteMemberFromEvents(id);
	    MemberServiceFunctions.deleteMemberFromTeams(id);
	    MemberServiceFunctions.deleteMemberFromSocials(id);
	    MemberServiceFunctions.deleteMember(id);

	    // remove from Tableview
	    membersTableView.getItems().removeAll(membersTableView.getSelectionModel().getSelectedItem());

	    membersTableView.refresh();
	}
    }

    // add new member button
    @FXML
    public void handleAddNewBtn(ActionEvent t) throws IOException {

	FXMLLoader loader = new FXMLLoader(getClass().getResource("MembersAddNewDialog.fxml"));
	DialogPane dialogPane = loader.load();

	Dialog dialog = new Dialog();
	dialog.setDialogPane(dialogPane);
	dialog.setResizable(true);

	MembersAddNewDialogController mandc = loader.getController();

	ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

	dialog.getDialogPane().getButtonTypes().set(0, saveBtn);
	dialog.getDialogPane().getButtonTypes().set(1, cancelBtn);

	Optional<ButtonType> result = dialog.showAndWait();

	if (!result.isPresent()) {

	    // alert is exited, no button has been pressed.

	} else if (result.get() == saveBtn) {

	    Member m = mandc.updateMemberDetails();
	    int idMember = m.getId();
	    MemberServiceFunctions.addMember(m);

	    membersTableView.getItems().clear();

	    readMembersList();

	    updateTable();
	    membersTableView.refresh();

	} else if (result.get() == cancelBtn) {

	    System.out.println("Cancel Button Pressed");

	}
    }

    // update membersTableView
    public void updateTable() {
	// load Data
	if (membersTableView != null) {
	    membersTableView.getItems().addAll(olMembers);
	}
    }

    // read list of all members
    public void readMembersList() {
	olMembers.clear();

	List<Member> xmlMembers = new ArrayList<Member>();
	xmlMembers = MemberServiceFunctions.getMembers();

	for (Member einM : xmlMembers) {
	    olMembers.add(new MemberFX(einM));
	}
    }

    // initialize membersTableView columns
    public void initializeColumns() {

	if (idColumn != null) {
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
	}
    }

//initialize methds when MembersScreen.fxml is loaded
    @Override
    public void initialize(URL location, ResourceBundle resources) {
	readMembersList();
	initializeColumns();
	updateTable();
    }
}
