package gcmClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fxClasses.GameFX;
import fxClasses.TeamFX;
import gcmClasses.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import serviceFunctions.GameServiceFunctions;
import serviceFunctions.TeamServiceFunctions;

public class TeamsScreenController {

	@FXML
	private ObservableList<TeamFX> olTeams = FXCollections.observableArrayList();
	@FXML private AnchorPane teamsAnchor;
	@FXML private TableView<TeamFX> teamsTableView;
	@FXML private TableColumn<TeamFX,Integer> idColumn;	
	@FXML private TableColumn<TeamFX,String> teamNameColumn;
	@FXML private TableColumn<TeamFX,String> teamDescriptionColumn;


	@FXML
	public Button editDetailsBtn;

	
	@FXML
	private void handleEditDetailsBtn(ActionEvent event) throws IOException {
		FxmlLoader loader = new FxmlLoader();
		DialogPane dialogPane = FXMLLoader.load(getClass().getResource("TeamsDetailDialog.fxml"));
		Dialog dialog = new Dialog();
		dialog.setDialogPane(dialogPane);
		dialog.showAndWait();

	
		System.out.println("TeamsDetailsDialog Button klicked");
	}

	@FXML 
	private void handleDeleteBtn()  {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("WARNING - DELETING MEMBER");
		alert.setHeaderText("THIS CAN NOT BE UNDONE");
		alert.setContentText("DO YOU REALLY WANT TO DELETE THIS MEMBER?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){

			// get ID from item in table view
			TeamFX team = teamsTableView.getSelectionModel().getSelectedItem();
			int id = team.getId(); 
			// delete from database		
			TeamServiceFunctions.deleteTeamFromMember(id);
			TeamServiceFunctions.deleteTeamFromTournaments(id);
			TeamServiceFunctions.deleteTeam(id);

			//remove from Tableview
			teamsTableView.getItems().removeAll(
					teamsTableView.getSelectionModel().getSelectedItem()
			);

			teamsTableView.refresh();			
		}	
	}



	public void updateTable() {		
		// load Data
		if(teamsTableView != null) {
				teamsTableView.getItems().addAll(olTeams);
		}
	}



	public void readTeamsList() {
		olTeams.clear();

		List<Team> xmlTeams = new ArrayList<Team>();
		xmlTeams = TeamServiceFunctions.getTeams();			

		for(Team einM : xmlTeams) {
			olTeams.add(new TeamFX(einM));
			System.out.println("CLIENT------------" + "\n" + einM);
		}
	}


	public  void initializeColumns() {
		
		if(idColumn != null) {
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
