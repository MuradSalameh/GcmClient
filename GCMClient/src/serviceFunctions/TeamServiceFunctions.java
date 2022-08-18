package serviceFunctions;

import java.util.List;

import gcmClasses.Member;
import gcmClasses.Team;
import gcmClasses.Tournament;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class TeamServiceFunctions {

	private static final String serverURI = "http://localhost:4712/team";

	// GET - get team list
	public static List<Team> getTeams() {

		List<Team> teams = ClientBuilder.newClient().target(serverURI).path("/teamlist")
				.request(MediaType.APPLICATION_XML).get(new GenericType<List<Team>>() {
				});

		return teams;
	}

	public static List<Team> getTeamsByMemberId(int id) {

		List<Team> teams = ClientBuilder.newClient().target(serverURI).path("/teamsByMember/" + id)
				.request(MediaType.APPLICATION_XML).get(new GenericType<List<Team>>() {
				});

		return teams;
	}

	public static List<Team> getTeamsByTournamentId(int id) {

		List<Team> teams = ClientBuilder.newClient().target(serverURI).path("/getTeamsByTournamentId/" + id)
				.request(MediaType.APPLICATION_XML).get(new GenericType<List<Team>>() {
				});

		return teams;
	}

	public static List<Member> getMembersByTeamId(int id) {

		List<Member> members = ClientBuilder.newClient().target(serverURI).path("/membersByTeam/" + id)
				.request(MediaType.APPLICATION_XML).get(new GenericType<List<Member>>() {
				});

		return members;
	}

	// GET - get one team
	public static Team getTeam(int id) {

		Team team = ClientBuilder.newClient().target(serverURI).path("/team/" + id).request(MediaType.APPLICATION_XML)
				.get(new GenericType<Team>() {
				});

		return team;
	}

	// Post - add new team
	public static Response addTeam(Team m) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/addTeam").request(MediaType.APPLICATION_XML)
				.post(Entity.entity(m, MediaType.APPLICATION_XML));
	}

	// PUT - add geam to member
	public static Response addTeamToTournament(int geamId, int tournamentId) {
		Tournament m = new Tournament();
		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/addTeamToTournament/" + geamId + "/" + tournamentId)
				.request(MediaType.APPLICATION_XML).put(Entity.entity(m, MediaType.APPLICATION_XML));
	}

	// PUT - update team
	public static Response updateTeam(int id, Team m) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/updateTeam/" + id).request(MediaType.APPLICATION_XML)
				.put(Entity.entity(m, MediaType.APPLICATION_XML));

	}

	// Delete - delete team
	public static Response deleteTeam(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteTeam/" + id).request(MediaType.APPLICATION_XML).delete();

	}

	// Delete - delete team from member
	public static Response deleteTeamFromMember(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteTeamFromMember/" + id).request(MediaType.APPLICATION_XML).delete();

	}

	// Delete - delete team from member
	public static Response deleteTeamFromTournaments(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteTeamFromTournaments/" + id).request(MediaType.APPLICATION_XML)
				.delete();

	}

	public static Response deleteTeamFromTournament(int teamid, int tournamentid) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteTeamFromTournament/" + teamid + "/" + tournamentid)
				.request(MediaType.APPLICATION_XML).delete();

	}

}
