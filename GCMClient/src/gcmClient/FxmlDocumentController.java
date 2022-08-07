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
		
		bpScreenView.setLeft(view);		
		System.out.println("Home Button klicked");
		
	}
	
	
	@FXML
	private void handleMembersBtn(ActionEvent event) throws IOException {
	
		FxmlLoader screen = new FxmlLoader();
		Pane view = screen.getPage("MembersScreen");		
		System.out.println("Pane view: " + view);
		MembersScreenController membersScreenController = new MembersScreenController();

		bpScreenView.setLeft(view);		
		membersScreenController.initialize();
		System.out.println("Members Button klicked");
		
		
	}
	
	
	@FXML
	private void handleGamesBtn(ActionEvent event) throws IOException {
		
		FxmlLoader screen = new FxmlLoader();
		Pane view = screen.getPage("GamesScreen");
		System.out.println("Pane view: " + view);
		
		bpScreenView.setLeft(view);		
		System.out.println("Games Button klicked");
		
	}
	@FXML
	private void handleEventsBtn(ActionEvent event) throws IOException {
		
		FxmlLoader screen = new FxmlLoader();
		Pane view = screen.getPage("EventsScreen");
		System.out.println("Pane view: " + view);
		
		bpScreenView.setLeft(view);		
		System.out.println("Events Button klicked");
		
	}
	@FXML
	private void handleTeamsBtn(ActionEvent event) throws IOException {
		
		FxmlLoader screen = new FxmlLoader();
		Pane view = screen.getPage("TeamsScreen");
		System.out.println("Pane view: " + view);
		
		bpScreenView.setLeft(view);		
		System.out.println("Teams Button klicked");
		
	}
	@FXML
	private void handleTournamentsBtn(ActionEvent event) throws IOException {
		
		FxmlLoader screen = new FxmlLoader();
		Pane view = screen.getPage("TournamentsScreen");
		System.out.println("Pane view: " + view);
		
		bpScreenView.setLeft(view);		
		System.out.println("Tournaments Button klicked");
		
	}
	@FXML
	private void handlePartnersBtn(ActionEvent event) throws IOException {
		
		FxmlLoader screen = new FxmlLoader();
		Pane view = screen.getPage("PartnersScreen");
		System.out.println("Pane view: " + view);
		
		bpScreenView.setLeft(view);		
		System.out.println("Partners Button klicked");
		
	}
	@FXML
	private void handleFinancesBtn(ActionEvent event) throws IOException {
		
		FxmlLoader screen = new FxmlLoader();
		Pane view = screen.getPage("FinancesScreen");
		System.out.println("Pane view: " + view);
		
		bpScreenView.setLeft(view);		
		System.out.println("Finances Button klicked");
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
