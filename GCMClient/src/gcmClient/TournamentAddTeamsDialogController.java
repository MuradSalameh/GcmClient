package gcmClient;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fxClasses.TeamFX;
import gcmClasses.Team;
import gcmClasses.Tournament;
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
import serviceFunctions.TeamServiceFunctions;
import serviceFunctions.TournamentServiceFunctions;

public class TournamentAddTeamsDialogController extends Dialog<ButtonType> implements Initializable {

    private int ccId = ControllerCommunicator.getId();

    @FXML
    final DialogPane dialogPane = getDialogPane();
    @FXML
    private Dialog dialog;
    @FXML
    private BorderPane tournamentEditBp;

    @FXML
    ButtonType cancelBtn = new ButtonType("Cancellus", ButtonData.CANCEL_CLOSE);
    @FXML
    ButtonType saveBtn = new ButtonType("Speichii", ButtonData.OK_DONE);

    // Teams Buttons -----

    @FXML
    private Button gAddBtn;
    @FXML
    private Button gRemoveBtn;

    // get Tournament from DB -----
    public Tournament loadTournament() {

	Tournament tournament = TournamentServiceFunctions.getTournament(ccId);
	return tournament;
    }

    // Tournament Teams Table
    private ObservableList<TeamFX> olTeams = FXCollections.observableArrayList();

    @FXML
    private TableView<TeamFX> teamsTableView;
    @FXML
    private TableColumn<TeamFX, Integer> teamIdColumn;
    @FXML
    private TableColumn<TeamFX, String> teamNameColumn;
    @FXML
    private TableColumn<TeamFX, String> teamDescriptionColumn;

    // initialize teamsTableView columns
    public void initializeTeamsColumns() {

	if (teamIdColumn != null) {
	    teamIdColumn.setCellValueFactory(new PropertyValueFactory<TeamFX, Integer>("id"));
	    teamNameColumn.setCellValueFactory(new PropertyValueFactory<TeamFX, String>("teamName"));
	    teamDescriptionColumn.setCellValueFactory(new PropertyValueFactory<TeamFX, String>("teamDescription"));

	}
    }

    // update teamsTableView
    public void updateTeamsTable() {
	// load Data
	if (teamsTableView != null) {
	    teamsTableView.getItems().addAll(olTeams);
	}
    }

    // read list of all teams by tournament id
    public void readTeamsList() {
	olTeams.clear();

	List<Team> xmlTeams = new ArrayList<Team>();
	xmlTeams = TeamServiceFunctions.getTeamsByTournamentId(ccId);

	if (xmlTeams != null) {
	    for (Team einT : xmlTeams) {
		olTeams.add(new TeamFX(einT));

	    }
	}

    }

    // -------------------------------------------------------------
    // All Available Teams Table -----------------------------------
    // -------------------------------------------------------------

    private ObservableList<TeamFX> olTeams1 = FXCollections.observableArrayList();

    @FXML
    private TableView<TeamFX> teamsTableView1;
    @FXML
    private TableColumn<TeamFX, Integer> teamIdColumn1;
    @FXML
    private TableColumn<TeamFX, String> teamNameColumn1;
    @FXML
    private TableColumn<TeamFX, String> teamDescriptionColumn1;

    // initialize teamsTableView1 columns
    public void initializeTeamsColumns1() {

	if (teamIdColumn1 != null) {
	    teamIdColumn1.setCellValueFactory(new PropertyValueFactory<TeamFX, Integer>("id"));
	    teamNameColumn1.setCellValueFactory(new PropertyValueFactory<TeamFX, String>("teamName"));
	    teamDescriptionColumn1.setCellValueFactory(new PropertyValueFactory<TeamFX, String>("teamDescription"));
	}
    }

    // update teamsTableView1
    public void updateTeamsTable1() {
	// load Data
	if (teamsTableView1 != null) {
	    teamsTableView1.getItems().addAll(olTeams1);
	}
    }

    // read list of all teams
    public void readTeamsList1() {
	olTeams1.clear();

	List<Team> xmlTeams1 = new ArrayList<Team>();
	xmlTeams1 = TeamServiceFunctions.getTeams();

	if (xmlTeams1 != null) {
	    for (Team einT : xmlTeams1) {
		olTeams1.add(new TeamFX(einT));

	    }
	}

    }

    // get selceted team from allavailable teams in teamsTableView1
    public Team getSelectedTeamFromAvailableTeams() {

	TeamFX getTeam = teamsTableView1.getSelectionModel().getSelectedItem();
	if (teamsTableView1.getSelectionModel().getSelectedItem() != null) {
	    int id = getTeam.getId();
	    Team team = TeamServiceFunctions.getTeam(id);
	    return team;
	} else {
	    return null;
	}
    }

    // add team from all available teams in teamsTableView1 to tournament in
    // teamsTableView
    public void handleTeamAddBtn(ActionEvent e) {

	TeamFX selectedTeam = teamsTableView1.getSelectionModel().getSelectedItem();

	if (selectedTeam == null) {
	    return;
	}

	int id = selectedTeam.getId();

	if (containsItem(teamsTableView, selectedTeam)) {

	    System.out.println("Team already Exists");

	} else {
	    System.out.println("Team doesnt Exist");
	    TeamServiceFunctions.addTeamToTournament(id, ccId);
	    teamsTableView.getItems().clear();

	    readTeamsList();

	    updateTeamsTable();
	    teamsTableView.refresh();
	}
    }

    // Method to check if table already contains specific Item
    public static boolean containsItem(TableView<TeamFX> teamsTableView, TeamFX teamFX) {
	for (TeamFX item : teamsTableView.getItems()) {
	    if (item.getId() == teamFX.getId()) {
		return true;
	    }
	}
	return false;
    }

    // remove team from tournament in teamsTableView
    @FXML
    public void handleTeamDeleteBtn() {

	TeamFX Team = teamsTableView.getSelectionModel().getSelectedItem();

	if (Team == null) {
	    return;
	}

	int id = Team.getId();
	// delete from database

	TeamServiceFunctions.deleteTeamFromTournament(id, ccId);

	// remove from Tableview
	teamsTableView.getItems().removeAll(teamsTableView.getSelectionModel().getSelectedItem());

	teamsTableView.getItems().clear();

	readTeamsList();

	updateTeamsTable();
	teamsTableView.refresh();
    }

    // initialize methods when TournamentAddTeamsDialog.fxml is loaded
    @Override
    public void initialize(URL location, ResourceBundle resources) {

	// Tournament Teams Table
	readTeamsList();
	initializeTeamsColumns();
	updateTeamsTable();

	// All Available Teams Table
	readTeamsList1();
	initializeTeamsColumns1();
	updateTeamsTable1();
    }
}
