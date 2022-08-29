package gcmClient;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fxClasses.MemberFX;
import gcmClasses.Member;
import gcmClasses.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import serviceFunctions.MemberServiceFunctions;
import serviceFunctions.TeamServiceFunctions;

public class TeamAddMembersDialogController extends Dialog<ButtonType> implements Initializable {

    private int ccId = ControllerCommunicator.getId();


    @FXML
    final DialogPane dialogPane = getDialogPane();
    @FXML
    private Dialog dialog;
    @FXML
    private BorderPane memberEditBp;

    @FXML
    ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
    @FXML
    ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

    // Members Buttons -----

    @FXML
    private Button gAddBtn;
    @FXML
    private Button gRemoveBtn;

    // get Member from DB -----
    public Team loadTeam() {

	Team team = TeamServiceFunctions.getTeam(ccId);
	return team;
    }

    @FXML
    private TableView<MemberFX> membersTableView;
    @FXML
    private TableColumn<MemberFX, Integer> membersIdColumn;
    @FXML
    private TableColumn<MemberFX, String> memberClanNameColumn;
    @FXML
    private TableColumn<MemberFX, String> memberClanIdColumn;

    public void initializeMembersColumns() {

	if (membersIdColumn != null) {
	    membersIdColumn.setCellValueFactory(new PropertyValueFactory<MemberFX, Integer>("id"));
	    memberClanNameColumn.setCellValueFactory(new PropertyValueFactory<MemberFX, String>("clanName"));
	    memberClanIdColumn.setCellValueFactory(new PropertyValueFactory<MemberFX, String>("clanId"));

	}
    }

    public void updateMembersTable() {
	// load Data
	if (membersTableView != null) {
	    membersTableView.getItems().addAll(olMembers);
	}
    }

    // Member Members Table
    private ObservableList<MemberFX> olMembers = FXCollections.observableArrayList();

    public void readMembersList() {
	olMembers.clear();

	List<Member> xmlMembers = new ArrayList<Member>();

	xmlMembers = MemberServiceFunctions.getMembersByTeamId(ccId);

	if(xmlMembers != null) {
	    for (Member einT : xmlMembers) {
		olMembers.add(new MemberFX(einT));

	    }
	}


    }

    // -------------------------------------------------------------
    // All Available Members Table -----------------------------------
    // -------------------------------------------------------------

    // Member Members Table
    private ObservableList<MemberFX> olMembers1 = FXCollections.observableArrayList();

    @FXML
    private TableView<MemberFX> membersTableView1;
    @FXML
    private TableColumn<MemberFX, Integer> membersIdColumn1;
    @FXML
    private TableColumn<MemberFX, String> memberClanNameColumn1;
    @FXML
    private TableColumn<MemberFX, String> memberClanIdColumn1;

    public void initializeMembersColumns1() {

	if (membersIdColumn1 != null) {
	    membersIdColumn1.setCellValueFactory(new PropertyValueFactory<MemberFX, Integer>("id"));
	    memberClanNameColumn1.setCellValueFactory(new PropertyValueFactory<MemberFX, String>("clanName"));
	    memberClanIdColumn1.setCellValueFactory(new PropertyValueFactory<MemberFX, String>("clanId"));

	}
    }

    public void updateMembersTable1() {
	// load Data
	if (membersTableView1 != null) {
	    membersTableView1.getItems().addAll(olMembers1);
	}
    }

    public void readMembersList1() {
	olMembers1.clear();

	List<Member> xmlMembers1 = new ArrayList<Member>();
	xmlMembers1 = MemberServiceFunctions.getMembers();

	if(xmlMembers1 != null) {
	    for (Member einT : xmlMembers1) {
		olMembers1.add(new MemberFX(einT));

	    }
	}

    }

    public Member getSelectedMemberFromAvailableMembers() {

	MemberFX getMember = membersTableView1.getSelectionModel().getSelectedItem();
	if (membersTableView1.getSelectionModel().getSelectedItem() != null) {
	    int id = getMember.getId();
	    Member member = MemberServiceFunctions.getMember(id);
	    return member;
	} else {
	    return null;
	}
    }

    public void handleMemberAddBtn(ActionEvent e) {
	int id = getSelectedMemberFromAvailableMembers().getId();
	MemberFX selectedMember = membersTableView1.getSelectionModel().getSelectedItem();

	if (containsItem(membersTableView, selectedMember)) {

	    System.out.println("Member already Exists");

	} else {
	    System.out.println("Member doesnt Exist");
	    MemberServiceFunctions.addMemberToTeam(id, ccId);
	    membersTableView.getItems().clear();

	    readMembersList();

	    updateMembersTable();
	    membersTableView.refresh();
	}
    }

    // Method to check if Table already contains specific Item
    public static boolean containsItem(TableView<MemberFX> membersTableView, MemberFX memberFX) {
	for (MemberFX item : membersTableView.getItems()) {
	    if (item.getId() == memberFX.getId()) {
		return true;
	    }
	}
	return false;
    }

    @FXML
    public void handleMemberDeleteBtn() {

	MemberFX Member = membersTableView.getSelectionModel().getSelectedItem();
	int id = Member.getId();
	// delete from database

	MemberServiceFunctions.deleteMemberFromTeam(id, ccId);

	// remove from Tableview
	membersTableView.getItems().removeAll(membersTableView.getSelectionModel().getSelectedItem());

	membersTableView.getItems().clear();

	readMembersList();

	updateMembersTable();
	membersTableView.refresh();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

	// Member Members Table
	readMembersList();
	initializeMembersColumns();
	updateMembersTable();

	// All Available Members Table
	readMembersList1();
	initializeMembersColumns1();
	updateMembersTable1();
    }
}
