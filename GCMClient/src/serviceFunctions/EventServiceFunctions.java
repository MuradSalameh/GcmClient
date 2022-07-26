package serviceFunctions;

import java.util.List;

import gcmClasses.Event;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;



public class EventServiceFunctions {

	private static final String serverURI = "http://localhost:4712/event";

	// get list of all events 
	public static List<Event> getEvents() {
	    

		List<Event> events = ClientBuilder.newClient().target(serverURI).path("/eventlist")
				.request(MediaType.APPLICATION_XML).get(new GenericType<List<Event>>() {
				});

		return events;
	}

	//get event
	public static Event getEvent(int id) {

	    Client client = ClientBuilder.newClient();
	    WebTarget webTarget = client.target(serverURI).path("/event/" + id);
	    
	    Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_XML);
	    Response response = invocationBuilder.get();
	     
	    Event event = response.readEntity(Event.class);

		return event;
	}

	// get events by member id from MemberEvents table
	public static List<Event> getEventsByMemberId(int id) {

		List<Event> events = ClientBuilder.newClient().target(serverURI).path("/eventsByMember/" + id)
				.request(MediaType.APPLICATION_XML).get(new GenericType<List<Event>>() {
				});

		return events;
	}

	// add new event
	public static Response addEvent(Event m) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/addEvent").request(MediaType.APPLICATION_XML)
				.post(Entity.entity(m, MediaType.APPLICATION_XML));
	}

	// update event
	public static Response updateEvent(int id, Event m) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/updateEvent/" + id).request(MediaType.APPLICATION_XML)
				.put(Entity.entity(m, MediaType.APPLICATION_XML));
	}

	// delete event
	public static Response deleteEvent(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteEvent/" + id).request(MediaType.APPLICATION_XML).delete();
	}

	// delete event from all members in MemberEvents table
	public static Response deleteEventFromMember(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteEventFromMember/" + id).request(MediaType.APPLICATION_XML)
				.delete();
	}
}
