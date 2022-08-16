package gcmClient;



import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import fxClasses.GameFX;
import fxClasses.RoleFX;
import fxClasses.SocialFX;
import fxClasses.TeamFX;

import gcmClasses.Game;
import gcmClasses.Member;
import gcmClasses.Role;
import gcmClasses.Social;
import gcmClasses.Team;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import serviceFunctions.GameServiceFunctions;
import serviceFunctions.MemberServiceFunctions;
import serviceFunctions.RoleServiceFunctions;
import serviceFunctions.SocialServiceFunctions;
import serviceFunctions.TeamServiceFunctions;


public class MembersAddGameDialogController extends Dialog<ButtonType> implements Initializable {

	private int ccId = ControllerCommunicator.getId();

	//	Member member = MemberServiceFunctions.getMember(ccId);

	@FXML final DialogPane dialogPane = getDialogPane();
	@FXML private Dialog dialog;
	@FXML private BorderPane memberEditBp;

	@FXML ButtonType cancelBtn = new ButtonType("Cancellus", ButtonData.CANCEL_CLOSE);
	@FXML ButtonType saveBtn = new ButtonType("Speichii", ButtonData.OK_DONE);


	// Games Buttons -----

	@FXML private Button gAddBtn;
	@FXML private Button gRemoveBtn;

	
	// get Member from DB -----
	public Member loadMember() {

		Member member = MemberServiceFunctions.getMember(ccId);
		return member;
	}


/*
	// Handle Roles Buttons ------------------------------------------------

	@FXML
	public void handleRoleAddBtn() {
		// GetRole from ComboBox
		Role rcbValue = rcb.getValue();
		int roleId = rcbValue.getId();

		RoleServiceFunctions.addRoleToMember(ccId,roleId);

		rolesTableView.getItems().clear();
		rolesTableView.refresh();
		readRolesList();
		initializeRolesColumns();		
		updateRolesTable(); 
	}


	
	@FXML 
	public void handleRoleDeleteBtn()  {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("WARNING - DELETING ROLE");
		alert.setHeaderText("THIS CAN NOT BE UNDONE");
		alert.setContentText("DO YOU REALLY WANT TO DELETE THIS ROLE?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){

			// get ID from item in table view
			RoleFX Role = rolesTableView.getSelectionModel().getSelectedItem();
			int id = Role.getId(); 
			// delete from database

			RoleServiceFunctions.deleteRoleFromMember(id, ccId);
		
			//remove from Tableview
			rolesTableView.getItems().removeAll(
					rolesTableView.getSelectionModel().getSelectedItem()
					);

			rolesTableView.refresh();			
		}	
	}

*/
	// Games Table
	private ObservableList<GameFX> olGames = FXCollections.observableArrayList();

	@FXML private TableView<GameFX> gamesTableView;
	@FXML private TableColumn<GameFX,Integer> gamesIdColumn;	
	@FXML private TableColumn<GameFX,String> gameTitleColumn;
	@FXML private TableColumn<GameFX,LocalDate> releaseDateColumn;
	@FXML private TableColumn<GameFX,String> gamesAdditionalNotesColumn;

	public  void initializeGamesColumns() {

		if(gamesIdColumn != null) {
			gamesIdColumn.setCellValueFactory(new PropertyValueFactory<GameFX, Integer>("id"));
			gameTitleColumn.setCellValueFactory(new PropertyValueFactory<GameFX, String>("gameTitle"));
			releaseDateColumn.setCellValueFactory(new PropertyValueFactory<GameFX, LocalDate>("releaseDate"));
			gamesAdditionalNotesColumn.setCellValueFactory(new PropertyValueFactory<GameFX, String>("gameAdditionalNotes"));		
		}
	}

	public void updateGamesTable() {		
		// load Data
		if(gamesTableView != null) {
			gamesTableView.getItems().addAll(olGames);
		}
	}

	public void readGamesList() {
		olGames.clear();

		List<Game> xmlGames = new ArrayList<Game>();
		xmlGames = GameServiceFunctions.getGamesByMemberId(ccId);			

		for(Game einT : xmlGames) {
			olGames.add(new GameFX(einT));

		}
	}



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Games Table
		readGamesList();
		initializeGamesColumns();
		updateGamesTable();
	}
}
