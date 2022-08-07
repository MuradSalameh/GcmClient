package fxClasses;

import java.time.LocalDate;
import java.time.LocalTime;

import gcmClasses.Event;
import gcmClasses.Member;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EventFX {
	private Event serverEvent;
	private SimpleIntegerProperty id;
	private SimpleStringProperty eventTitle;
	private SimpleStringProperty eventDescription;
	private ObjectProperty<LocalDate> date;
	private ObjectProperty<LocalTime> eventStartTime;
	private ObjectProperty<LocalTime> eventEndTime;
	private SimpleStringProperty eventAddidtionalNotes;
	private SimpleBooleanProperty reoccuring;
	private SimpleListProperty<Member> members;


	public EventFX() {
		super();
	}

	public EventFX(Event serverEvent) {
		super();
		this.serverEvent = serverEvent;
		id = new SimpleIntegerProperty(serverEvent.getId());
		eventTitle = new SimpleStringProperty(serverEvent.getEventTitle());
		eventDescription = new SimpleStringProperty(serverEvent.getEventDescription());
		date = new SimpleObjectProperty<LocalDate>(serverEvent.getDate());
		eventStartTime = new SimpleObjectProperty<LocalTime>(serverEvent.getEventStartTime());
		eventEndTime = new SimpleObjectProperty<LocalTime>(serverEvent.getEventEndTime());
		eventAddidtionalNotes = new SimpleStringProperty(serverEvent.getEventAddidtionalNotes());;
		reoccuring = new SimpleBooleanProperty(serverEvent.isReoccuring());

		ObservableList<Member> membersOl = FXCollections.observableArrayList(serverEvent.getMembers());
		this.members = new SimpleListProperty<Member>(membersOl);

	}

	public Event getServerEvent() {
		return serverEvent;
	}

	//-----------------------
	
	public final SimpleIntegerProperty idProperty() {
		return this.id;
	}

	public final int getId() {
		return this.idProperty().get();
	}

	public void setId(final int id) {
		this.idProperty().set(id);
	}

	//-----------------------
	
	public final SimpleStringProperty eventTitleProperty() {
		return this.eventTitle;
	}

	public final String getEventTitle() {
		return this.eventTitleProperty().get();
	}

	public final void setEventTitle(final String eventTitle) {
		this.eventTitleProperty().set(eventTitle);
	}

	//-----------------------
	
	public final SimpleStringProperty eventDescriptionProperty() {
		return this.eventDescription;
	}

	public final String getEventDescription() {
		return eventDescriptionProperty().get();
	}

	public final void setEventDescription(final String eventDescription) {
		this.eventDescriptionProperty().set(eventDescription);
	}

	//-----------------------

	public final ObjectProperty<LocalDate> dateProperty() {
		return this.date;
	}

	public final LocalDate getDate() {
		return dateProperty().get();
	}

	public final void setDate(final LocalDate date) {
		this.dateProperty().set(date);
	}

	//-----------------------
	
	public final ObjectProperty<LocalTime> eventStartTimeProperty() {
		return this.eventStartTime;
	}

	public final LocalTime getEventStartTime() {
		return eventStartTimeProperty().get();
	}

	public final void setEventStartTime(final LocalTime eventStartTime) {
		this.eventStartTimeProperty().set(eventStartTime);
	}

	//-----------------------
	
	public final ObjectProperty<LocalTime> eventEndTimeProperty() {
		return this.eventEndTime;
	}

	public final LocalTime getEventEndTime() {
		return eventEndTimeProperty().get();
	}

	public final void setEventEndTime(final LocalTime eventEndTime) {
		this.eventEndTimeProperty().set(eventEndTime);
	}

	//-----------------------
	
	public final SimpleStringProperty eventAddidtionalNotesProperty() {
		return this.eventAddidtionalNotes;
	}

	public final String getEventAddidtionalNotes() {
		return eventAddidtionalNotesProperty().get();
	}

	public final void setEventAddidtionalNotes(final String eventAddidtionalNotes) {
		this.eventAddidtionalNotesProperty().set(eventAddidtionalNotes);
	}

	//-----------------------
	
	public final SimpleBooleanProperty reoccuringProperty() {
		return this.reoccuring;
	}
	public final Boolean getReoccuring() {
		return reoccuringProperty().get();
	}

	public final void setReoccuring(final Boolean reoccuring) {
		this.reoccuringProperty().set(reoccuring);
	}

	//-----------------------
	
	public final SimpleListProperty<Member> membersProperty() {
		return this.members;
	}

	public final ObservableList<Member> getMembers() {
		return membersProperty().get();
	}

	public final void setMembers(final ObservableList<Member> members) {
		this.membersProperty().set(members);
	}

}
