package gcmClient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;



public class FxmlDocumentController implements Initializable {	
	
	@FXML
	private Label label;
	
	@FXML
	private BorderPane bpScreenView;
	
	@FXML
	private void handleHomeBtn(ActionEvent event) throws IOException {
		
		FxmlLoader screen = new FxmlLoader();
		Pane view = screen.getPage("HomeScreen");
		System.out.println("Pane view: " + view);
		
		bpScreenView.setCenter(view);		
		System.out.println("Home Button klicked");
		
	}
	
	
	@FXML
	private void handleMembersBtn(ActionEvent event) throws IOException {
	
		FxmlLoader screen = new FxmlLoader();
		Pane view = screen.getPage("MembersScreen");		
		//System.out.println("Pane view: " + view);
		
		bpScreenView.setCenter(view);
		
		//initialize table and data after loading screen
		MembersScreenController msc = new MembersScreenController();
		msc.initialize();
		//System.out.println("Members Button klicked");
		
		
	}
	
	
	@FXML
	private void handleGamesBtn(ActionEvent event) throws IOException {
		
		FxmlLoader screen = new FxmlLoader();
		Pane view = screen.getPage("GamesScreen");
		//System.out.println("Pane view: " + view);
		bpScreenView.setCenter(view);	
		
		GamesScreenController gsc = new GamesScreenController();
		gsc.initialize();
		//System.out.println("Games Button klicked");
		
	}
	@FXML
	private void handleEventsBtn(ActionEvent event) throws IOException {
		
		FxmlLoader screen = new FxmlLoader();
		Pane view = screen.getPage("EventsScreen");
		//System.out.println("Pane view: " + view);
		
		bpScreenView.setCenter(view);	
		EventsScreenController esc = new EventsScreenController();
		esc.initialize();
		//System.out.println("Events Button klicked");
		
	}
	@FXML
	private void handleTeamsBtn(ActionEvent event) throws IOException {
		
		FxmlLoader screen = new FxmlLoader();
		Pane view = screen.getPage("TeamsScreen");
		System.out.println("Pane view: " + view);
		
		bpScreenView.setCenter(view);
		TeamsScreenController tsc = new TeamsScreenController();
		tsc.initialize();
		System.out.println("Teams Button klicked");
		
	}
	@FXML
	private void handleTournamentsBtn(ActionEvent event) throws IOException {
		
		FxmlLoader screen = new FxmlLoader();
		Pane view = screen.getPage("TournamentsScreen");
		System.out.println("Pane view: " + view);
		
		bpScreenView.setCenter(view);
		TournamentsScreenController trsc = new TournamentsScreenController();
		trsc.initialize();
		System.out.println("Tournaments Button klicked");
		
	}
	@FXML
	private void handlePartnersBtn(ActionEvent event) throws IOException {
		
		FxmlLoader screen = new FxmlLoader();
		Pane view = screen.getPage("PartnersScreen");
		System.out.println("Pane view: " + view);
		
		bpScreenView.setCenter(view);	
		PartnerScreenController psc = new PartnerScreenController();
		psc.initialize();
		System.out.println("Partners Button klicked");
		
	}
	@FXML
	private void handleFinancesBtn(ActionEvent event) throws IOException {
		
		FxmlLoader screen = new FxmlLoader();
		Pane view = screen.getPage("FinancesScreen");
		System.out.println("Pane view: " + view);
		
		bpScreenView.setCenter(view);	
		FinancesScreenController fsc = new FinancesScreenController();
		fsc.initialize();
		
		System.out.println("Finances Button klicked");
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
