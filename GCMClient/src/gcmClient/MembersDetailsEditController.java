package gcmClient;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import serviceFunctions.GameServiceFunctions;
import serviceFunctions.MemberServiceFunctions;
import serviceFunctions.RoleServiceFunctions;
import serviceFunctions.SocialServiceFunctions;
import serviceFunctions.TeamServiceFunctions;

public class MembersDetailsEditController extends Dialog<ButtonType> implements Initializable {

	private int ccId = ControllerCommunicator.getId();

	// Member member = MemberServiceFunctions.getMember(ccId);

	@FXML
	final DialogPane dialogPane = getDialogPane();
	@FXML
	private Dialog dialog;
	@FXML
	private BorderPane memberEditBp;

	@FXML
	ButtonType cancelBtn = new ButtonType("Cancellus", ButtonData.CANCEL_CLOSE);
	@FXML
	ButtonType saveBtn = new ButtonType("Speichii", ButtonData.OK_DONE);

	// Socials Buttons -----

	@FXML
	private Button sAnBtn;
	@FXML
	private Button sEBtn;
	@FXML
	private Button sDelBtn;
	@FXML
	private Button sSaveBtn;

	// Roles Buttons -----

	@FXML
	private Button rAnBtn;
	@FXML
	private Button rEBtn;
	@FXML
	private Button rDelBtn;
	@FXML
	private Button rSaveBtn;
	@FXML
	private ComboBox<Role> rcb;

	// Teams Buttons -----

	@FXML
	private Button tDtlsBtn;
	@FXML
	private Button tDelBtn;

	// Games Buttons -----

	@FXML
	private Button gDAnBtn;
	@FXML
	private Button gDelBtn;

	// ID Labels -----

	@FXML
	private Label idLabel;
	@FXML
	private Label rIdLabel;
	@FXML
	private Label sIdLabel;

	// Member TextFields -----

	@FXML
	private TextField clanNameTf;
	@FXML
	private TextField clanIdTf;
	@FXML
	private TextField realNameTf;
	@FXML
	private TextField addressTf;
	@FXML
	private TextField postCodeTf;
	@FXML
	private TextField cityTf;
	@FXML
	private TextField countryTf;
	@FXML
	private TextField emailTf;
	@FXML
	private TextField phoneNumberTf;
	@FXML
	private DatePicker dateDp;

	// Social TextFields -----

	@FXML
	private TextField socialPlatformTf;
	@FXML
	private TextField usernameTf;
	@FXML
	private TextField linkTf;
	@FXML
	private TextArea NotesTa;

	// Role TextFields -----

	@FXML
	private TextField roleNameTf;
	@FXML
	private TextField descriptionTf;

	// get Member from DB -----
	public Member loadMember() {

		Member member = MemberServiceFunctions.getMember(ccId);
		return member;
	}

	// initialize TextFields -----

	public void initializeTextFields() {
		Member member = loadMember();

		idLabel.setText(String.valueOf(ccId));

		// Member TextFields
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

		// Social TextFields
		socialPlatformTf.setText("");
		usernameTf.setText("");
		linkTf.setText("");
		NotesTa.setText("");
	}

	// update Member TextFields -----

	public Member updateMemberDetails() {
		Member member = loadMember();

		member.setClanName(clanNameTf.getText());
		member.setClanId(clanIdTf.getText());
		member.setRealName(realNameTf.getText());
		member.setAddress(addressTf.getText());
		member.setAddressPostCode(postCodeTf.getText());
		member.setAddressCity(cityTf.getText());
		member.setCountry(countryTf.getText());
		member.setEmail(emailTf.getText());
		member.setPhoneNumber(phoneNumberTf.getText());
		member.setBirthday(dateDp.getValue());

		return member;
	}

	// Teams Table -----

	@FXML
	private ObservableList<TeamFX> olTeams = FXCollections.observableArrayList();
	@FXML
	private TableView<TeamFX> teamsTableView;
	@FXML
	private TableColumn<TeamFX, Integer> teamsIdColumn;
	@FXML
	private TableColumn<TeamFX, String> teamNameColumn;
	@FXML
	private TableColumn<TeamFX, String> teamDescriptionColumn;

