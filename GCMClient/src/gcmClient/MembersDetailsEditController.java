package gcmClient;



import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import fxClasses.EventFX;
import fxClasses.GameFX;
import fxClasses.MemberFX;
import fxClasses.RoleFX;
import fxClasses.SocialFX;
import fxClasses.TeamFX;

import gcmClasses.Event;
import gcmClasses.Game;
import gcmClasses.Member;
import gcmClasses.Role;
import gcmClasses.Social;
import gcmClasses.Team;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import serviceFunctions.EventServiceFunctions;
import serviceFunctions.GameServiceFunctions;
import serviceFunctions.MemberServiceFunctions;
import serviceFunctions.RoleServiceFunctions;
import serviceFunctions.SocialServiceFunctions;
import serviceFunctions.TeamServiceFunctions;


public class MembersDetailsEditController implements Initializable {

	private int ccId = ControllerCommunicator.getId();

	//	Member member = MemberServiceFunctions.getMember(ccId);

	private Stage stage;

	@FXML private BorderPane memberEditBp;

	// Window Buttons -----

	@FXML private Button okBtn;	

	@FXML private Button closeBtn;	

	// Socials Buttons -----

	@FXML private Button sAnBtn;	

	@FXML private Button sEBtn;	

	@FXML private Button sDelBtn;	

	// Roles Buttons -----

	@FXML private Button rAnBtn;	

	@FXML private Button rEBtn;	

	@FXML private Button rDelBtn;	

	// Events Buttons -----

	@FXML private Button eDtlsBtn;	

	@FXML private Button eDelBtn;	

	// Teams Buttons -----

	@FXML private Button tDtlsBtn;	

	@FXML private Button tDelBtn;	

	// Games Buttons -----

	@FXML private Button gDAnBtn;

	@FXML private Button gDelBtn;


	// ID Label -----

	@FXML private Label idLabel;

	// TextFields -----

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


	// get Member from DB -----
	public Member loadMember() {

		Member member = MemberServiceFunctions.getMember(ccId);
		return member;
	}


	// initialize TextFields -----
	@FXML
	public void initializeTextFields() {
		Member member = loadMember();

		idLabel.setText(String.valueOf(ccId));

		clanNameTf.setText(member.getClanName());
		clanIdTf.setText(member.getClanId());
		realNameTf.setText(member.getRealName());
		addressTf.setText(member.getAddress());
		postCodeTf.setText(member.getAddressPostCode());
		cityTf.setText(member.getAddressCity());
		countryTf.setText(member.getCountry());
		emailTf.setText(member.getEmail());
		phoneNumberTf.setText(member.getPhoneNumber());
		dateDp.setValue(member.getBirthday());						
	}	

	// update Member TextFields -----
	public Member updateMemberDetails() {
		Member member = loadMember();

		//----------------------
		clanNameTf.textProperty().addListener((observable, oldValue, newValue) -> {
			clanNameTf.setText(newValue);
		});

		//		System.out.println("New Value " +  clanNameTf.getText());	
		member.setClanName(clanNameTf.getText());

		//----------------------

		clanIdTf.textProperty().addListener((observable, oldValue, newValue) -> {
			clanIdTf.setText(newValue);
		});

		//		System.out.println("New Value " +  clanIdTf.getText());	
		member.setClanId(clanIdTf.getText());		

		//----------------------

		realNameTf.textProperty().addListener((observable, oldValue, newValue) -> {
			realNameTf.setText(newValue);
		});

		//		System.out.println("New Value " +  realNameTf.getText());	
		member.setRealName(realNameTf.getText());		

		//----------------------

		addressTf.textProperty().addListener((observable, oldValue, newValue) -> {
			addressTf.setText(newValue);
		});

		//		System.out.println("New Value " +  addressTf.getText());	
		member.setAddress(addressTf.getText());

		//----------------------

		postCodeTf.textProperty().addListener((observable, oldValue, newValue) -> {
			postCodeTf.setText(newValue);
		});

		//		System.out.println("New Value " +  postCodeTf.getText());	
		member.setAddressPostCode(postCodeTf.getText());

		//----------------------

		cityTf.textProperty().addListener((observable, oldValue, newValue) -> {
			cityTf.setText(newValue);
		});

		//		System.out.println("New Value " +  cityTf.getText());	
		member.setAddressCity(cityTf.getText());	

		//----------------------

		countryTf.textProperty().addListener((observable, oldValue, newValue) -> {
			countryTf.setText(newValue);
		});

		//		System.out.println("New Value " +  countryTf.getText());	
		member.setCountry(countryTf.getText());	

		//----------------------

		emailTf.textProperty().addListener((observable, oldValue, newValue) -> {
			emailTf.setText(newValue);
		});

		//		System.out.println("New Value " +  emailTf.getText());	
		member.setEmail(emailTf.getText());

		//----------------------

		phoneNumberTf.textProperty().addListener((observable, oldValue, newValue) -> {
			phoneNumberTf.setText(newValue);
		});

		//		System.out.println("New Value " +  phoneNumberTf.getText());	
		member.setPhoneNumber(phoneNumberTf.getText());	

		//----------------------

		dateDp.valueProperty().addListener((observable, oldValue, newValue) -> {
			dateDp.setValue(newValue);
		});

		//		System.out.println("New Value " +  dateDp.getValue());	
		member.setBirthday(dateDp.getValue());

		//----------------------

		//		System.out.println("Updated Member = " + member);		
		return member;
	}


