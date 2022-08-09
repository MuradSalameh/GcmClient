package gcmClient;



import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.cell.PropertyValueFactory;
import fxClasses.EventFX;
import fxClasses.GameFX;
import fxClasses.MemberFX;
import fxClasses.RoleFX;
import fxClasses.SocialFX;
import fxClasses.TeamFX;
import gcmClasses.Member;
import gcmClasses.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import serviceFunctions.MemberServiceFunctions;
import serviceFunctions.TeamServiceFunctions;


public class MembersDetailsDialogController {
	private int ccId = ControllerCommunicator.getId();
	
	
	@FXML
	private ObservableList<MemberFX> olMembers = FXCollections.observableArrayList();
	@FXML private DialogPane memberDetailsDialog;
	@FXML private Label idLabel;
	@FXML private TextField clanNameTf;
	@FXML private TextField clanIdTf;
	@FXML private TextField realNameTf;
	@FXML private TextField addressTf;
	@FXML private TextField postCodeTf;
	@FXML private TextField cityTf;
	@FXML private TextField countryTf;
	@FXML private TextField emailTf;
	@FXML private TextField phoneNumberTf;
	@FXML private DatePicker dateDp;
	
	
	// TextFields
	public void initializeTextFields() {
		idLabel.setText(String.valueOf(ccId));
	}
	
	
	// Teams Table
	
	@FXML private ObservableList<TeamFX> olTeams = FXCollections.observableArrayList();
	@FXML private TableView<TeamFX> teamsTableView;	
	@FXML private TableColumn<TeamFX,Integer> teamsIdColumn;	
	@FXML private TableColumn<TeamFX,String> teamNameColumn;
	@FXML private TableColumn<TeamFX,String> teamDescriptionColumn;
	
	public  void initializeTeamsColumns() {
		
		if(teamsIdColumn != null) {
			teamsIdColumn.setCellValueFactory(new PropertyValueFactory<TeamFX, Integer>("id"));
			teamNameColumn.setCellValueFactory(new PropertyValueFactory<TeamFX, String>("teamName"));
			teamDescriptionColumn.setCellValueFactory(new PropertyValueFactory<TeamFX, String>("teamDescription"));
		}
	}
	
	public void updateTeamsTable() {		
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

/*	
	// Roles Table
	@FXML private TableView<RoleFX> rolesTableView;
	
	
	// Games Table
	@FXML private TableView<GameFX> gamesTableView;
	@FXML private TableColumn<GameFX,Integer> gamesIdColumn;	
	@FXML private TableColumn<GameFX,String> gameTitleColumn;
	@FXML private TableColumn<GameFX,LocalDate> releaseDateColumn;
	@FXML private TableColumn<GameFX,String> gamesAdditionalNotesColumn;
	
	// Events Table
	@FXML private TableView<EventFX> eventsTableView;
	@FXML private TableColumn<EventFX,Integer> eventsIdColumn;	
	@FXML private TableColumn<EventFX,String> eventTitleColumn;
	@FXML private TableColumn<EventFX,String> eventDescriptionColumn;
	@FXML private TableColumn<EventFX,LocalDate> eventDateColumn;
	@FXML private TableColumn<EventFX,LocalTime> eventStartTimeColumn;
	@FXML private TableColumn<EventFX,LocalTime> eventEndTimeColumn;
	@FXML private TableColumn<EventFX,String> eventAdditionalNotesColumn;
	@FXML private TableColumn<EventFX,Boolean> reoccuringColumn;
	
	// Socials Table
	@FXML private TableView<SocialFX> socialsTableView;
	


	@FXML
	public Button editDetailsBtn;


	@FXML
	private void handleEditDetailsBtn(ActionEvent event) throws IOException {
		FxmlLoader loader = new FxmlLoader();
		DialogPane dialogPane = FXMLLoader.load(getClass().getResource("MembersDetailDialog.fxml"));
		Dialog dialog = new Dialog();
		dialog.setDialogPane(dialogPane);
		dialog.showAndWait();

	
		System.out.println("MembersDetailsDialog Button klicked");
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
			MemberFX member = membersTableView.getSelectionModel().getSelectedItem();
			int id = member.getId(); 
			// delete from database
			MemberServiceFunctions.deleteMember(id);
			
			//remove from Tableview
			membersTableView.getItems().removeAll(
					membersTableView.getSelectionModel().getSelectedItem()
	        );
			
			membersTableView.refresh();			
		}	
	}


	@FXML
	public void handleAddNewBtn(ActionEvent t){
		
		

		
	}



	public void updateTable() {		
		// load Data
		if(membersTableView != null) {
			membersTableView.getItems().addAll(olMembers);
		}
	}



	public void readMembersList() {
		olMembers.clear();

		List<Member> xmlMembers = new ArrayList<Member>();
		xmlMembers = MemberServiceFunctions.getMembers();			

		for(Member einM : xmlMembers) {
			olMembers.add(new MemberFX(einM));
			System.out.println("CLIENT------------" + "\n" + einM);
		}
	}


	public  void initializeColumns() {

		if(idColumn != null) {
		
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

*/

	public void initialize() {
	//	readMembersList();
	//	initializeColumns();
		initializeTextFields();
	//	updateTable();
	}


}