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

	private static final String serverURI = "http://localhost:4712/game";

	// GET - get expenseType list
	public static List<Game> getGames() {

		List<Game> games = ClientBuilder.newClient().target(serverURI).path("/gamelist")
				.request(MediaType.APPLICATION_XML).get(new GenericType<List<Game>>() {
				});
		return games;
	}

	// GET - get game list by memberId
	public static List<Game> getGamesByMemberId(int id) {

		List<Game> games = ClientBuilder.newClient().target(serverURI).path("/gamesByMember/" + id)
				.request(MediaType.APPLICATION_XML).get(new GenericType<List<Game>>() {
				});
		return games;
	}

	// GET - get game list by tournamentId
	public static List<Game> getGamesByTournamentId(int id) {

		List<Game> games = ClientBuilder.newClient().target(serverURI).path("/getGamesByTournamentId/" + id)
				.request(MediaType.APPLICATION_XML).get(new GenericType<List<Game>>() {
				});
		return games;
	}

	// GET - get one game
	public static Game getGame(int id) {

		Game game = ClientBuilder.newClient().target(serverURI).path("/game/" + id).request(MediaType.APPLICATION_XML)
				.get(new GenericType<Game>() {
				});
		return game;
	}

	// Post - add new game
	public static Response addGame(Game m) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/addGame").request(MediaType.APPLICATION_XML)
				.post(Entity.entity(m, MediaType.APPLICATION_XML));
	}

	// PUT - add game to member
	public static Response addGameToMember(int memberID, int gameID) {

		Member m = new Member();
		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/addGameToMember/" + memberID + "/" + gameID)
				.request(MediaType.APPLICATION_XML).put(Entity.entity(m, MediaType.APPLICATION_XML));

	}

	// PUT - add game to member
	public static Response addGameToTournament(int gameId, int tournamentId) {

		Tournament m = new Tournament();
		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/addGameToTournament/" + gameId + "/" + tournamentId)
				.request(MediaType.APPLICATION_XML).put(Entity.entity(m, MediaType.APPLICATION_XML));
	}

	// PUT - update game
	public static Response updateGame(int id, Game m) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/updateGame/" + id).request(MediaType.APPLICATION_XML)
				.put(Entity.entity(m, MediaType.APPLICATION_XML));

	}

	// Delete - delete game
	public static Response deleteGameFromAllMembers(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteGameFromAllMembers/" + id).request(MediaType.APPLICATION_XML)
				.delete();
	}

	// Delete - delete game
	public static Response deleteGameFromAllTournaments(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteGameFromAllTournaments/" + id).request(MediaType.APPLICATION_XML)
				.delete();
	}

	public static Response deleteGame(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteGame/" + id).request(MediaType.APPLICATION_XML).delete();

	}

	public static Response deleteGameFromMember(int gameid, int memberid) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteGameFromMember/" + gameid + "/" + memberid)
				.request(MediaType.APPLICATION_XML).delete();

	}

	public static Response deleteGameFromTournament(int gameid, int tournamentid) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteGameFromTournament/" + gameid + "/" + tournamentid)
				.request(MediaType.APPLICATION_XML).delete();

	}

	public static Response deleteGameFromGenre(int gameid, int genreid) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteGameFromGenre/" + gameid + "/" + genreid)
				.request(MediaType.APPLICATION_XML).delete();

	}

}
