package gcmClient;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import gcmClasses.Game;
import javafx.event.ActionEvent;
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
import serviceFunctions.GameServiceFunctions;

public class GamesDetailsDialogController extends Dialog<ButtonType> implements Initializable {

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

	// get Member from DB -----
	public Game loadGame() {

		Game game = GameServiceFunctions.getGame(ccId);
		return game;
	}

	// initialize TextFields -----

	public void initializeTextFields() {
		Game game = loadGame();

		sIdLabel.setText(String.valueOf(ccId));

		// Game TextFields
		gameTitleTf.setText(game.getGameTitle());
		relaseDateDP.setValue(game.getReleaseDate());
		NotesTa.setText(game.getGameAdditionalNotes());
	}

	// Handle Social Buttons ------------------------------------------------

	public Game getSelectedGame() {

		if (loadGame() != null) {

			Game game = loadGame();
			return game;
		} else {
			Game newGame = new Game("Game Title", // title
					LocalDate.of(1981, 4, 11), // release date
					null, // genres
					null, // members
					null, // tournaments
					"Notes"); // notes
			return newGame;
		}
	}

	public Game updateGame() {
		Game game = getSelectedGame();

		game.setGameTitle(gameTitleTf.getText());
		game.setReleaseDate(relaseDateDP.getValue());
		game.setGameAdditionalNotes(NotesTa.getText());
		return game;
	}

	@FXML
	public void handleGameEditBtn(ActionEvent e) {
		Game game = getSelectedGame();
		int id = getSelectedGame().getId();

		if (id != 0) {
			// Set Game ID Label
			sIdLabel.setText(String.valueOf(id));

			// Game TextFields
			gameTitleTf.setText(game.getGameTitle());
			relaseDateDP.setValue(game.getReleaseDate());
			NotesTa.setText(game.getGameAdditionalNotes());
		}
		// updateGame();
	}

	@FXML
	public void handleGameEditSaveBtn(ActionEvent e) {
		int id = getSelectedGame().getId();

		if (id != 0) {
			Game updatedGame = updateGame();
			GameServiceFunctions.updateGame(id, updatedGame);
		} else {
			Game updatedGame = updateGame();
			GameServiceFunctions.addGame(updatedGame);
//			int newGameId = GameServiceFunctions.getGameWithHighestId().getId();	
//			GameServiceFunctions.addGameToMember(ccId,newGameId);	
		}
	}

	@FXML
	public void handleGameEditNewBtn(ActionEvent e) {
		Game game = updateGame();
		Integer id = null;

		if (id == null) {
			// Set Game ID Label
			sIdLabel.setText(String.valueOf(id));

			gameTitleTf.setPromptText("Enter Game Title");
			NotesTa.setPromptText("Enter Additional Notes");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initializeTextFields();
	}
}