	public void initializeTeamsColumns() {

		if (teamsIdColumn != null) {
			teamsIdColumn.setCellValueFactory(new PropertyValueFactory<TeamFX, Integer>("id"));
			teamNameColumn.setCellValueFactory(new PropertyValueFactory<TeamFX, String>("teamName"));
			teamDescriptionColumn.setCellValueFactory(new PropertyValueFactory<TeamFX, String>("teamDescription"));
		}
	}

	public void updateTeamsTable() {
		// load Data
		if (teamsTableView != null) {
			teamsTableView.getItems().addAll(olTeams);
		}
	}

	public void readTeamsList() {
		olTeams.clear();

		List<Team> xmlTeams = new ArrayList<Team>();
		xmlTeams = TeamServiceFunctions.getTeamsByMemberId(ccId);

		for (Team einT : xmlTeams) {
			olTeams.add(new TeamFX(einT));
		}
	}

	// Socials Table -----

	private ObservableList<SocialFX> olSocials = FXCollections.observableArrayList();

	@FXML
	private TableView<SocialFX> socialsTableView;
	@FXML
	private TableColumn<SocialFX, Integer> socialIdColumn;
	@FXML
	private TableColumn<SocialFX, String> socialPlattformColumn;
	@FXML
	private TableColumn<SocialFX, String> socialUsernameColumn;
	@FXML
	private TableColumn<SocialFX, String> socialLinkColumn;
	@FXML
	private TableColumn<SocialFX, String> socialNotesColumn;

	public void initializeSocialsColumns() {

		if (socialIdColumn != null) {
			socialIdColumn.setCellValueFactory(new PropertyValueFactory<SocialFX, Integer>("id"));
			socialPlattformColumn.setCellValueFactory(new PropertyValueFactory<SocialFX, String>("socialPlatform"));
			socialUsernameColumn.setCellValueFactory(new PropertyValueFactory<SocialFX, String>("socialUsername"));
			socialLinkColumn.setCellValueFactory(new PropertyValueFactory<SocialFX, String>("socialLink"));
			socialNotesColumn.setCellValueFactory(new PropertyValueFactory<SocialFX, String>("socialNotes"));
		}
	}

	public void updateSocialsTable() {
		// load Data
		if (socialsTableView != null) {
			socialsTableView.getItems().addAll(olSocials);
		}
	}

	public void readSocialsList() {
		olSocials.clear();

		List<Social> xmlSocials = new ArrayList<Social>();
		xmlSocials = SocialServiceFunctions.getSocialsByMemberId(ccId);

		for (Social einT : xmlSocials) {
			olSocials.add(new SocialFX(einT));
		}
	}

	public Social getSelectedSocial() {

		SocialFX getSocial = socialsTableView.getSelectionModel().getSelectedItem();
		if (socialsTableView.getSelectionModel().getSelectedItem() != null) {
			int id = getSocial.getId();
			Social social = SocialServiceFunctions.getSocial(id);
			return social;
		} else {
			Social newSocial = new Social("Platform", // platform
					"Username", // username
					"Link", // link
					null);
			return newSocial;
		}
	}

	public Social updateSocial() {
		Social social = getSelectedSocial();

		social.setSocialPlatform(socialPlatformTf.getText());
		social.setSocialUsername(usernameTf.getText());
		social.setSocialLink(linkTf.getText());
		social.setSocialNotes(NotesTa.getText());
		return social;
	}

	// Handle Social Buttons ------------------------------------------------

	@FXML
	public void handleSocialEditBtn(ActionEvent e) {
		Social social = getSelectedSocial();
		int id = getSelectedSocial().getId();

		if (id != 0) {
			// Set Social ID Label
			sIdLabel.setText(String.valueOf(id));

			// Social TextFields
			socialPlatformTf.setText(social.getSocialPlatform());
			usernameTf.setText(social.getSocialUsername());
			linkTf.setText(social.getSocialLink());
			NotesTa.setText(social.getSocialNotes());
		}
		// updateSocial();
	}