	// Teams Table -----

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
		xmlTeams = TeamServiceFunctions.getTeamsByMemberId(ccId);			

		for(Team einT : xmlTeams) {
			olTeams.add(new TeamFX(einT));
			//		System.out.println("CLIENT TeamsTable------------" + "\n" + einT);

		}
	}

	// Socials Table -----

	private ObservableList<SocialFX> olSocials = FXCollections.observableArrayList();

	@FXML private TableView<SocialFX> socialsTableView;
	@FXML private TableColumn<SocialFX,Integer> socialIdColumn;
	@FXML private TableColumn<SocialFX,String> socialPlattformColumn;
	@FXML private TableColumn<SocialFX,String> socialUsernameColumn;
	@FXML private TableColumn<SocialFX,String> socialLinkColumn;
	@FXML private TableColumn<SocialFX,String> socialNotesColumn;

	public  void initializeSocialsColumns() {

		if(socialIdColumn != null) {
			socialIdColumn.setCellValueFactory(new PropertyValueFactory<SocialFX, Integer>("id"));
			socialPlattformColumn.setCellValueFactory(new PropertyValueFactory<SocialFX, String>("socialPlatform"));
			socialUsernameColumn.setCellValueFactory(new PropertyValueFactory<SocialFX, String>("socialUsername"));
			socialLinkColumn.setCellValueFactory(new PropertyValueFactory<SocialFX, String>("socialLink"));
			socialNotesColumn.setCellValueFactory(new PropertyValueFactory<SocialFX, String>("socialNotes"));
		}
	}

	public void updateMemberSocials() {


	}

	public void updateSocialsTable() {		
		// load Data
		if(socialsTableView != null) {
			socialsTableView.getItems().addAll(olSocials);
		}
	}

	public void readSocialsList() {
		olSocials.clear();

		List<Social> xmlSocials = new ArrayList<Social>();
		xmlSocials = SocialServiceFunctions.getSocialsByMemberId(ccId);			

		for(Social einT : xmlSocials) {
			olSocials.add(new SocialFX(einT));
			//		System.out.println("CLIENT SocialsTable------------" + "\n" + einT);

		}
	}



	// Roles Table
	private ObservableList<RoleFX> olRoles = FXCollections.observableArrayList();
	@FXML private TableView<RoleFX> rolesTableView;
	@FXML private TableColumn<RoleFX,Integer> roleIdColumn;
	@FXML private TableColumn<RoleFX,String> roleNameColumn;
	@FXML private TableColumn<RoleFX,String> sroleDescriptionColumn;


	public  void initializeRolesColumns() {

		if(roleIdColumn != null) {
			roleIdColumn.setCellValueFactory(new PropertyValueFactory<RoleFX, Integer>("id"));
			roleNameColumn.setCellValueFactory(new PropertyValueFactory<RoleFX, String>("roleName"));
			sroleDescriptionColumn.setCellValueFactory(new PropertyValueFactory<RoleFX, String>("roleDescription"));
		}
	}

	public void updateRolesTable() {		
		// load Data
		if(rolesTableView != null) {
			rolesTableView.getItems().addAll(olRoles);
		}
	}

	public void readRolesList() {
		olRoles.clear();

		List<Role> xmlRoles = new ArrayList<Role>();
		xmlRoles = RoleServiceFunctions.getRolesByMemberId(ccId);			

		for(Role einT : xmlRoles) {
			olRoles.add(new RoleFX(einT));
			//		System.out.println("CLIENT RolesTable------------" + "\n" + einT);

		}
	}


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
			//		System.out.println("CLIENT GamesTable------------" + "\n" + einT);

		}
	}


	
	
