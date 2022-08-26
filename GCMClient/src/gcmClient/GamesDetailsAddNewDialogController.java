package gcmClient;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import gcmClasses.Game;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class GamesDetailsAddNewDialogController extends Dialog<ButtonType> implements Initializable {

	private int ccId = ControllerCommunicator.getId();

	@FXML
	final DialogPane dialogPane = getDialogPane();
	@FXML
	private Dialog dialog;
	@FXML
	private BorderPane memberEditBp;

	@FXML
	ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	@FXML
	ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

	// Games Buttons -----

	@FXML
	private Button gSaveBtn;
	@FXML
	private Button gAnBtn;
	@FXML
	private Button gEBtn;
	@FXML
	private Button gDelBtn;

	// ID Labels -----

	@FXML
	private Label sIdLabel;

	// Game TextFields -----

	@FXML
	private TextField gameTitleTf;
	@FXML
	private DatePicker relaseDateDP;
	@FXML
	private TextArea NotesTa;
	
	
	
	// create empty game object
	public Game loadGame() {

		Game newGame = new Game("Game Title", // title
				LocalDate.now(), // release date
				null, // tournaments
				null, "Notes"); // notes

		return newGame;
	}

	// initialize TextFields -----

	public void initializeTextFields() {
		Game game = loadGame();

		sIdLabel.setText(String.valueOf(loadGame().getId()));

		// Game TextFields
		gameTitleTf.setText(game.getGameTitle());
		relaseDateDP.setValue(game.getReleaseDate());
		NotesTa.setText(game.getGameAdditionalNotes());
	}

	// Handle Game Buttons ------------------------------------------------

	
	//update game
	public Game updateGame() {
		Game game = loadGame();

		game.setGameTitle(gameTitleTf.getText());
		game.setReleaseDate(relaseDateDP.getValue());
		game.setGameAdditionalNotes(NotesTa.getText());
		return game;
	}

	


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadGame();
		initializeTextFields();
	}
}
