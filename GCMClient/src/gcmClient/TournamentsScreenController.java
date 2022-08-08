package gcmClient;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


import fxClasses.TournamentFX;
import gcmClasses.Game;
import gcmClasses.Tournament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import serviceFunctions.TournamentServiceFunctions;

public class TournamentsScreenController {

	@FXML
	private ObservableList<TournamentFX> olTournaments = FXCollections.observableArrayList();
	@FXML private AnchorPane tournamentsAnchor;
	@FXML private TableView<TournamentFX> tournamentsTableView;
	@FXML private TableColumn<TournamentFX,Integer> idColumn;	
	@FXML private TableColumn<TournamentFX,String> tournamentTitleColumn;
	@FXML private TableColumn<TournamentFX,String> tournamentDescriptionColumn;
	@FXML private TableColumn<TournamentFX,LocalDate> tournamentDateColumn;
	@FXML private TableColumn<TournamentFX,LocalTime> tournamentTimeBeginnColumn;
	@FXML private TableColumn<TournamentFX,LocalTime> tournamentTimeEndColumn;
	@FXML private TableColumn<TournamentFX,Game> gameColumn;
	@FXML private TableColumn<TournamentFX,String> tournamentResultColumn;


	@FXML
	public Button editDetailsBtn;

	
	@FXML
	private void handleEditDetailsBtn(ActionEvent event) throws IOException {
		FxmlLoader loader = new FxmlLoader();
		DialogPane dialogPane = FXMLLoader.load(getClass().getResource("TournamentsDetailDialog.fxml"));
		Dialog dialog = new Dialog();
		dialog.setDialogPane(dialogPane);
		dialog.showAndWait();

		/*
		Optional<ButtonType> r = new WeinDetailDialog(TournamentFX).showAndWait();
		if(r.isPresent() && r.get().getButtonData() == ButtonData.OK_DONE) {
			// neuer Tournament wurde gespeichert, daher neue Weinliste vom Server holen
			//leseTournamentliste();
			System.out.println("Aktualisiere Tournament Liste");
		}
		 */	
		System.out.println("TournamentsDetailsDialog Button klicked");
	}



	public void updateTable() {		
		// load Data
		if(tournamentsTableView != null) {
				tournamentsTableView.getItems().addAll(olTournaments);
		}
	}



	public void readTournamentsList() {
		olTournaments.clear();

		List<Tournament> xmlTournaments = new ArrayList<Tournament>();
		xmlTournaments = TournamentServiceFunctions.getTournaments();			

		for(Tournament einM : xmlTournaments) {
			olTournaments.add(new TournamentFX(einM));
			System.out.println("CLIENT------------" + "\n" + einM);
		}
	}


	public  void initializeColumns() {
		
		if(idColumn != null) {
			idColumn.setCellValueFactory(new PropertyValueFactory<TournamentFX, Integer>("id"));
			tournamentTitleColumn.setCellValueFactory(new PropertyValueFactory<TournamentFX, String>("tournamentTitle"));
			tournamentDescriptionColumn.setCellValueFactory(new PropertyValueFactory<TournamentFX, String>("tournamentDescription"));
			tournamentDateColumn.setCellValueFactory(new PropertyValueFactory<TournamentFX, LocalDate>("tournamentDate"));
			tournamentTimeBeginnColumn.setCellValueFactory(new PropertyValueFactory<TournamentFX, LocalTime>("tournamentTimeBeginn"));
			tournamentTimeEndColumn.setCellValueFactory(new PropertyValueFactory<TournamentFX, LocalTime>("tournamentTimeEnd"));
			gameColumn.setCellValueFactory(new PropertyValueFactory<TournamentFX, Game>("game"));
			tournamentResultColumn.setCellValueFactory(new PropertyValueFactory<TournamentFX, String>("tournamentResult"));
		}
	}


		
	public void initialize() {
		readTournamentsList();
		initializeColumns();		
		updateTable();
	}


}
