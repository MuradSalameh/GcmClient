package gcmClient;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import gcmClasses.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import serviceFunctions.EventServiceFunctions;

public class TournamentDetailDialogController extends Dialog<ButtonType> implements Initializable {

	private int ccId = ControllerCommunicator.getId();

	@FXML
	final DialogPane dialogPane = getDialogPane();
	@FXML
	private Dialog dialog;
	@FXML
	private BorderPane eventEditBp;
	@FXML
	private Label idLabel;
	@FXML
	private TextField eventTitleTF;
	@FXML
	private TextField eventDescriptionTF;
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
	private CheckBox reoccuringCB;
	@FXML
	private TextField additionalNotesTF;
	@FXML
	ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	@FXML
	ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

	private String startHour;
	private String startMinute;

	private String endHour;
	private String endMinute;

	public Event loadEvent() {

		Event event = EventServiceFunctions.getEvent(ccId);
		return event;
	}

	public Event getSelectedEvent() {

		if (loadEvent() != null) {
			Event event = loadEvent();
			return event;
		} else {
			Event newEvent = new Event("", // event title
					"", // eventdescription
					LocalDate.of(2022, 01, 31), // tournament date
					LocalTime.of(01, 11), // start time
					LocalTime.of(02, 22), // end time
					"", // additional notes
					false, // reoccuring
					null); // members

			return newEvent;
		}
	}

	public void initializeTextFields() {
		Event event = loadEvent();

		idLabel.setText(String.valueOf(ccId));

		startHour = String.valueOf(event.getEventStartTime().getHour());
		startMinute = String.valueOf(event.getEventStartTime().getMinute());

		endHour = String.valueOf(event.getEventEndTime().getHour());
		endMinute = String.valueOf(event.getEventEndTime().getMinute());

		// Event TextFields
		eventTitleTF.setText(event.getEventTitle());
		eventDescriptionTF.setText(event.getEventDescription());
		additionalNotesTF.setText(event.getEventAddidtionalNotes());
		dateDp.setValue(event.getDate());

		startHourTF.setText(startHour);
		startMinuteTF.setText(startMinute);

		endHourTF.setText(endHour);
		endMinuteTF.setText(endMinute);

		reoccuringCB.setSelected(event.isReoccuring());

		eventTitleTF.setPromptText("Enter Event Title");
		eventDescriptionTF.setPromptText("Enter Description");
		additionalNotesTF.setPromptText("Enter Additional Notes");
		startHourTF.setPromptText("00");
		startMinuteTF.setPromptText("00");
		endHourTF.setPromptText("00");
		endMinuteTF.setPromptText("00");
		reoccuringCB.setSelected(event.isReoccuring());
	}

	public Event updateEvent() {
		Event event = loadEvent();

		event.setEventTitle(eventTitleTF.getText());
		event.setEventDescription(eventDescriptionTF.getText());
		event.setEventAddidtionalNotes(additionalNotesTF.getText());
		event.setDate(dateDp.getValue());
		event.setReoccuring(reoccuringCB.isSelected());

		// ------ Time converters -------

		// Start time
		startHour = String.valueOf(startHourTF.getText());
		startMinute = String.valueOf(startMinuteTF.getText());

		int startHourInt = Integer.parseInt(startHour);
		int startMinuteInt = Integer.parseInt(startMinute);
		LocalTime start = LocalTime.of(startHourInt, startMinuteInt);

		event.setEventStartTime(start);

		// End Time
		endHour = String.valueOf(endHourTF.getText());
		endMinute = String.valueOf(endMinuteTF.getText());

		int endHourInt = Integer.parseInt(endHour);
		int endMinuteInt = Integer.parseInt(endMinute);
		LocalTime end = LocalTime.of(endHourInt, endMinuteInt);

		event.setEventEndTime(end);

		// ---------------------------------
		return event;
	}

	/*
	 * @FXML public void handleEventEditSaveBtn(ActionEvent e) { int id =
	 * loadEvent().getId();
	 * 
	 * if (id != 0) { Event updatedEvent = updateEvent();
	 * EventServiceFunctions.updateEvent(id, updatedEvent); } else { Event
	 * updatedEvent = updateEvent(); EventServiceFunctions.addEvent(updatedEvent);
	 * 
	 * } }
	 * 
	 * @FXML public void handleEventEditNewBtn(ActionEvent e) { Event event =
	 * updateEvent(); Integer id = null;
	 * 
	 * if (id == null) { // Set Event ID Label idLabel.setText(String.valueOf(id));
	 * 
	 * eventTitleTF.setPromptText("Enter Event Title");
	 * eventDescriptionTF.setPromptText("Enter Description");
	 * additionalNotesTF.setPromptText("Enter Additional Notes");
	 * 
	 * startHourTF.setPromptText("00"); startMinuteTF.setPromptText("00");
	 * endHourTF.setPromptText("00"); endMinuteTF.setPromptText("00");
	 * reoccuringCB.setSelected(event.isReoccuring()); } }
	 * 
	 */

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadEvent();
		initializeTextFields();
	}
}
