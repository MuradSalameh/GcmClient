package gcmClient;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fxClasses.EventFX;
import fxClasses.MemberFX;
import gcmClasses.Event;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import serviceFunctions.EventServiceFunctions;
import serviceFunctions.MemberServiceFunctions;

public class EventsScreenController {

	@FXML
	private ObservableList<EventFX> olEvents = FXCollections.observableArrayList();
	@FXML private AnchorPane eventsAnchor;
	@FXML private TableView<EventFX> eventsTableView;
	@FXML private TableColumn<EventFX,Integer> idColumn;	
	@FXML private TableColumn<EventFX,String> eventTitleColumn;
	@FXML private TableColumn<EventFX,String> eventDescriptionColumn;
	@FXML private TableColumn<EventFX,LocalDate> dateColumn;
	@FXML private TableColumn<EventFX,LocalTime> eventStartTimeColumn;
	@FXML private TableColumn<EventFX,LocalTime> eventEndTimeColumn;
	@FXML private TableColumn<EventFX,String> additionalNotesColumn;
	@FXML private TableColumn<EventFX,Boolean> reoccuringColumn;

	@FXML
	public Button editDetailsBtn;

	
	@FXML
	private void handleEditDetailsBtn(ActionEvent event) throws IOException {
		FxmlLoader loader = new FxmlLoader();
		DialogPane dialogPane = FXMLLoader.load(getClass().getResource("EventsDetailDialog.fxml"));
		Dialog dialog = new Dialog();
		dialog.setDialogPane(dialogPane);
		dialog.showAndWait();

		/*
		Optional<ButtonType> r = new WeinDetailDialog(EventFX).showAndWait();
		if(r.isPresent() && r.get().getButtonData() == ButtonData.OK_DONE) {
			// neuer Event wurde gespeichert, daher neue Weinliste vom Server holen
			//leseEventliste();
			System.out.println("Aktualisiere Event Liste");
		}
		 */	
		System.out.println("EventsDetailsDialog Button klicked");
	}
	
	@FXML 
	private void handleDeleteBtn()  {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("WARNING - DELETING EVENT");
		alert.setHeaderText("THIS CAN NOT BE UNDONE");
		alert.setContentText("DO YOU REALLY WANT TO DELETE THIS EVENT?");
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			
			// get ID from item in table view
			EventFX event = eventsTableView.getSelectionModel().getSelectedItem();
			int id = event.getId(); 
			// delete from database			
			EventServiceFunctions.deleteEventFromMember(id);
			EventServiceFunctions.deleteEvent(id);
			
			//remove from Tableview
			eventsTableView.getItems().removeAll(
					eventsTableView.getSelectionModel().getSelectedItem()
	        );
			
			eventsTableView.refresh();			
		}	
	}



	public void updateTable() {		
		// load Data
		if(eventsTableView != null) {
				eventsTableView.getItems().addAll(olEvents);
		}
	}



	public void readEventsList() {
		olEvents.clear();

		List<Event> xmlEvents = new ArrayList<Event>();
		xmlEvents = EventServiceFunctions.getEvents();			

		for(Event einM : xmlEvents) {
			olEvents.add(new EventFX(einM));
			System.out.println("CLIENT------------" + "\n" + einM);
		}
	}


	public  void initializeColumns() {
		
		if(idColumn != null) {
			idColumn.setCellValueFactory(new PropertyValueFactory<EventFX, Integer>("id"));
			eventTitleColumn.setCellValueFactory(new PropertyValueFactory<EventFX, String>("eventTitle"));
			eventDescriptionColumn.setCellValueFactory(new PropertyValueFactory<EventFX, String>("eventDescription"));
			dateColumn.setCellValueFactory(new PropertyValueFactory<EventFX, LocalDate>("date"));
			eventStartTimeColumn.setCellValueFactory(new PropertyValueFactory<EventFX, LocalTime>("eventStartTime"));
			eventEndTimeColumn.setCellValueFactory(new PropertyValueFactory<EventFX, LocalTime>("eventEndTime"));
			additionalNotesColumn.setCellValueFactory(new PropertyValueFactory<EventFX, String>("eventAddidtionalNotes"));
			reoccuringColumn.setCellValueFactory(new PropertyValueFactory<EventFX, Boolean>("reoccuring"));
		}
	}

	
	public void initialize() {
		readEventsList();
		initializeColumns();		
		updateTable();
	}


}