	@FXML
	public void handleSocialEditSaveBtn(ActionEvent e) {
		int id = getSelectedSocial().getId();

		if (id != 0) {
			Social updatedSocial = updateSocial();
			SocialServiceFunctions.updateSocial(id, updatedSocial);
		} else {
			Social updatedSocial = updateSocial();
			SocialServiceFunctions.addSocial(updatedSocial);
			int newSocialId = SocialServiceFunctions.getSocialWithHighestId().getId();
			SocialServiceFunctions.addSocialToMember(ccId, newSocialId);
		}

		socialsTableView.getItems().clear();
		socialsTableView.refresh();
		readSocialsList();
		initializeSocialsColumns();
		updateSocialsTable();
	}

	@FXML
	public void handleSocialEditNewBtn(ActionEvent e) {
		Social social = updateSocial();
		Integer id = updateSocial().getId();

		if (id != null) {
			id = null;
			Social newSocial = new Social("", // platform
					"", // username
					"", // link
					null);

			socialPlatformTf.setText(newSocial.getSocialPlatform());
			usernameTf.setText(newSocial.getSocialUsername());
			linkTf.setText(newSocial.getSocialLink());
			NotesTa.setText(newSocial.getSocialNotes());

			socialPlatformTf.setPromptText("Enter Social Platform");
			usernameTf.setPromptText("Enter Username");
			linkTf.setPromptText("Enter Link");
			NotesTa.setPromptText("Enter Additional Notes");

		}

		id = null;// Set Social ID Label
		sIdLabel.setText(String.valueOf(id));

		socialPlatformTf.setPromptText("Enter Social Platform");
		usernameTf.setPromptText("Enter Username");
		linkTf.setPromptText("Enter Link");
		NotesTa.setPromptText("Enter Additional Notes");

	}

