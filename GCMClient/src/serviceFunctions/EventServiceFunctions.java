package serviceFunctions;

import java.util.List;

import gcmClasses.Event;
import gcmClasses.Game;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


public class EventServiceFunctions {


	private static final String serverURI = "http://localhost:4712/event";


	//GET - get event list
	public static List<Event> getEvents() {

		List<Event> events = ClientBuilder.newClient()
				.target(serverURI)
				.path("/eventlist")
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<List<Event>>(){});

		return events;
	}


	//GET - get one event
	public static Event getEvent(int id) {

		Event event = ClientBuilder.newClient()
				.target(serverURI)
				.path("/event/" + id)
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<Event>(){});

		return event;
	}
	
	//GET - get event list by memberId
		public static List<Event> getEventsByMemberId(int id) {

			List<Event> events = ClientBuilder.newClient()
					.target(serverURI)
					.path("/eventsByMember/" +id)
					.request(MediaType.APPLICATION_XML)
					.get(new GenericType<List<Event>>(){});

			return events;
		}




	//Post - add new event 
	public static Response addEvent(Event m) {

		Client client = ClientBuilder.newClient();
		return client
				.target(serverURI)
				.path("/addEvent")
				.request(MediaType.APPLICATION_XML)
				.post(Entity.entity(m, MediaType.APPLICATION_XML));
	}


	//PUT - update event 
	public static Response updateEvent(int id, Event m) {

		Client client = ClientBuilder.newClient();
		return client
				.target(serverURI)
				.path("/updateEvent/" + id)
				.request(MediaType.APPLICATION_XML)
				.put(Entity.entity(m, MediaType.APPLICATION_XML));	

	}


	//Delete - delete event 
	public static Response deleteEvent(int id) {

		Client client = ClientBuilder.newClient();
		return client
				.target(serverURI)
				.path("/deleteEvent/" + id)
				.request(MediaType.APPLICATION_XML)
				.delete();	

	}
	//Delete - delete event 
	public static Response deleteEventFromMember(int id) {
		
		Client client = ClientBuilder.newClient();
		return client
				.target(serverURI)
				.path("/deleteEventFromMember/" + id)
				.request(MediaType.APPLICATION_XML)
				.delete();	
		
	}

}
