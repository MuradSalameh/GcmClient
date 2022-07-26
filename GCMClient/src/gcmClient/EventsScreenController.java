package gcmClient;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fxClasses.EventFX;
import gcmClasses.Event;
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
import serviceFunctions.EventServiceFunctions;

public class EventsScreenController {

    @FXML
    private ObservableList<EventFX> olEvents = FXCollections.observableArrayList();
    @FXML
    private AnchorPane eventsAnchor;
    @FXML
    private TableView<EventFX> eventsTableView;
    @FXML
    private TableColumn<EventFX, Integer> idColumn;
    @FXML
    private TableColumn<EventFX, String> eventTitleColumn;
    @FXML
    private TableColumn<EventFX, String> eventDescriptionColumn;
    @FXML
    private TableColumn<EventFX, LocalDate> dateColumn;
    @FXML
    private TableColumn<EventFX, LocalTime> eventStartTimeColumn;
    @FXML
    private TableColumn<EventFX, LocalTime> eventEndTimeColumn;
    @FXML
    private TableColumn<EventFX, String> additionalNotesColumn;
    @FXML
    private TableColumn<EventFX, Boolean> reoccuringColumn;

    @FXML
    public Button editDetailsBtn;
    @FXML
    public Button addNewBtn;


    // add new event button - opens EventsDetailAddNewDialog.fxml
    @FXML
    private void handleAddNewBtn(ActionEvent event) throws IOException {

	FXMLLoader loader = new FXMLLoader(getClass().getResource("EventsDetailAddNewDialog.fxml"));
	DialogPane dialogPane = loader.load();

	Dialog dialog = new Dialog();
	dialog.setDialogPane(dialogPane);
	dialog.setResizable(true);

	EventsDetailAddNewDialog edand = loader.getController();

	ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

	dialog.getDialogPane().getButtonTypes().set(0, saveBtn);
	dialog.getDialogPane().getButtonTypes().set(1, cancelBtn);

	Optional<ButtonType> result = dialog.showAndWait();

	if (!result.isPresent()) {

	    // alert is exited, no button has been pressed.

	} else if (result.get() == saveBtn) {

	    Event m = edand.updateEvent();
	    int idEvent = m.getId();
	    EventServiceFunctions.addEvent(m);

	    eventsTableView.getItems().clear();

	    readEventsList();	
	    updateTable();
	    eventsTableView.refresh();

	} else if (result.get() == cancelBtn) {
	    System.out.println("Cancel Button Pressed");
	}
    }


    // edit event detail button - opens EventsDetailDialog.fxml
    @FXML
    private void handleEditDetailsBtn(ActionEvent event) throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("EventsDetailDialog.fxml"));

	EventFX getEvent = eventsTableView.getSelectionModel().getSelectedItem();

	if (getEvent == null) {
	    return;
	}

	int id = getEvent.getId();
	ControllerCommunicator cc = new ControllerCommunicator(id);

	DialogPane dialogPane = loader.load();

	Dialog dialog = new Dialog();
	dialog.setDialogPane(dialogPane);
	dialog.setResizable(true);

	ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

	dialog.getDialogPane().getButtonTypes().set(0, saveBtn);
	dialog.getDialogPane().getButtonTypes().set(1, cancelBtn);
	EventsDetailDialogController eddc = loader.getController();
	Optional<ButtonType> result = dialog.showAndWait();

	if (!result.isPresent()) {

	    // alert is exited, no button has been pressed.

	} else if (result.get() == saveBtn) {

	    Event m;
	    try {
		m = eddc.updateEvent();
		int idEvent = m.getId();
		EventServiceFunctions.updateEvent(idEvent, m);
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	    eventsTableView.getItems().clear();
	    readEventsList();
	    updateTable();
	    eventsTableView.refresh();
	} else if (result.get() == cancelBtn) {
	    System.out.println("Cancel Button Pressed");
	}
    }


    // delete event button 
    @FXML
    private void handleDeleteBtn() {
	Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setTitle("WARNING - DELETING EVENT");
	alert.setHeaderText("THIS CAN NOT BE UNDONE");
	alert.setContentText("DO YOU REALLY WANT TO DELETE THIS EVENT?");

	EventFX event = eventsTableView.getSelectionModel().getSelectedItem();
	if (event == null) {
	    return;}

	Optional<ButtonType> result = alert.showAndWait();

	if (result.get() == ButtonType.OK) {
	    // get ID from item in table view
	    int id = event.getId();
	    // delete from database
	    EventServiceFunctions.deleteEventFromMember(id);
	    EventServiceFunctions.deleteEvent(id);

	    // remove from Tableview
	    eventsTableView.getItems().removeAll(eventsTableView.getSelectionModel().getSelectedItem());
	    eventsTableView.refresh();
	}
    }


    // update eventsTableView
    public void updateTable() {
	// load Data
	if (eventsTableView != null) {
	    eventsTableView.getItems().addAll(olEvents);
	}
    }


    // read list of all events
    public void readEventsList() {
	olEvents.clear();

	List<Event> xmlEvents = new ArrayList<Event>();
	xmlEvents = EventServiceFunctions.getEvents();

	if(xmlEvents != null) {
	    for (Event einM : xmlEvents) {
		olEvents.add(new EventFX(einM));
		System.out.println("CLIENT------------" + "\n" + einM);
	    }
	}
    }


    // initialize eventsTableView columns
    public void initializeColumns() {

	if (idColumn != null) {
	    idColumn.setCellValueFactory(new PropertyValueFactory<EventFX, Integer>("id"));
	    eventTitleColumn.setCellValueFactory(new PropertyValueFactory<EventFX, String>("eventTitle"));
	    eventDescriptionColumn.setCellValueFactory(new PropertyValueFactory<EventFX, String>("eventDescription"));
	    dateColumn.setCellValueFactory(new PropertyValueFactory<EventFX, LocalDate>("date"));
	    eventStartTimeColumn.setCellValueFactory(new PropertyValueFactory<EventFX, LocalTime>("eventStartTime"));
	    eventEndTimeColumn.setCellValueFactory(new PropertyValueFactory<EventFX, LocalTime>("eventEndTime"));
	    additionalNotesColumn
	    .setCellValueFactory(new PropertyValueFactory<EventFX, String>("eventAddidtionalNotes"));
	    reoccuringColumn.setCellValueFactory(new PropertyValueFactory<EventFX, Boolean>("reoccuring"));
	}
    }

    // initialize methods when EventsScreen.fxml is loaded
    public void initialize() {
	readEventsList();
	initializeColumns();
	updateTable();
    }

}
