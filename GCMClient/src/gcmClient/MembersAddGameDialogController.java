package gcmClient;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fxClasses.GameFX;
import gcmClasses.Game;
import gcmClasses.Member;
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
import serviceFunctions.MemberServiceFunctions;

public class MembersAddGameDialogController extends Dialog<ButtonType> implements Initializable {

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

    @FXML
    private Button gAddBtn;
    @FXML
    private Button gRemoveBtn;

    // get Member from DB -----
    public Member loadMember() {

	Member member = MemberServiceFunctions.getMember(ccId);
	return member;
    }

    // Member Games Table
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

    // initialize gamesTableView columns
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

    // read list of all games
    public void readGamesList() {
	olGames.clear();

	List<Game> xmlGames = new ArrayList<Game>();
	xmlGames = GameServiceFunctions.getGamesByMemberId(ccId);

	for (Game einT : xmlGames) {
	    olGames.add(new GameFX(einT));

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

    // initialize gamesTableView1 columns
    public void initializeGamesColumns1() {

	if (gamesIdColumn1 != null) {
	    gamesIdColumn1.setCellValueFactory(new PropertyValueFactory<GameFX, Integer>("id"));
	    gameTitleColumn1.setCellValueFactory(new PropertyValueFactory<GameFX, String>("gameTitle"));
	    releaseDateColumn1.setCellValueFactory(new PropertyValueFactory<GameFX, LocalDate>("releaseDate"));
	    gamesAdditionalNotesColumn1
		    .setCellValueFactory(new PropertyValueFactory<GameFX, String>("gameAdditionalNotes"));
	}
    }

    // update gamesTableView1
    public void updateGamesTable1() {
	// load Data
	if (gamesTableView1 != null) {
	    gamesTableView1.getItems().addAll(olGames1);
	}
    }

    // read list of all games
    public void readGamesList1() {
	olGames1.clear();

	List<Game> xmlGames1 = new ArrayList<Game>();
	xmlGames1 = GameServiceFunctions.getGames();

	for (Game einT : xmlGames1) {
	    olGames1.add(new GameFX(einT));

	}
    }

    // get selected game from gamesTableView1
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

    // add selected game from gamesTableView1 to gamesTableView
    public void handleGameAddBtn(ActionEvent e) {
	int id = getSelectedGameFromAvailableGames().getId();
	GameFX selectedGame = gamesTableView1.getSelectionModel().getSelectedItem();

	if (selectedGame == null) {
	    return;
	}

	if (containsItem(gamesTableView, selectedGame)) {

	} else {
	    GameServiceFunctions.addGameToMember(ccId, id);
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

    // remove game from member in gamesTableView
    @FXML
    public void handleGameDeleteBtn() {

	GameFX game = gamesTableView.getSelectionModel().getSelectedItem();
	System.out.println("Remove Game: ------------------------\n" + game);
	if (game == null) {
	    return;
	}

	int id = game.getId();
	System.out.println("Delete Game from Member:\n gameId: " + id + "\nmemberId: " + ccId);

	// delete from database
	GameServiceFunctions.deleteGameFromMember(id, ccId);

	// remove from Tableview
	gamesTableView.getItems().removeAll(gamesTableView.getSelectionModel().getSelectedItem());

	gamesTableView.getItems().clear();

	readGamesList();

	updateGamesTable();
	gamesTableView.refresh();
    }

    // initialize methods when MembersAddGamesDetailDialog.fxml is loaded
    @Override
    public void initialize(URL location, ResourceBundle resources) {

	// Member Games Table
	readGamesList();
	initializeGamesColumns();
	updateGamesTable();

	// All Available Games Table
	readGamesList1();
	initializeGamesColumns1();
	updateGamesTable1();
    }
}
