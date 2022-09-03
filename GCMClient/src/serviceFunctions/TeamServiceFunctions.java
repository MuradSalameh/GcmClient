package serviceFunctions;

import java.util.List;

import gcmClasses.Member;
import gcmClasses.Team;
import gcmClasses.Tournament;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class TeamServiceFunctions {
    
	// Methods to send and retrieve data from server 


	private static final String serverURI = "http://localhost:4712/team";

	// get list of all teams
	public static List<Team> getTeams() {
System.out.println("getTeams");
		List<Team> teams = ClientBuilder.newClient().target(serverURI).path("/teamlist")
				.request(MediaType.APPLICATION_XML).get(new GenericType<List<Team>>() {
				});

		return teams;
	}

	
	// get teams by member id  from MemberTeam table
	public static List<Team> getTeamsByMemberId(int id) {
	    System.out.println("getTeamsByMemberId" + id);
	    
		List<Team> teams = ClientBuilder.newClient().target(serverURI).path("/teamsByMember/" + id)
				.request(MediaType.APPLICATION_XML).get(new GenericType<List<Team>>() {
				});				
		return teams;
	}

	
	// get teams by tournament id from TournamensTeams table
	public static List<Team> getTeamsByTournamentId(int id) {
	    System.out.println("getTeamsByTournamentId" + id);

		List<Team> teams = ClientBuilder.newClient().target(serverURI).path("/getTeamsByTournamentId/" + id)
				.request(MediaType.APPLICATION_XML).get(new GenericType<List<Team>>() {
				});

		return teams;
	}

	
	// get members by team id from MemberTeams table
	public static List<Member> getMembersByTeamId(int id) {
	    System.out.println("getMembersByTeamId" + id);

		List<Member> members = ClientBuilder.newClient().target(serverURI).path("/membersByTeam/" + id)
				.request(MediaType.APPLICATION_XML).get(new GenericType<List<Member>>() {
				});

		return members;
	}

	// get team
	public static Team getTeam(int id) {
	    System.out.println("getTeam" + id);

	    Client client = ClientBuilder.newClient();
	    WebTarget webTarget = client.target(serverURI).path("/team/" + id);
	    
	    Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_XML);
	    Response response = invocationBuilder.get();
	     
	    Team team = response.readEntity(Team.class);

		return team;
	}

	// add new team
	public static Response addTeam(Team m) {
	    System.out.println("addTeam" + m);

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/addTeam").request(MediaType.APPLICATION_XML)
				.post(Entity.entity(m, MediaType.APPLICATION_XML));
	}

	// assign team to tournament in TournamentsTeams table
	public static Response addTeamToTournament(int geamId, int tournamentId) {
	    System.out.println("addTeamToTournament" + " "+ geamId + " " +  tournamentId);

		Tournament m = new Tournament();
		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/addTeamToTournament/" + geamId + "/" + tournamentId)
				.request(MediaType.APPLICATION_XML).put(Entity.entity(m, MediaType.APPLICATION_XML));
	}

	// update team
	public static Response updateTeam(int id, Team m) {
	    System.out.println("updateTeam" + " "+ id + " " +  m);

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/updateTeam/" + id).request(MediaType.APPLICATION_XML)
				.put(Entity.entity(m, MediaType.APPLICATION_XML));

	}

	// delete team
	public static Response deleteTeam(int id) {
	    System.out.println("deleteTeam" + " "+ id );

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteTeam/" + id).request(MediaType.APPLICATION_XML).delete();

	}

	// delete team from all members in MemberTeams table
	public static Response deleteTeamFromMember(int id) {
	    System.out.println("deleteTeamFromMember" + " "+ id );

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteTeamFromMember/" + id).request(MediaType.APPLICATION_XML).delete();

	}

	// delete team from all tournaments in TournamentsTeams table
	public static Response deleteTeamFromTournaments(int id) {
	    System.out.println("deleteTeamFromTournaments" + " "+ id );

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteTeamFromTournaments/" + id).request(MediaType.APPLICATION_XML)
				.delete();

	}

	
	//delete specific team from specific tournament
	public static Response deleteTeamFromTournament(int teamid, int tournamentid) {
	    System.out.println("deleteTeamFromTournament" + " "+  teamid + " " + tournamentid);

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteTeamFromTournament/" + teamid + "/" + tournamentid)
				.request(MediaType.APPLICATION_XML).delete();

	}

}
