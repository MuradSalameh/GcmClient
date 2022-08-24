package gcmClient;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fxClasses.TournamentFX;
import gcmClasses.Tournament;
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
import serviceFunctions.TournamentServiceFunctions;

public class TournamentsScreenController {

	@FXML
	private ObservableList<TournamentFX> olTournaments = FXCollections.observableArrayList();
	@FXML
	private AnchorPane tournamentsAnchor;
	@FXML
	private TableView<TournamentFX> tournamentsTableView;
	@FXML
	private TableColumn<TournamentFX, Integer> idColumn;
	@FXML
	private TableColumn<TournamentFX, String> tournamentTitleColumn;
	@FXML
	private TableColumn<TournamentFX, String> tournamentDescriptionColumn;
	@FXML
	private TableColumn<TournamentFX, LocalDate> tournamentDateColumn;
	@FXML
	private TableColumn<TournamentFX, LocalTime> tournamentTimeBeginnColumn;
	@FXML
	private TableColumn<TournamentFX, LocalTime> tournamentTimeEndColumn;

	@FXML
	private TableColumn<TournamentFX, String> tournamentResultColumn;

	@FXML
	public Button editDetailsBtn;
	@FXML
	Button addNewBtn;

	@FXML
	private void handleAddNewBtn(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("TournamentAddNewDialog.fxml"));
		DialogPane dialogPane = loader.load();

		Dialog dialog = new Dialog();
		dialog.setDialogPane(dialogPane);
		dialog.setResizable(true);

		TournamentAddNewDialogController edand = loader.getController();

		ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

		dialog.getDialogPane().getButtonTypes().set(0, saveBtn);
		dialog.getDialogPane().getButtonTypes().set(1, cancelBtn);

		Optional<ButtonType> result = dialog.showAndWait();

		if (!result.isPresent()) {

			// alert is exited, no button has been pressed.

		} else if (result.get() == saveBtn) {

			Tournament m = edand.updateTournament();
			int idTournament = m.getId();
			TournamentServiceFunctions.addTournament(m);

			tournamentsTableView.getItems().clear();
			tournamentsTableView.refresh();
			readTournamentsList();
			initializeColumns();
			updateTable();

		} else if (result.get() == cancelBtn) {
			System.out.println("Cancel Button Pressed");
		}
	}

	@FXML
	private void handleEditDetailsBtn(ActionEvent event) throws IOException {

		TournamentFX getTournament = tournamentsTableView.getSelectionModel().getSelectedItem();

		if (getTournament == null) {
			return;
		}

		int id = getTournament.getId();
		ControllerCommunicator cc = new ControllerCommunicator(id);

		FXMLLoader loader = new FXMLLoader(getClass().getResource("TournamentDetailDialog.fxml"));
		DialogPane dialogPane = loader.load();

		Dialog dialog = new Dialog();
		dialog.setDialogPane(dialogPane);
		dialog.setResizable(true);

		TournamentDetailDialogController tddc = loader.getController();

		ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

		dialog.getDialogPane().getButtonTypes().set(0, saveBtn);
		dialog.getDialogPane().getButtonTypes().set(1, cancelBtn);

		Optional<ButtonType> result = dialog.showAndWait();

		if (!result.isPresent()) {

			// alert is exited, no button has been pressed.

		} else if (result.get() == saveBtn) {

			Tournament m = tddc.updateTournament();
			int idTournament = m.getId();
			TournamentServiceFunctions.updateTournament(idTournament, m);

			tournamentsTableView.getItems().clear();
			tournamentsTableView.refresh();
			readTournamentsList();
			initializeColumns();
			updateTable();
		} else if (result.get() == cancelBtn) {
			System.out.println("Cancel Button Pressed");
		}
	}

	@FXML
	private void handleDeleteBtn() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("WARNING - DELETING TOURNAMENT");
		alert.setHeaderText("THIS CAN NOT BE UNDONE");
		alert.setContentText("DO YOU REALLY WANT TO DELETE THIS TOURNAMENT?");
		
		TournamentFX tournament = tournamentsTableView.getSelectionModel().getSelectedItem();
		
		if (tournament == null) {
			return;
		}

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {

			// get ID from item in table view
			
			int id = tournament.getId();
			// delete from database

			TournamentServiceFunctions.deleteTournamentFromGame(id);
			TournamentServiceFunctions.deleteTournamentsFromTeams(id);
			TournamentServiceFunctions.deleteTournament(id);

			// remove from Tableview
			tournamentsTableView.getItems().removeAll(tournamentsTableView.getSelectionModel().getSelectedItem());

			tournamentsTableView.refresh();
		}
	}

	public void updateTable() {
		// load Data
		if (tournamentsTableView != null) {
			tournamentsTableView.getItems().addAll(olTournaments);
		}
	}

	public void readTournamentsList() {
		olTournaments.clear();

		List<Tournament> xmlTournaments = new ArrayList<Tournament>();
		xmlTournaments = TournamentServiceFunctions.getTournaments();

		for (Tournament einM : xmlTournaments) {
			olTournaments.add(new TournamentFX(einM));
			System.out.println("CLIENT------------" + "\n" + einM);
		}
	}

	public void initializeColumns() {

		if (idColumn != null) {
			idColumn.setCellValueFactory(new PropertyValueFactory<TournamentFX, Integer>("id"));
			tournamentTitleColumn
					.setCellValueFactory(new PropertyValueFactory<TournamentFX, String>("tournamentTitle"));
			tournamentDescriptionColumn
					.setCellValueFactory(new PropertyValueFactory<TournamentFX, String>("tournamentDescription"));
			tournamentDateColumn
					.setCellValueFactory(new PropertyValueFactory<TournamentFX, LocalDate>("tournamentDate"));
			tournamentTimeBeginnColumn
					.setCellValueFactory(new PropertyValueFactory<TournamentFX, LocalTime>("tournamentTimeBeginn"));
			tournamentTimeEndColumn
					.setCellValueFactory(new PropertyValueFactory<TournamentFX, LocalTime>("tournamentTimeEnd"));
			tournamentResultColumn
					.setCellValueFactory(new PropertyValueFactory<TournamentFX, String>("tournamentResult"));
		}
	}

	public void initialize() {
		readTournamentsList();
		initializeColumns();
		updateTable();
	}

}
