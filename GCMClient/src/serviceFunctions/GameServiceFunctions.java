package serviceFunctions;

import java.util.List;

import gcmClasses.Game;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


public class GameServiceFunctions {


	private static final String serverURI = "http://localhost:4712/game";


	//GET - get game list
	public static List<Game> getGames() {

		List<Game> games = ClientBuilder.newClient()
				.target(serverURI)
				.path("/gamelist")
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<List<Game>>(){});

		return games;
	}


	//GET - get one game
	public static Game getGame(int id) {

		Game game = ClientBuilder.newClient()
				.target(serverURI)
				.path("/game/" + id)
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<Game>(){});

		return game;
	}


	//Post - add new game 
	public static Response addGame(Game m) {

		Client client = ClientBuilder.newClient();
		return client
				.target(serverURI)
				.path("/addGame")
				.request(MediaType.APPLICATION_XML)
				.post(Entity.entity(m, MediaType.APPLICATION_XML));
	}


	//PUT - update game 
	public static Response updateGame(int id, Game m) {

		Client client = ClientBuilder.newClient();
		return client
				.target(serverURI)
				.path("/updateGame/" + id)
				.request(MediaType.APPLICATION_XML)
				.put(Entity.entity(m, MediaType.APPLICATION_XML));	

	}


	//Delete - delete game 
	public static Response deleteGame(int id) {

		Client client = ClientBuilder.newClient();
		return client
				.target(serverURI)
				.path("/deleteGame/" + id)
				.request(MediaType.APPLICATION_XML)
				.delete();	

	}

}