//	@FXML
//	public void handleComboBoxAction(ActionEvent e)  {
//		System.out.println(gCB.getValue() + " selected");         	           	
//	}
	

	// Set on action
//	gCB.setOnAction(event);




	// Events Table
	private ObservableList<EventFX> olEvents = FXCollections.observableArrayList();

	@FXML private TableView<EventFX> eventsTableView;
	@FXML private TableColumn<EventFX,Integer> eventIdColumn;	
	@FXML private TableColumn<EventFX,String> eventTitleColumn;
	@FXML private TableColumn<EventFX,String> eventDescriptionColumn;
	@FXML private TableColumn<EventFX,LocalDate> eventDateColumn;
	@FXML private TableColumn<EventFX,LocalTime> eventStartTimeColumn;
	@FXML private TableColumn<EventFX,LocalTime> eventEndTimeColumn;
	@FXML private TableColumn<EventFX,String> eventAdditionalNotesColumn;
	@FXML private TableColumn<EventFX,Boolean> reoccuringColumn;

	public  void initializeEventsColumns() {

		if(eventIdColumn != null) {
			eventIdColumn.setCellValueFactory(new PropertyValueFactory<EventFX, Integer>("id"));
			eventTitleColumn.setCellValueFactory(new PropertyValueFactory<EventFX, String>("eventTitle"));
			eventDescriptionColumn.setCellValueFactory(new PropertyValueFactory<EventFX, String>("eventDescription"));
			eventDateColumn.setCellValueFactory(new PropertyValueFactory<EventFX, LocalDate>("date"));
			eventStartTimeColumn.setCellValueFactory(new PropertyValueFactory<EventFX, LocalTime>("eventStartTime"));
			eventEndTimeColumn.setCellValueFactory(new PropertyValueFactory<EventFX, LocalTime>("eventEndTime"));
			eventAdditionalNotesColumn.setCellValueFactory(new PropertyValueFactory<EventFX, String>("eventAddidtionalNotes"));		
			reoccuringColumn.setCellValueFactory(new PropertyValueFactory<EventFX, Boolean>("reoccuring"));		
		}
	}

	public void updateEventsTable() {		
		// load Data
		if(eventsTableView != null) {
			eventsTableView.getItems().addAll(olEvents);
		}
	}

	public void readEventsList() {
		olEvents.clear();

		List<Event> xmlEvents = new ArrayList<Event>();
		xmlEvents = EventServiceFunctions.getEventsByMemberId(ccId);			

		for(Event einT : xmlEvents) {
			olEvents.add(new EventFX(einT));
			//		System.out.println("CLIENT EventsTable------------" + "\n" + einT);

		}
	}



	/*



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



	//		public void initialize() {
	//			// TextFields
	//		
	//			initializeTextFields();
	//
	//			// Teams Table
	//			readTeamsList();
	//			initializeTeamsColumns();
	//			updateTeamsTable();
	//
	//			// Socials Table
	//			readSocialsList();
	//			initializeSocialsColumns();
	//			updateSocialsTable();
	//
	//			// Roles Table
	//			readRolesList();
	//			initializeRolesColumns();
	//			updateRolesTable();
	//
	//			// Games Table
	//			readGamesList();
	//			initializeGamesColumns();
	//			updateGamesTable();
	//
	//			// Events Table
	//			readEventsList();
	//			initializeEventsColumns();
	//			updateEventsTable();
	//		}


	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	public void okButtonClicked(ActionEvent actionEvent) throws IOException {
		Member updatedMember = updateMemberDetails();

		MemberServiceFunctions.updateMember(ccId,updatedMember);

		Stage stage = (Stage) okBtn.getScene().getWindow();
		stage.close();

	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initializeTextFields();
	

		// Teams Table
		readTeamsList();
		initializeTeamsColumns();
		updateTeamsTable();

		// Socials Table
		readSocialsList();
		initializeSocialsColumns();
		updateSocialsTable();

		// Roles Table
		readRolesList();
		initializeRolesColumns();
		updateRolesTable();

		// Games Table
		readGamesList();
		initializeGamesColumns();
		updateGamesTable();

		// Events Table
		readEventsList();
		initializeEventsColumns();
		updateEventsTable();

	}

}
