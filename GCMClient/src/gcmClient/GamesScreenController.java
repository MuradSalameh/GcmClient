package gcmClient;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fxClasses.GameFX;
import gcmClasses.Game;
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
import serviceFunctions.GameServiceFunctions;

public class GamesScreenController {

    @FXML
    private ObservableList<GameFX> olGames = FXCollections.observableArrayList();
    @FXML
    private AnchorPane gamesAnchor;
    @FXML
    private TableView<GameFX> gamesTableView;
    @FXML
    private TableColumn<GameFX, Integer> idColumn;
    @FXML
    private TableColumn<GameFX, String> gameTitleColumn;
    @FXML
    private TableColumn<GameFX, LocalDate> releaseDateColumn;
    @FXML
    private TableColumn<GameFX, String> additionalNotesColumn;
    @FXML
    public Button editDetailsBtn;
    @FXML
    public Button deleteBtn;

    // edit game details button
    @FXML
    private void handleEditDetailsBtn(ActionEvent event) throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("GamesDetailsDialog.fxml"));

	GameFX game = gamesTableView.getSelectionModel().getSelectedItem();

	if (game == null) {
	    return;
	}

	int id = game.getId();
	ControllerCommunicator cc = new ControllerCommunicator(id);

	DialogPane dialogPane = loader.load();

	Dialog dialog = new Dialog();
	dialog.setDialogPane(dialogPane);
	dialog.setResizable(true);

	GamesDetailsDialogController mddc = loader.getController();

	ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

	dialog.getDialogPane().getButtonTypes().set(0, saveBtn);
	dialog.getDialogPane().getButtonTypes().set(1, cancelBtn);

	Optional<ButtonType> result = dialog.showAndWait();

	if (!result.isPresent()) {

	    // alert is exited, no button has been pressed.

	} else if (result.get() == saveBtn) {

	    Game m = mddc.updateGame();
	    int idGame = m.getId();
	    GameServiceFunctions.updateGame(idGame, m);

	    gamesTableView.getItems().clear();
	    readGamesList();
	    updateTable();
	    gamesTableView.refresh();
	} else if (result.get() == cancelBtn) {
	    System.out.println("Cancel Button Pressed");
	}
    }

    // add new game button
    @FXML
    private void handleAddNewBtn(ActionEvent event) throws IOException {

	FXMLLoader loader = new FXMLLoader(getClass().getResource("GamesDetailsAddNewDialog.fxml"));
	DialogPane dialogPane = loader.load();

	Dialog dialog = new Dialog();
	dialog.setDialogPane(dialogPane);
	dialog.setResizable(true);

	GamesDetailsAddNewDialogController mddc = loader.getController();

	ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

	dialog.getDialogPane().getButtonTypes().set(0, saveBtn);
	dialog.getDialogPane().getButtonTypes().set(1, cancelBtn);

	Optional<ButtonType> result = dialog.showAndWait();

	if (!result.isPresent()) {

	    // alert is exited, no button has been pressed.

	} else if (result.get() == saveBtn) {

	    Game m = mddc.updateGame();
	    int idGame = m.getId();
	    GameServiceFunctions.addGame(m);

	    gamesTableView.getItems().clear();
	    readGamesList();
	    updateTable();
	    gamesTableView.refresh();

	} else if (result.get() == cancelBtn) {
	    System.out.println("Cancel Button Pressed");
	}
    }

    // delete game
    @FXML
    private void handleDeleteBtn() {
	Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setTitle("WARNING - DELETING GAME");
	alert.setHeaderText("THIS CAN NOT BE UNDONE");
	alert.setContentText("DO YOU REALLY WANT TO DELETE THIS GAME?");

	GameFX game = gamesTableView.getSelectionModel().getSelectedItem();

	if (game == null) {
	    return;
	}
	Optional<ButtonType> result = alert.showAndWait();

	// get ID from item in table view

	if (result.get() == ButtonType.OK) {

	    int id = game.getId();

	    // delete from database
	    GameServiceFunctions.deleteGameFromAllTournaments(id);
	    GameServiceFunctions.deleteGameFromAllMembers(id);
	    GameServiceFunctions.deleteGame(id);

	    // remove from Tableview
	    gamesTableView.getItems().removeAll(gamesTableView.getSelectionModel().getSelectedItem());

	    gamesTableView.refresh();
	}
    }

    // update gamesTableView
    public void updateTable() {
	// load Data
	if (gamesTableView != null) {
	    gamesTableView.getItems().addAll(olGames);
	}
    }

    // read list of all games
    public void readGamesList() {
	olGames.clear();

	List<Game> xmlGames = new ArrayList<Game>();

	xmlGames = GameServiceFunctions.getGames();

	if (xmlGames == null) {
	    return;
	}

	for (Game einM : xmlGames) {
	    olGames.add(new GameFX(einM));
	}
    }

    // initialize gamesTableView columns
    public void initializeColumns() {

	if (idColumn != null) {
	    idColumn.setCellValueFactory(new PropertyValueFactory<GameFX, Integer>("id"));
	    gameTitleColumn.setCellValueFactory(new PropertyValueFactory<GameFX, String>("gameTitle"));
	    additionalNotesColumn.setCellValueFactory(new PropertyValueFactory<GameFX, String>("GameAdditionalNotes"));
	    releaseDateColumn.setCellValueFactory(new PropertyValueFactory<GameFX, LocalDate>("releaseDate"));
	}
    }

    // inititalize methods when GamesScreen.fxml is loaded
    public void initialize() {
	readGamesList();
	initializeColumns();
	updateTable();
    }

}
