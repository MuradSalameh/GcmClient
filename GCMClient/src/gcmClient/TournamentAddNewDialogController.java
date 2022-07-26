package gcmClient;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gcmClasses.Tournament;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class TournamentAddNewDialogController extends Dialog<ButtonType> implements Initializable {

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
    ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
    @FXML
    ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

    private String startHour;
    private String startMinute;

    private String endHour;
    private String endMinute;

// create new tournament
    public Tournament loadTournament() {

	Tournament tournament = new Tournament("", // title
		"", // description
		LocalDate.now(), // tournament date
		LocalTime.of(00, 00), // start time
		LocalTime.of(00, 00), // end time
		null, // teams list
		null, // games list
		null); // result string

	return tournament;
    }

    // initialize text fields
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

    // update tournament
    public Tournament updateTournament() {
	Tournament tournament = loadTournament();

	tournament.setTouramentTitle(tournamentTitleTF.getText());
	tournament.setTournamentDescription(tournamentDescriptionTF.getText());
	tournament.setTournamentResult(tournamentResultTa.getText());
	tournament.setTournamentDate(dateDp.getValue());

	// ------ Time converters -------

	// Start time
	String hourPattern = "([01]?[0-9]|2[0-3])";
	String minutePattern = "[0-5][0-9]";
	Pattern hPattern = Pattern.compile(hourPattern);
	Pattern mPattern = Pattern.compile(minutePattern);

	Matcher hMatcher = hPattern.matcher(startHourTF.getText());
	if (hMatcher.matches()) {
	    startHour = String.valueOf(startHourTF.getText());
	} else {

	}

	Matcher mMatcher = mPattern.matcher(startMinuteTF.getText());
	if (mMatcher.matches()) {
	    startMinute = String.valueOf(startMinuteTF.getText());
	} else {

	}

	int startHourInt = Integer.parseInt(startHour);
	int startMinuteInt = Integer.parseInt(startMinute);
	LocalTime start = LocalTime.of(startHourInt, startMinuteInt);

	tournament.setTournamentTimeBeginn(start);

	// End Time

	Matcher ehMatcher = hPattern.matcher(endHourTF.getText());
	if (ehMatcher.matches()) {
	    endHour = String.valueOf(endHourTF.getText());
	}

	Matcher emMatcher = mPattern.matcher(endMinuteTF.getText());
	if (emMatcher.matches()) {
	    endMinute = String.valueOf(endMinuteTF.getText());
	}

	int endHourInt = Integer.parseInt(endHour);
	int endMinuteInt = Integer.parseInt(endMinute);
	LocalTime end = LocalTime.of(endHourInt, endMinuteInt);

	tournament.setTournamentTimeEnd(end);

	return tournament;
    }

    // initialize methods when TournamentAddNewDialog.fxml is loaded
    @Override
    public void initialize(URL location, ResourceBundle resources) {
	loadTournament();
	initializeTextFields();
    }
}
