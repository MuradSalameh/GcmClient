package gcmClient;

import java.net.URL;
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
import serviceFunctions.GameServiceFunctions;

public class GamesDetailsDialogController extends Dialog<ButtonType> implements Initializable {

    private int ccId = ControllerCommunicator.getId();

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

    // update game
    public Game updateGame() {
	Game game = loadGame();

	game.setGameTitle(gameTitleTf.getText());
	game.setReleaseDate(relaseDateDP.getValue());
	game.setGameAdditionalNotes(NotesTa.getText());

	return game;
    }

    // initialize methods when GamesDetailsDialog.fxml is opened
    @Override
    public void initialize(URL location, ResourceBundle resources) {
	initializeTextFields();
    }
}
