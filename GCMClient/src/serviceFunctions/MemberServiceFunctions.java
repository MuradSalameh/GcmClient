package serviceFunctions;

import java.util.List;

import gcmClasses.Member;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class MemberServiceFunctions {

	private static final String serverURI = "http://localhost:4712/member";

	// GET - get member list
	public static List<Member> getMembers() {

		List<Member> members = ClientBuilder.newClient().target(serverURI).path("/memberlist")
				.request(MediaType.APPLICATION_XML).get(new GenericType<List<Member>>() {
				});

		return members;
	}

	// GET - get member list
	public static List<Member> getMembersByTeamId(int id) {

		List<Member> members = ClientBuilder.newClient().target(serverURI).path("/getMembersByTeamId/" + id)
				.request(MediaType.APPLICATION_XML).get(new GenericType<List<Member>>() {
				});

		return members;
	}

	// GET - get one member
	public static Member getMember(int id) {

		Member member = ClientBuilder.newClient().target(serverURI).path("/member/" + id)
				.request(MediaType.APPLICATION_XML).get(new GenericType<Member>() {
				});

		return member;
	}

	// GET - get memberWithHighestId
	public static Member getMemberWithHighestId() {

		Member member = ClientBuilder.newClient().target(serverURI).path("/memberWithHighestId/")
				.request(MediaType.APPLICATION_XML).get(new GenericType<Member>() {
				});

		return member;
	}

	// Post - add new member
	public static Response addMember(Member m) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/addMember/").request(MediaType.APPLICATION_XML)
				.post(Entity.entity(m, MediaType.APPLICATION_XML));
	}

	// PUT - add game to member
	public static Response addMemberToTeam(int memberID, int teamID) {

		Member t = new Member();
		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/addMemberToTeam/" + memberID + "/" + teamID)
				.request(MediaType.APPLICATION_XML).put(Entity.entity(t, MediaType.APPLICATION_XML));
	}

	public static Response deleteMemberFromTeam(int memberid, int teamid) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteMemberFromTeam/" + memberid + "/" + teamid)
				.request(MediaType.APPLICATION_XML).delete();

	}

	// PUT - update member
	public static Response updateMember(int id, Member m) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/updateMember/" + id).request(MediaType.APPLICATION_XML)
				.put(Entity.entity(m, MediaType.APPLICATION_XML));

	}

	// Delete - delete member
	public static Response deleteMember(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteMember/" + id).request(MediaType.APPLICATION_XML).delete();

	}

	// Delete - delete member from events
	public static Response deleteMemberFromEvents(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteMemberFromEvents/" + id).request(MediaType.APPLICATION_XML)
				.delete();

	}

	// Delete - delete member from Teams
	public static Response deleteMemberFromTeams(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteMemberFromTeams/" + id).request(MediaType.APPLICATION_XML)
				.delete();

	}

	// Delete - delete member from Teams
	public static Response deleteMemberFromGames(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteMemberFromGames/" + id).request(MediaType.APPLICATION_XML)
				.delete();

	}

	// Delete - delete role
	public static Response deleteMemberFromRoles(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteMemberFromRoles/" + id).request(MediaType.APPLICATION_XML)
				.delete();

	}

	// Delete - delete Social
	public static Response deleteMemberFromSocials(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteMemberFromSocials/" + id).request(MediaType.APPLICATION_XML)
				.delete();

	}

}
