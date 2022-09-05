package gcmClient;

import java.net.URL;
import java.util.ResourceBundle;

import gcmClasses.Team;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class TeamsAddNewDetailDialogController extends Dialog<ButtonType> implements Initializable {

    private int ccId = ControllerCommunicator.getId();

    @FXML
    final DialogPane dialogPane = getDialogPane();
    @FXML
    private Dialog dialog;
    @FXML
    private BorderPane teamEditBp;
    @FXML
    private Label idLabel;
    @FXML
    private TextField teamNameTF;
    @FXML
    private TextField teamDescriptionTF;

    @FXML
    ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
    @FXML
    ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

    // create new team
    public Team loadTeam() {

	Team team = new Team("", // team name
		"", // desc
		null // members teams
	);

	return team;
    }

    // initialize text fields
    public void initializeTextFields() {
	Team team = loadTeam();

	// idLabel.setText(String.valueOf(ccId));

	// Team TextFields
	teamNameTF.setText(team.getTeamName());
	teamDescriptionTF.setText(team.getTeamDescription());

	teamNameTF.setPromptText("Enter Team Name");
	teamDescriptionTF.setPromptText("Enter Description");

    }

    // update team
    public Team updateTeam() {
	Team team = loadTeam();

	team.setTeamName(teamNameTF.getText());
	team.setTeamDescription(teamDescriptionTF.getText());

	return team;
    }

    // initialize methods when TeamsAddNewDetailDialog.fxml is loaded
    @Override
    public void initialize(URL location, ResourceBundle resources) {
	loadTeam();
	initializeTextFields();

    }
}
