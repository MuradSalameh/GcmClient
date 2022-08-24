package gcmClient;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import fxClasses.GameFX;
import fxClasses.TeamFX;
import gcmClasses.Game;
import gcmClasses.Team;
import gcmClasses.Tournament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import serviceFunctions.GameServiceFunctions;
import serviceFunctions.TeamServiceFunctions;
import serviceFunctions.TournamentServiceFunctions;

public class TournamentDetailDialogController extends Dialog<ButtonType> implements Initializable {

	private int ccId = ControllerCommunicator.getId();

	@FXML
	final DialogPane dialogPane = getDialogPane();
	@FXML
	private Dialog dialog;
	@FXML
	private BorderPane tournamentEditBp;
	@FXML
	private Label idLabel;
	@FXML
	private TextField tournamentTitleTF;
	@FXML
	private TextField tournamentDescriptionTF;
	@FXML
	private DatePicker dateDp;
	@FXML
	private TextField startHourTF;
	@FXML
	private TextField startMinuteTF;
	@FXML
	private TextField endHourTF;
	@FXML
	private TextField endMinuteTF;
	@FXML
	private TextArea tournamentResultTa;
	@FXML
	private Button tAddRemoveBtn;
	@FXML
	private Button gAddRemoveBtn;
	@FXML
	ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	@FXML
	ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

	private String startHour;
	private String startMinute;

	private String endHour;
	private String endMinute;

	public Tournament loadTournament() {

		Tournament tournament = TournamentServiceFunctions.getTournament(ccId);
		return tournament;
	}

	public Tournament getSelectedTournament() {

		if (loadTournament() != null) {
			Tournament tournament = loadTournament();
			return tournament;
		} else {
			Tournament tournament = new Tournament("", // title
					"", // description
					LocalDate.of(2022, 1, 31), // tournament date
					LocalTime.of(00, 00), // start time
					LocalTime.of(00, 00), // end time
					null, // teams list
					null, // games list
					null); // result string

			return tournament;
		}
	}

	public void initializeTextFields() {
		Tournament tournament = loadTournament();

		idLabel.setText(String.valueOf(ccId));

		startHour = String.valueOf(tournament.getTournamentTimeBeginn().getHour());
		startMinute = String.valueOf(tournament.getTournamentTimeBeginn().getMinute());

		endHour = String.valueOf(tournament.getTournamentTimeEnd().getHour());
		endMinute = String.valueOf(tournament.getTournamentTimeEnd().getMinute());

		// Tournament TextFields
		tournamentTitleTF.setText(tournament.getTouramentTitle());
		tournamentDescriptionTF.setText(tournament.getTournamentDescription());
		tournamentResultTa.setText(tournament.getTournamentResult());
		dateDp.setValue(tournament.getTournamentDate());

		startHourTF.setText(startHour);
		startMinuteTF.setText(startMinute);

		endHourTF.setText(endHour);
		endMinuteTF.setText(endMinute);

		tournamentTitleTF.setPromptText("Enter Tournament Title");
		tournamentDescriptionTF.setPromptText("Enter Description");
		tournamentResultTa.setPromptText("Enter Results If Available");
		startHourTF.setPromptText("00");
		startMinuteTF.setPromptText("00");
		endHourTF.setPromptText("00");
		endMinuteTF.setPromptText("00");

	}

	public Tournament updateTournament() {
		Tournament tournament = loadTournament();

		tournament.setTouramentTitle(tournamentTitleTF.getText());
		tournament.setTournamentDescription(tournamentDescriptionTF.getText());
		tournament.setTournamentResult(tournamentResultTa.getText());
		tournament.setTournamentDate(dateDp.getValue());

		// ------ Time converters -------

		// Start time
		startHour = String.valueOf(startHourTF.getText());
		startMinute = String.valueOf(startMinuteTF.getText());

		int startHourInt = Integer.parseInt(startHour);
		int startMinuteInt = Integer.parseInt(startMinute);
		LocalTime start = LocalTime.of(startHourInt, startMinuteInt);

		tournament.setTournamentTimeBeginn(start);

		// End Time
		endHour = String.valueOf(endHourTF.getText());
		endMinute = String.valueOf(endMinuteTF.getText());

		int endHourInt = Integer.parseInt(endHour);
		int endMinuteInt = Integer.parseInt(endMinute);
		LocalTime end = LocalTime.of(endHourInt, endMinuteInt);

		tournament.setTournamentTimeEnd(end);

		// ---------------------------------
		return tournament;
	}

