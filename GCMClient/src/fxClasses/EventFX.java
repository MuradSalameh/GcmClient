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
		date = new SimpleObjectProperty(serverEvent.getDate());
		eventStartTime = new SimpleObjectProperty(serverEvent.getEventStartTime());
		eventEndTime = new SimpleObjectProperty(serverEvent.getEventEndTime());
		eventAddidtionalNotes = new SimpleStringProperty(serverEvent.getEventAddidtionalNotes());;
		reoccuring = new SimpleBooleanProperty(serverEvent.isReoccuring());
		
		ObservableList<Member> membersOl = FXCollections.observableArrayList(serverEvent.getMembers());
		this.members = new SimpleListProperty<Member>(membersOl);
				
	}





	public Event getServerEvent() {
		return serverEvent;
	}


	public void setServerEvent(Event serverEvent) {
		this.serverEvent = serverEvent;
	}


	public SimpleIntegerProperty getId() {
		return id;
	}


	public void setId(SimpleIntegerProperty id) {
		this.id = id;
	}


	public SimpleStringProperty getEventTitle() {
		return eventTitle;
	}


	public void setEventTitle(SimpleStringProperty eventTitle) {
		this.eventTitle = eventTitle;
	}


	public SimpleStringProperty getEventDescription() {
		return eventDescription;
	}


	public void setEventDescription(SimpleStringProperty eventDescription) {
		this.eventDescription = eventDescription;
	}


	public ObjectProperty<LocalDate> getDate() {
		return date;
	}


	public void setDate(ObjectProperty<LocalDate> date) {
		this.date = date;
	}


	public ObjectProperty<LocalTime> getEventStartTime() {
		return eventStartTime;
	}


	public void setEventStartTime(ObjectProperty<LocalTime> eventStartTime) {
		this.eventStartTime = eventStartTime;
	}


	public ObjectProperty<LocalTime> getEventEndTime() {
		return eventEndTime;
	}


	public void setEventEndTime(ObjectProperty<LocalTime> eventEndTime) {
		this.eventEndTime = eventEndTime;
	}


	public SimpleStringProperty getEventAddidtionalNotes() {
		return eventAddidtionalNotes;
	}


	public void setEventAddidtionalNotes(SimpleStringProperty eventAddidtionalNotes) {
		this.eventAddidtionalNotes = eventAddidtionalNotes;
	}


	public SimpleBooleanProperty getReoccuring() {
		return reoccuring;
	}


	public void setReoccuring(SimpleBooleanProperty reoccuring) {
		this.reoccuring = reoccuring;
	}


	public SimpleListProperty<Member> getMembers() {
		return members;
	}


	public void setMembers(SimpleListProperty<Member> members) {
		this.members = members;
	}
	
	
	
	

}
