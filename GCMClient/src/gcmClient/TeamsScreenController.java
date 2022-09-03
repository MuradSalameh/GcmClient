package gcmClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fxClasses.TeamFX;
import gcmClasses.Team;
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
import serviceFunctions.TeamServiceFunctions;

public class TeamsScreenController {

    @FXML
    private ObservableList<TeamFX> olTeams = FXCollections.observableArrayList();
    @FXML
    private AnchorPane teamsAnchor;
    @FXML
    private TableView<TeamFX> teamsTableView;
    @FXML
    private TableColumn<TeamFX, Integer> idColumn;
    @FXML
    private TableColumn<TeamFX, String> teamNameColumn;
    @FXML
    private TableColumn<TeamFX, String> teamDescriptionColumn;

    @FXML
    public Button editDetailsBtn;
    public Button addNewBtn;

    @FXML
    private void handleAddNewBtn(ActionEvent team) throws IOException {

	FXMLLoader loader = new FXMLLoader(getClass().getResource("TeamsAddNewDetailDialog.fxml"));
	DialogPane dialogPane = loader.load();

	Dialog dialog = new Dialog();
	dialog.setDialogPane(dialogPane);
	dialog.setResizable(true);

	TeamsAddNewDetailDialogController tanddc = loader.getController();

	ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

	dialog.getDialogPane().getButtonTypes().set(0, saveBtn);
	dialog.getDialogPane().getButtonTypes().set(1, cancelBtn);

	Optional<ButtonType> result = dialog.showAndWait();

	if (!result.isPresent()) {

	    // alert is exited, no button has been pressed.

	} else if (result.get() == saveBtn) {

	    Team m = tanddc.updateTeam();
	    int idTeam = m.getId();
	    TeamServiceFunctions.addTeam(m);

	    teamsTableView.getItems().clear();			
	    readTeamsList();			
	    updateTable();
	    teamsTableView.refresh();

	} else if (result.get() == cancelBtn) {

	}
    }

    @FXML
    private void handleEditDetailsBtn(ActionEvent event) throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("TeamsDetailDialog.fxml"));

	TeamFX team = teamsTableView.getSelectionModel().getSelectedItem();

	if (team == null) {
	    return;
	}

	int id = team.getId();
	ControllerCommunicator cc = new ControllerCommunicator(id);

	DialogPane dialogPane = loader.load();
	Dialog dialog = new Dialog();
	dialog.setDialogPane(dialogPane);
	dialog.setResizable(true);

	TeamsDetailDialogController tddc = loader.getController();

	ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

	System.out.println("SHOW AND WAIT");	

	dialog.getDialogPane().getButtonTypes().set(0, saveBtn);
	dialog.getDialogPane().getButtonTypes().set(1, cancelBtn);

	Optional<ButtonType> result = dialog.showAndWait();

	if (!result.isPresent()) {

	    // alert is exited, no button has been pressed.

	} else if (result.get() == saveBtn) {

	    Team m = tddc.updateTeam();
	    int idTeam = m.getId();
	    TeamServiceFunctions.updateTeam(idTeam, m);

	    teamsTableView.getItems().clear();

	    readTeamsList();

	    updateTable();
	    teamsTableView.refresh();

	} else if (result.get() == cancelBtn) {

	}
    }

    @FXML
    private void handleDeleteBtn() {
	Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setTitle("WARNING - DELETING TEAM");
	alert.setHeaderText("THIS CAN NOT BE UNDONE");
	alert.setContentText("DO YOU REALLY WANT TO DELETE THIS TEAM?");

	TeamFX team = teamsTableView.getSelectionModel().getSelectedItem();
	if (team == null) {
	    return;
	}

	Optional<ButtonType> result = alert.showAndWait();
	if (result.get() == ButtonType.OK) {

	    // get ID from item in table view

	    int id = team.getId();
	    // delete from database
	    TeamServiceFunctions.deleteTeamFromMember(id);
	    TeamServiceFunctions.deleteTeamFromTournaments(id);
	    TeamServiceFunctions.deleteTeam(id);

	    // remove from Tableview
	    teamsTableView.getItems().removeAll(teamsTableView.getSelectionModel().getSelectedItem());

	    teamsTableView.refresh();
	}
    }

    public void updateTable() {
	// load Data
	if (teamsTableView != null) {
	    teamsTableView.getItems().addAll(olTeams);
	}
    }

    public void readTeamsList() {
	olTeams.clear();

	List<Team> xmlTeams = new ArrayList<Team>();
	xmlTeams = TeamServiceFunctions.getTeams();
	if(xmlTeams != null) {
	    for (Team einM : xmlTeams) {
		olTeams.add(new TeamFX(einM));

	    }  
	}

    }

    public void initializeColumns() {

	if (idColumn != null) {
	    idColumn.setCellValueFactory(new PropertyValueFactory<TeamFX, Integer>("id"));
	    teamNameColumn.setCellValueFactory(new PropertyValueFactory<TeamFX, String>("teamName"));
	    teamDescriptionColumn.setCellValueFactory(new PropertyValueFactory<TeamFX, String>("teamDescription"));
	}
    }

    public void initialize() {
	readTeamsList();
	initializeColumns();
	updateTable();
    }

}