	// Games Table
	private ObservableList<GameFX> olGames = FXCollections.observableArrayList();

	@FXML
	private TableView<GameFX> gamesTableView;
	@FXML
	private TableColumn<GameFX, Integer> gamesIdColumn;
	@FXML
	private TableColumn<GameFX, String> gameTitleColumn;

	public void initializeGamesColumns() {

		if (gamesIdColumn != null) {
			gamesIdColumn.setCellValueFactory(new PropertyValueFactory<GameFX, Integer>("id"));
			gameTitleColumn.setCellValueFactory(new PropertyValueFactory<GameFX, String>("gameTitle"));
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
		xmlGames = GameServiceFunctions.getGamesByTournamentId(ccId);

		for (Game einT : xmlGames) {
			olGames.add(new GameFX(einT));

		}
	}

	@FXML
	public void handleAddGamesBtn(ActionEvent e) throws IOException {
		FXMLLoader addGameloader = new FXMLLoader(getClass().getResource("TournamentAddGamesDialog.fxml"));

		DialogPane dialogPaneAddGame = addGameloader.load();

		Dialog dialog = new Dialog();
		dialog.setDialogPane(dialogPaneAddGame);
		dialog.setResizable(true);

		TournamentAddGameDialogController tagdc = addGameloader.getController();

		ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

		dialog.getDialogPane().getButtonTypes().set(0, saveBtn);
		dialog.getDialogPane().getButtonTypes().set(1, cancelBtn);

		Optional<ButtonType> result = dialog.showAndWait();

		if (!result.isPresent()) {

			// alert is exited, no button has been pressed.

		} else if (result.get() == saveBtn) {

			gamesTableView.getItems().clear();
			gamesTableView.refresh();
			readGamesList();
			initializeGamesColumns();
			updateGamesTable();
		} else if (result.get() == cancelBtn) {

			System.out.println("Cancel Button Pressed");

		}
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

	public void initializeTeamsColumns() {

		if (teamsIdColumn != null) {
			teamsIdColumn.setCellValueFactory(new PropertyValueFactory<TeamFX, Integer>("id"));
			teamNameColumn.setCellValueFactory(new PropertyValueFactory<TeamFX, String>("teamName"));
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
		xmlTeams = TeamServiceFunctions.getTeamsByTournamentId(ccId);

		for (Team einT : xmlTeams) {
			olTeams.add(new TeamFX(einT));
		}

	}

	@FXML
	public void handleAddTeamsBtn(ActionEvent e) throws IOException {
		FXMLLoader addTeamloader = new FXMLLoader(getClass().getResource("TournamentAddTeamsDialog.fxml"));

		DialogPane dialogPaneAddTeam = addTeamloader.load();

		Dialog dialog = new Dialog();
		dialog.setDialogPane(dialogPaneAddTeam);
		dialog.setResizable(true);

		TournamentAddTeamsDialogController magdc = addTeamloader.getController();

		ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

		dialog.getDialogPane().getButtonTypes().set(0, saveBtn);
		dialog.getDialogPane().getButtonTypes().set(1, cancelBtn);

		Optional<ButtonType> result = dialog.showAndWait();

		if (!result.isPresent()) {

			// alert is exited, no button has been pressed.

		} else if (result.get() == saveBtn) {

			teamsTableView.getItems().clear();
			teamsTableView.refresh();
			readTeamsList();
			initializeTeamsColumns();
			updateTeamsTable();
		} else if (result.get() == cancelBtn) {

			System.out.println("Cancel Button Pressed");

		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadTournament();
		initializeTextFields();

		// Teams Table
		readTeamsList();
		initializeTeamsColumns();
		updateTeamsTable();

		// Games Table
		readGamesList();
		initializeGamesColumns();
		updateGamesTable();
	}
}
