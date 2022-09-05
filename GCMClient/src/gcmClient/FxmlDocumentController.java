package gcmClient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class FxmlDocumentController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private BorderPane bpScreenView;

    // load HomeScreen when Home button is clicked
    @FXML
    private void handleHomeBtn(ActionEvent event) throws IOException {
	Pane view = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
	bpScreenView.setCenter(view);

    }

    // load MembersScreen when Members button is clicked in main menu
    @FXML
    private void handleMembersBtn(ActionEvent event) throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("MembersScreen.fxml"));
	Pane view = loader.load();
	bpScreenView.setCenter(view);

    }

    // load GamesScreen when Games button is clicked in main menu
    @FXML
    private void handleGamesBtn(ActionEvent event) throws IOException {
	Pane view = FXMLLoader.load(getClass().getResource("GamesScreen.fxml"));
	bpScreenView.setCenter(view);
//		GamesScreenController gsc = new GamesScreenController();
//		gsc.initialize();
    }

    // load EventScreen when Events button is clicked in main menu
    @FXML
    private void handleEventsBtn(ActionEvent event) throws IOException {
	Pane view = FXMLLoader.load(getClass().getResource("EventsScreen.fxml"));
	bpScreenView.setCenter(view);
//		EventsScreenController esc = new EventsScreenController();
//		esc.initialize();
    }

    // load TeamsScreen when Teams Button is Clicked
    @FXML
    private void handleTeamsBtn(ActionEvent event) throws IOException {
	Pane view = FXMLLoader.load(getClass().getResource("TeamsScreen.fxml"));
	bpScreenView.setCenter(view);
//		TeamsScreenController tsc = new TeamsScreenController();
//		tsc.initialize();
    }

    // load TournamentScreen when Tournaments button is cliked in main menu
    @FXML
    private void handleTournamentsBtn(ActionEvent event) throws IOException {
	Pane view = FXMLLoader.load(getClass().getResource("TournamentsScreen.fxml"));
	bpScreenView.setCenter(view);
//		TournamentsScreenController trsc = new TournamentsScreenController();
//		trsc.initialize();
    }

    // load PartnersScreen when Partners button is clicked in main menu
    @FXML
    private void handlePartnersBtn(ActionEvent event) throws IOException {
	Pane view = FXMLLoader.load(getClass().getResource("PartnersScreen.fxml"));
	bpScreenView.setCenter(view);
//		PartnerScreenController psc = new PartnerScreenController();
//		psc.initialize();
    }

    // load FinancesScreen when Finances button is clicked in main menu
    @FXML
    private void handleFinancesBtn(ActionEvent event) throws IOException {
	Pane view = FXMLLoader.load(getClass().getResource("FinancesScreen.fxml"));
	bpScreenView.setCenter(view);
//		FinancesScreenController fsc = new FinancesScreenController();
//		fsc.initialize();
    }

    // initialize when FXMLFocumentController is called
    @Override
    public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub

    }

}