	@FXML
	public void handleSocialDeleteBtn() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("WARNING - DELETING SOCIAL");
		alert.setHeaderText("THIS CAN NOT BE UNDONE");
		alert.setContentText("DO YOU REALLY WANT TO DELETE THIS SOCIAL?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {

			// get ID from item in table view
			SocialFX social = socialsTableView.getSelectionModel().getSelectedItem();
			int id = social.getId();
			// delete from database

			// SocialServiceFunctions.deleteSocialFromMember(id);
			SocialServiceFunctions.deleteSocial(id);

			// remove from Tableview
			socialsTableView.getItems().removeAll(socialsTableView.getSelectionModel().getSelectedItem());

			socialsTableView.refresh();
		}
	}

	// Roles Table
	private ObservableList<RoleFX> olRoles = FXCollections.observableArrayList();
	@FXML
	private TableView<RoleFX> rolesTableView;
	@FXML
	private TableColumn<RoleFX, Integer> roleIdColumn;
	@FXML
	private TableColumn<RoleFX, String> roleNameColumn;
	@FXML
	private TableColumn<RoleFX, String> sroleDescriptionColumn;

	public void initializeRolesColumns() {

		if (roleIdColumn != null) {
			roleIdColumn.setCellValueFactory(new PropertyValueFactory<RoleFX, Integer>("id"));
			roleNameColumn.setCellValueFactory(new PropertyValueFactory<RoleFX, String>("roleName"));
			sroleDescriptionColumn.setCellValueFactory(new PropertyValueFactory<RoleFX, String>("roleDescription"));
		}
	}

	public void updateRolesTable() {
		// load Data
		if (rolesTableView != null) {
			rolesTableView.getItems().addAll(olRoles);
		}
	}

	public void readRolesList() {
		olRoles.clear();

		try {
			List<Role> xmlRoles = new ArrayList<Role>();
			xmlRoles = RoleServiceFunctions.getRolesByMemberId(ccId);
			for (Role einT : xmlRoles) {
				olRoles.add(new RoleFX(einT));
				// System.out.println("CLIENT RolesTable------------" + "\n" + einT);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Handle Roles Buttons ------------------------------------------------

	@FXML
	public void handleRoleAddBtn() {
		// GetRole from ComboBox
		Role rcbValue = rcb.getValue();
		int roleId = rcbValue.getId();

		RoleServiceFunctions.addRoleToMember(ccId, roleId);

		rolesTableView.getItems().clear();
		rolesTableView.refresh();
		readRolesList();
		initializeRolesColumns();
		updateRolesTable();
	}

	@FXML
	public void loadComboBox() {
		List<Role> xmlRoles = new ArrayList<Role>();
		xmlRoles = RoleServiceFunctions.getRoles();
		Callback<ListView<Role>, ListCell<Role>> cellFactory = lv -> new ListCell<Role>() {

			@Override
			protected void updateItem(Role item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getRoleName());
			}
		};

		rcb.setButtonCell(cellFactory.call(null));
		rcb.setCellFactory(cellFactory);
		rcb.getItems().addAll(xmlRoles);
	}

	@FXML
	public void handleRoleDeleteBtn() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("WARNING - DELETING ROLE");
		alert.setHeaderText("THIS CAN NOT BE UNDONE");
		alert.setContentText("DO YOU REALLY WANT TO DELETE THIS ROLE?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {

			// get ID from item in table view
			RoleFX Role = rolesTableView.getSelectionModel().getSelectedItem();
			int id = Role.getId();
			// delete from database

			RoleServiceFunctions.deleteRoleFromMember(id, ccId);

			// remove from Tableview
			rolesTableView.getItems().removeAll(rolesTableView.getSelectionModel().getSelectedItem());

			rolesTableView.refresh();
		}
	}

	// Games Table
	private ObservableList<GameFX> olGames = FXCollections.observableArrayList();

	@FXML
	private TableView<GameFX> gamesTableView;
	@FXML
	private TableColumn<GameFX, Integer> gamesIdColumn;
	@FXML
	private TableColumn<GameFX, String> gameTitleColumn;
	@FXML
	private TableColumn<GameFX, LocalDate> releaseDateColumn;
	@FXML
	private TableColumn<GameFX, String> gamesAdditionalNotesColumn;

	public void initializeGamesColumns() {

		if (gamesIdColumn != null) {
			gamesIdColumn.setCellValueFactory(new PropertyValueFactory<GameFX, Integer>("id"));
			gameTitleColumn.setCellValueFactory(new PropertyValueFactory<GameFX, String>("gameTitle"));
			releaseDateColumn.setCellValueFactory(new PropertyValueFactory<GameFX, LocalDate>("releaseDate"));
			gamesAdditionalNotesColumn
					.setCellValueFactory(new PropertyValueFactory<GameFX, String>("gameAdditionalNotes"));
		}
	}

	public void updateGamesTable() {
		// load Data
		if (gamesTableView != null) {
			gamesTableView.getItems().addAll(olGames);
		}
	}

	public void readGamesList() {
		olGames.clear();

		List<Game> xmlGames = new ArrayList<Game>();
		xmlGames = GameServiceFunctions.getGamesByMemberId(ccId);

		for (Game einT : xmlGames) {
			olGames.add(new GameFX(einT));

		}
	}

	public void handleAddGamesBtn() throws IOException {

		FXMLLoader addGameloader = new FXMLLoader(getClass().getResource("MembersAddGamesDetailDialog.fxml"));

		DialogPane dialogPaneAddGame = addGameloader.load();

		Dialog dialog = new Dialog();
		dialog.setDialogPane(dialogPaneAddGame);
		dialog.setResizable(true);

		MembersAddGameDialogController magdc = addGameloader.getController();

		ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

		dialog.getDialogPane().getButtonTypes().set(0, saveBtn);
		dialog.getDialogPane().getButtonTypes().set(1, cancelBtn);

		Optional<ButtonType> result = dialog.showAndWait();

		if (!result.isPresent()) {

			// alert is exited, no button has been pressed.

		} else if (result.get() == saveBtn) {

			// Member m = mddc.updateMemberDetails();
			// int idMember = m.getId();
			// MemberServiceFunctions.updateMember(idMember, m);
			//
			// membersTableView.getItems().clear();
			// membersTableView.refresh();
			// readMembersList();
			// initializeColumns();
			// updateTable();
			//
			// Social soc = mddc.updateSocial();
			// int idSocial = soc.getId();
			// SocialServiceFunctions.updateSocial(idSocial,soc);

		} else if (result.get() == cancelBtn) {

			System.out.println("Cancel Button Pressed");

		}

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
		loadComboBox();

		// Games Table
		readGamesList();
		initializeGamesColumns();
		updateGamesTable();
	}
}
