package tests;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;



import fxClasses.EventFX;
import gcmClasses.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import serviceFunctions.EventServiceFunctions;




public class EventServiceFunctionsTest {
	public static  ObservableList<EventFX> olEvents = FXCollections.observableArrayList();

	public static void main(String[] args) {	

		int id = 2;
		int id2 = 5;
		int id3 = 5;

		//--------- addEvent() Test -----------//

//				addEvent();
//				addEvent();
//				addEvent();
//				addEvent();

		

		//--------- getEvents() Test to get a List of all events in database-----------//

				getEventList();


		
		//--------- deleteEvent() Test -----------//

//	deleteEvent(id);



		//--------- getEvent() Test to get one specific event by id -----------//

//				getEventTest(id);



		//--------- updateEvent() Test -----------//

//				String s = "CLLIIIEEEENNNT UUPPDDAATTEE";
//				updateEventTest(id,s);


		
	}	
	public static void addEvent() {
		Event test = new Event(
				"EVENT", 					// event title
				"event descr", 					// eventdescription
				LocalDate.of(1990, 8, 30),	// tournament date
				LocalTime.of(22,58),		// start time
				LocalTime.of(2,30), 		// end time
				"lorem ipsum", 			// additional notes
				false, 						// reoccuring
				null);						// members

		EventServiceFunctions.addEvent(test);
	}


	public static void updateEventTest(int id, String s) {	
		//get event from database
		Event m = EventServiceFunctions.getEvent(id);

		// set new value for clan name
		m.setEventTitle(s);

		//update Event m  in Datenbank updaten
		EventServiceFunctions.updateEvent(id, m);

		System.out.println(m);
	}



	public static void deleteEvent(int id) {
		EventServiceFunctions.deleteEvent(id);

		Event event = EventServiceFunctions.getEvent(id);
		System.out.println(event);

	}


	public static void getEventTest(int id) {
		System.out.println("CLIENT------------" 
				+ "\n" 
				+ EventServiceFunctions.getEvent(id)); 
	}


	public static void getEventList() {
		List<Event> xmlEvents = new ArrayList<Event>();
		xmlEvents = (List<Event>) EventServiceFunctions.getEvents();

		for(Event einM : xmlEvents) {
			olEvents.add(new EventFX(einM));
			System.out.println("CLIENT------------" + "\n" + einM);
		}		
	}



}

