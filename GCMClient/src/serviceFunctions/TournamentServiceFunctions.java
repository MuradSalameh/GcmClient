package serviceFunctions;

import java.util.List;

import gcmClasses.Tournament;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


public class TournamentServiceFunctions {


	private static final String serverURI = "http://localhost:4712/tournament";


	//GET - get tournament list
	public static List<Tournament> getTournaments() {

		List<Tournament> tournaments = ClientBuilder.newClient()
				.target(serverURI)
				.path("/tournamentlist")
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<List<Tournament>>(){});

		return tournaments;
	}


	//GET - get one tournament
	public static Tournament getTournament(int id) {

		Tournament tournament = ClientBuilder.newClient()
				.target(serverURI)
				.path("/tournament/" + id)
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<Tournament>(){});

		return tournament;
	}


	//Post - add new tournament 
	public static Response addTournament(Tournament m) {

		Client client = ClientBuilder.newClient();
		return client
				.target(serverURI)
				.path("/addTournament")
				.request(MediaType.APPLICATION_XML)
				.post(Entity.entity(m, MediaType.APPLICATION_XML));
	}


	//PUT - update tournament 
	public static Response updateTournament(int id, Tournament m) {

		Client client = ClientBuilder.newClient();
		return client
				.target(serverURI)
				.path("/updateTournament/" + id)
				.request(MediaType.APPLICATION_XML)
				.put(Entity.entity(m, MediaType.APPLICATION_XML));	

	}


	//Delete - delete tournament 
	public static Response deleteTournament(int id) {

		Client client = ClientBuilder.newClient();
		return client
				.target(serverURI)
				.path("/deleteTournament/" + id)
				.request(MediaType.APPLICATION_XML)
				.delete();	

	}

}
