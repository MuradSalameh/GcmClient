package serviceFunctions;

import java.util.List;

import gcmClasses.Team;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


public class TeamServiceFunctions {


	private static final String serverURI = "http://localhost:4712/team";


	//GET - get team list
	public static List<Team> getTeams() {

		List<Team> teams = ClientBuilder.newClient()
				.target(serverURI)
				.path("/teamlist")
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<List<Team>>(){});

		return teams;
	}


	//GET - get one team
	public static Team getTeam(int id) {

		Team team = ClientBuilder.newClient()
				.target(serverURI)
				.path("/team/" + id)
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<Team>(){});

		return team;
	}


	//Post - add new team 
	public static Response addTeam(Team m) {

		Client client = ClientBuilder.newClient();
		return client
				.target(serverURI)
				.path("/addTeam")
				.request(MediaType.APPLICATION_XML)
				.post(Entity.entity(m, MediaType.APPLICATION_XML));
	}


	//PUT - update team 
	public static Response updateTeam(int id, Team m) {

		Client client = ClientBuilder.newClient();
		return client
				.target(serverURI)
				.path("/updateTeam/" + id)
				.request(MediaType.APPLICATION_XML)
				.put(Entity.entity(m, MediaType.APPLICATION_XML));	

	}


	//Delete - delete team 
	public static Response deleteTeam(int id) {

		Client client = ClientBuilder.newClient();
		return client
				.target(serverURI)
				.path("/deleteTeam/" + id)
				.request(MediaType.APPLICATION_XML)
				.delete();	

	}

}
