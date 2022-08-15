package gcmClient;



import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.cell.PropertyValueFactory;
import fxClasses.GameFX;
import fxClasses.MemberFX;
import gcmClasses.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import serviceFunctions.GameServiceFunctions;
import serviceFunctions.MemberServiceFunctions;


public class GamesScreenController {

	@FXML
	private ObservableList<GameFX> olGames = FXCollections.observableArrayList();
	@FXML private AnchorPane gamesAnchor;
	@FXML private TableView<GameFX> gamesTableView;
	@FXML private TableColumn<GameFX,Integer> idColumn;	
	@FXML private TableColumn<GameFX,String> gameTitleColumn;
	@FXML private TableColumn<GameFX,LocalDate> releaseDateColumn;
	@FXML private TableColumn<GameFX,String> additionalNotesColumn;

	@FXML
	public Button editDetailsBtn;

	
	@FXML
	private void handleEditDetailsBtn(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		DialogPane dialogPane = FXMLLoader.load(getClass().getResource("GamesDetailDialog.fxml"));
		Dialog dialog = new Dialog();
		dialog.setDialogPane(dialogPane);
		dialog.showAndWait();

	
		System.out.println("GamesDetailsDialog Button klicked");
	}
	
	@FXML 
	private void handleDeleteBtn()  {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("WARNING - DELETING GAME");
		alert.setHeaderText("THIS CAN NOT BE UNDONE");
		alert.setContentText("DO YOU REALLY WANT TO DELETE THIS GAME?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){

			// get ID from item in table view
			GameFX game = gamesTableView.getSelectionModel().getSelectedItem();
			int id = game.getId(); 
			// delete from database
			GameServiceFunctions.deleteGameFromGenre(id);
			GameServiceFunctions.deleteGameFromMember(id);
			GameServiceFunctions.deleteGameFromTournament(id);
		
			GameServiceFunctions.deleteGame(id);

			//remove from Tableview
			gamesTableView.getItems().removeAll(
					gamesTableView.getSelectionModel().getSelectedItem()
					);

			gamesTableView.refresh();			
		}	
	}




	public void updateTable() {		
		// load Data
		if(gamesTableView != null) {
				gamesTableView.getItems().addAll(olGames);
		}
	}



	public void readGamesList() {
		olGames.clear();

		List<Game> xmlGames = new ArrayList<Game>();
		xmlGames = GameServiceFunctions.getGames();			

		for(Game einM : xmlGames) {
			olGames.add(new GameFX(einM));
			System.out.println("CLIENT------------" + "\n" + einM);
		}
	}


	public  void initializeColumns() {
		
		if(idColumn != null) {
			idColumn.setCellValueFactory(new PropertyValueFactory<GameFX, Integer>("id"));
			gameTitleColumn.setCellValueFactory(new PropertyValueFactory<GameFX, String>("gameTitle"));
			additionalNotesColumn.setCellValueFactory(new PropertyValueFactory<GameFX, String>("GameAdditionalNotes"));
			releaseDateColumn.setCellValueFactory(new PropertyValueFactory<GameFX, LocalDate>("releaseDate"));
		}
	}


		
	public void initialize() {
		readGamesList();
		initializeColumns();		
		updateTable();
	}


}
