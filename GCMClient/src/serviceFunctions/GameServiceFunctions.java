package serviceFunctions;

import java.util.List;

import gcmClasses.Game;
import gcmClasses.Member;
import gcmClasses.Tournament;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class GameServiceFunctions {
	// Methods to send and retrieve data from server 


	private static final String serverURI = "http://localhost:4712/game";

	//get list of all games
	public static List<Game> getGames() {

		List<Game> games = ClientBuilder.newClient().target(serverURI).path("/gamelist")
				.request(MediaType.APPLICATION_XML).get(new GenericType<List<Game>>() {
				});
		return games;
	}

	// get games by member id in MemberGames table
	public static List<Game> getGamesByMemberId(int id) {

		List<Game> games = ClientBuilder.newClient().target(serverURI).path("/gamesByMember/" + id)
				.request(MediaType.APPLICATION_XML).get(new GenericType<List<Game>>() {
				});
		return games;
	}

	//get games by tournament id in Tournamentgame table
	public static List<Game> getGamesByTournamentId(int id) {

		List<Game> games = ClientBuilder.newClient().target(serverURI).path("/getGamesByTournamentId/" + id)
				.request(MediaType.APPLICATION_XML).get(new GenericType<List<Game>>() {
				});
		return games;
	}

	// get game
	public static Game getGame(int id) {

		Game game = ClientBuilder.newClient().target(serverURI).path("/game/" + id).request(MediaType.APPLICATION_XML)
				.get(new GenericType<Game>() {
				});
		return game;
	}

	// add new game
	public static Response addGame(Game m) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/addGame").request(MediaType.APPLICATION_XML)
				.post(Entity.entity(m, MediaType.APPLICATION_XML));
	}

	// assign game to member in MemberGames table
	public static Response addGameToMember(int memberID, int gameID) {

		Member m = new Member();
		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/addGameToMember/" + memberID + "/" + gameID)
				.request(MediaType.APPLICATION_XML).put(Entity.entity(m, MediaType.APPLICATION_XML));

	}

	//assign game to tournament in TournamentGame table
	public static Response addGameToTournament(int gameId, int tournamentId) {

		Tournament m = new Tournament();
		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/addGameToTournament/" + gameId + "/" + tournamentId)
				.request(MediaType.APPLICATION_XML).put(Entity.entity(m, MediaType.APPLICATION_XML));
	}

	// update game
	public static Response updateGame(int id, Game m) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/updateGame/" + id).request(MediaType.APPLICATION_XML)
				.put(Entity.entity(m, MediaType.APPLICATION_XML));

	}

	// delete game from all members in MmberGames table
	public static Response deleteGameFromAllMembers(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteGameFromAllMembers/" + id).request(MediaType.APPLICATION_XML)
				.delete();
	}

	// delete game from all tournaments in TournamentGame table
	public static Response deleteGameFromAllTournaments(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteGameFromAllTournaments/" + id).request(MediaType.APPLICATION_XML)
				.delete();
	}

	// delete game
	public static Response deleteGame(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteGame/" + id).request(MediaType.APPLICATION_XML).delete();

	}

	// delete specific game from specific member in MemberGames table 
	public static Response deleteGameFromMember(int gameid, int memberid) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteGameFromMember/" + gameid + "/" + memberid)
				.request(MediaType.APPLICATION_XML).delete();

	}

	// delete specific game from specific tournament in TournamentGame table
	public static Response deleteGameFromTournament(int gameid, int tournamentid) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteGameFromTournament/" + gameid + "/" + tournamentid)
				.request(MediaType.APPLICATION_XML).delete();

	}

	

}
