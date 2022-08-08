package gcmClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fxClasses.TeamFX;
import gcmClasses.Team;
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

		/*
		Optional<ButtonType> r = new WeinDetailDialog(TeamFX).showAndWait();
		if(r.isPresent() && r.get().getButtonData() == ButtonData.OK_DONE) {
			// neuer Team wurde gespeichert, daher neue Weinliste vom Server holen
			//leseTeamliste();
			System.out.println("Aktualisiere Team Liste");
		}
		 */	
		System.out.println("TeamsDetailsDialog Button klicked");
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
