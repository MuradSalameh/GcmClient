package gcmClient;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import fxClasses.MemberFX;
import gcmClasses.Member;
import gcmClasses.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import serviceFunctions.MemberServiceFunctions;
import serviceFunctions.TeamServiceFunctions;

public class TeamsDetailDialogController extends Dialog<ButtonType> implements Initializable {

	private int ccId = ControllerCommunicator.getId();

	@FXML
	final DialogPane dialogPane = getDialogPane();
	@FXML
	private Dialog dialog;
	@FXML
	private BorderPane teamEditBp;
	@FXML
	private Label idLabel;
	@FXML
	private TextField teamNameTF;
	@FXML
	private TextField teamDescriptionTF;

	@FXML
	ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	@FXML
	ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

	@FXML
	Button addMemberBtn;
	@FXML
	Button removeMemberBtn;

	@FXML
	private TableView<MemberFX> membersTableView;
	@FXML
	private TableColumn<MemberFX, Integer> idColumn;
	@FXML
	private TableColumn<MemberFX, String> clanIdColumn;
	@FXML
	private TableColumn<MemberFX, String> clanNameColumn;

	public Team loadTeam() {

		Team event = TeamServiceFunctions.getTeam(ccId);
		return event;
	}

	public Team getSelectedTeam() {

		if (loadTeam() != null) {
			Team event = loadTeam();
			return event;
		} else {
			Team newTeam = new Team("", // team name
					"", // desc
					null // members teams
			); // team tournaments teams

			return newTeam;
		}
	}

	public void initializeTextFields() {
		Team team = loadTeam();

		idLabel.setText(String.valueOf(ccId));

		// Team TextFields
		teamNameTF.setText(team.getTeamName());
		teamDescriptionTF.setText(team.getTeamDescription());

		teamNameTF.setPromptText("Enter Team Name");
		teamDescriptionTF.setPromptText("Enter Description");

	}

	public Team updateTeam() {
		Team team = loadTeam();

		team.setTeamName(teamNameTF.getText());
		team.setTeamDescription(teamDescriptionTF.getText());

		return team;
	}


	@FXML
	private ObservableList<MemberFX> olMembers = FXCollections.observableArrayList();

	
	public void readMembersList() {
		olMembers.clear();

		List<Member> xmlMembers = new ArrayList<Member>();
		
		
		    xmlMembers = MemberServiceFunctions.getMembersByTeamId(ccId);
		

		if(xmlMembers != null) {
		  for (Member einM : xmlMembers) {
			olMembers.add(new MemberFX(einM));

		}  
		}
		
	}

	public void initializeColumns() {

		if (idColumn != null) {
			idColumn.setCellValueFactory(new PropertyValueFactory<MemberFX, Integer>("id"));
			clanNameColumn.setCellValueFactory(new PropertyValueFactory<MemberFX, String>("clanName"));
			clanIdColumn.setCellValueFactory(new PropertyValueFactory<MemberFX, String>("clanId"));
		}
	}

	public void updateTable() {
		// load Data
		if (membersTableView != null) {
			membersTableView.getItems().addAll(olMembers);
		}
	}

	public void handleAddMembersBtn() throws IOException {

		FXMLLoader addMemberloader = new FXMLLoader(getClass().getResource("TeamAddMembersDetailDialog.fxml"));

		DialogPane dialogPaneAddMember = addMemberloader.load();

		Dialog dialog = new Dialog();
		dialog.setDialogPane(dialogPaneAddMember);
		dialog.setResizable(true);

		TeamAddMembersDialogController tamdc = addMemberloader.getController();

		ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

		dialog.getDialogPane().getButtonTypes().set(0, saveBtn);
		dialog.getDialogPane().getButtonTypes().set(1, cancelBtn);

		Optional<ButtonType> result = dialog.showAndWait();

		if (!result.isPresent()) {

			// alert is exited, no button has been pressed.

		} else if (result.get() == saveBtn) {

			membersTableView.getItems().clear();
			readMembersList();			
			updateTable();
			membersTableView.refresh();
		} else if (result.get() == cancelBtn) {

	

		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// loadTeam();
		initializeTextFields();
		readMembersList();
		initializeColumns();
		updateTable();
	}
}
