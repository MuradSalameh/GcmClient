package serviceFunctions;

import java.util.List;

import gcmClasses.Tournament;
import jakarta.ws.rs.client.AsyncInvoker;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.InvocationCallback;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public class TournamentServiceFunctions {

	private static final String serverURI = "http://localhost:4712/tournament";
	private static String path = "";

	// Methods to send and retrieve data from server 
	
	
	// GET - get tournament list 
	public static List<Tournament> getTournaments() {
	    path = "/tournamentlist";
	    Client client = ClientBuilder.newClient();

	    WebTarget webTarget = client.target(serverURI).path(path);

	    Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
	    // set headers and other stuff
	    
	    List<Tournament> tournaments = invocationBuilder.get(new GenericType<List<Tournament>>() {
		});
	    
	    AsyncInvoker asyncInvoker = invocationBuilder.async();

	    asyncInvoker.get(new InvocationCallback<Response>() {

	        @Override
	        public void completed(Response response) {
	            if (response.getStatusInfo().equals(Status.OK)) {
	        	System.out.println("Successful");
	               // parse the response in success scenario
	            } else {
	        	 System.out.println("Failed");
	               // parse the response if error response is received from server
	            }
	            client.close();
	        }

	        @Override
	        public void failed(Throwable throwable) {
	            System.out.println("An error occurred while calling API");
	            throwable.printStackTrace();
	            client.close();
	        }
	    });	
		return tournaments;
	}
	

	// GET - get one tournament
	public static Tournament getTournament(int id) {
	    path = "/tournament/" + id;
	    Client client = ClientBuilder.newClient();

	    WebTarget webTarget = client.target(serverURI).path(path);

	    Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
	    // set headers and other stuff
	    
	   
   
	    AsyncInvoker asyncInvoker = invocationBuilder.async();
	    
	    Tournament tournament = invocationBuilder.get(new GenericType<Tournament>() {
		});

	    asyncInvoker.get(new InvocationCallback<Response>() {

	        @Override
	        public void completed(Response response) {
	            if (response.getStatusInfo().equals(Status.OK)) {
	        	System.out.println("Successful");
	               // parse the response in success scenario
	            } else {
	               // parse the response if error response is received from server
	            }
	            client.close();
	        }

	        @Override
	        public void failed(Throwable throwable) {
	            System.out.println("An error occurred while calling API");
	            throwable.printStackTrace();
	            client.close();
	        }
	    });	
	   
		return tournament;
	}

	// Post - add new tournament
	public static Response addTournament(Tournament m) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/addTournament").request(MediaType.APPLICATION_XML)
				.post(Entity.entity(m, MediaType.APPLICATION_XML));
	}

	// PUT - update tournament
	public static Response updateTournament(int id, Tournament m) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/updateTournament/" + id).request(MediaType.APPLICATION_XML)
				.put(Entity.entity(m, MediaType.APPLICATION_XML));

	}

	// Delete - delete tournament
	public static Response deleteTournament(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteTournament/" + id).request(MediaType.APPLICATION_XML).delete();

	}

	// delete tournament assignments from all teams in TournamentsTeams DB table
	public static Response deleteTournamentsFromTeams(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteTournamentFromTeams/" + id).request(MediaType.APPLICATION_XML)
				.delete();

	}

	// delete tournament assignements from all games in TournamentsGame DB table
	public static Response deleteTournamentFromGame(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteTournamentFromGame/" + id).request(MediaType.APPLICATION_XML)
				.delete();

	}

}
