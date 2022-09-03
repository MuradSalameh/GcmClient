package gcmClient;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fxClasses.GameFX;
import gcmClasses.Game;
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
import serviceFunctions.GameServiceFunctions;
import serviceFunctions.TournamentServiceFunctions;

public class TournamentAddGameDialogController extends Dialog<ButtonType> implements Initializable {

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

	// Games Buttons -----

	@FXML
	private Button gAddBtn;
	@FXML
	private Button gRemoveBtn;

	// get Tournament from DB -----
	public Tournament loadTournament() {

		Tournament tournament = TournamentServiceFunctions.getTournament(ccId);
		return tournament;
	}

	// Tournament Games Table
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

	//initialize gamesTableView columns
	public void initializeGamesColumns() {

		if (gamesIdColumn != null) {
			gamesIdColumn.setCellValueFactory(new PropertyValueFactory<GameFX, Integer>("id"));
			gameTitleColumn.setCellValueFactory(new PropertyValueFactory<GameFX, String>("gameTitle"));
			releaseDateColumn.setCellValueFactory(new PropertyValueFactory<GameFX, LocalDate>("releaseDate"));
			gamesAdditionalNotesColumn
					.setCellValueFactory(new PropertyValueFactory<GameFX, String>("gameAdditionalNotes"));
		}
	}

	// update gamesTableView
	public void updateGamesTable() {
		// load Data
		if (gamesTableView != null) {
			gamesTableView.getItems().addAll(olGames);
		}
	}

	
	//read list of all games
	public void readGamesList() {
		olGames.clear();

		List<Game> xmlGames = new ArrayList<Game>();
		xmlGames = GameServiceFunctions.getGamesByTournamentId(ccId);

		if(xmlGames != null) {
		    for (Game einT : xmlGames) {
			olGames.add(new GameFX(einT));

		}
		}
		
	}

	// -------------------------------------------------------------
	// All Available Games Table -----------------------------------
	// -------------------------------------------------------------

	private ObservableList<GameFX> olGames1 = FXCollections.observableArrayList();

	@FXML
	private TableView<GameFX> gamesTableView1;
	@FXML
	private TableColumn<GameFX, Integer> gamesIdColumn1;
	@FXML
	private TableColumn<GameFX, String> gameTitleColumn1;
	@FXML
	private TableColumn<GameFX, LocalDate> releaseDateColumn1;
	@FXML
	private TableColumn<GameFX, String> gamesAdditionalNotesColumn1;

	
	//initialize gamesTableView1 columns
	public void initializeGamesColumns1() {

		if (gamesIdColumn1 != null) {
			gamesIdColumn1.setCellValueFactory(new PropertyValueFactory<GameFX, Integer>("id"));
			gameTitleColumn1.setCellValueFactory(new PropertyValueFactory<GameFX, String>("gameTitle"));
			releaseDateColumn1.setCellValueFactory(new PropertyValueFactory<GameFX, LocalDate>("releaseDate"));
			gamesAdditionalNotesColumn1
					.setCellValueFactory(new PropertyValueFactory<GameFX, String>("gameAdditionalNotes"));
		}
	}

	//update gamesTableView1
	public void updateGamesTable1() {
		
		if (gamesTableView1 != null) {
			gamesTableView1.getItems().addAll(olGames1);
		}
	}

	//read list of all games in gamesTableView1
	public void readGamesList1() {
		olGames1.clear();

		List<Game> xmlGames1 = new ArrayList<Game>();
		xmlGames1 = GameServiceFunctions.getGames();

		if(xmlGames1 != null) {
		  for (Game einT : xmlGames1) {
			olGames1.add(new GameFX(einT));

		}  
		}
		
	}

	// get selected game from all available games in gamesTableView1
	public Game getSelectedGameFromAvailableGames() {

		GameFX getGame = gamesTableView1.getSelectionModel().getSelectedItem();
		if (gamesTableView1.getSelectionModel().getSelectedItem() != null) {
			int id = getGame.getId();
			Game game = GameServiceFunctions.getGame(id);
			return game;
		} else {
			return null;
		}
	}

	//ad selected game from gamesTableView1 to gamesTableView
	public void handleGameAddBtn(ActionEvent e) {

		GameFX selectedGame = gamesTableView1.getSelectionModel().getSelectedItem();

		if (selectedGame == null) {
			return;
		}

		int id = getSelectedGameFromAvailableGames().getId();


		if (containsItem(gamesTableView, selectedGame)) {

			System.out.println("Game already Exists");

		} else {
			System.out.println("Game doesnt Exist");
			GameServiceFunctions.addGameToTournament(id, ccId);
			gamesTableView.getItems().clear();
		
			readGamesList();
		
			updateGamesTable();
			gamesTableView.refresh();
		}
	}

	// Method to check if table already contains specific Item
	public static boolean containsItem(TableView<GameFX> gamesTableView, GameFX gameFX) {
		for (GameFX item : gamesTableView.getItems()) {
			if (item.getId() == gameFX.getId()) {
				return true;
			}
		}
		return false;
	}

	//remove game from tournament in gamesTableView
	@FXML
	public void handleGameDeleteBtn() {

		GameFX Game = gamesTableView.getSelectionModel().getSelectedItem();

		if (Game == null) {
			return;
		}

		int id = Game.getId();
		// delete from database

		GameServiceFunctions.deleteGameFromTournament(id, ccId);

		// remove from Tableview
		gamesTableView.getItems().removeAll(gamesTableView.getSelectionModel().getSelectedItem());

		gamesTableView.getItems().clear();
	
		readGamesList();
		
		updateGamesTable();
		gamesTableView.refresh();
		
	}

	
	// initilize methods when TournamentAddGamesDialog.fxml is loaded
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Tournament Games Table
		readGamesList();
		initializeGamesColumns();
		updateGamesTable();

		// All Available Games Table
		readGamesList1();
		initializeGamesColumns1();
		updateGamesTable1();
	}
}
