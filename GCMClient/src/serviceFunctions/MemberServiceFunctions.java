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

	// get list of all members
	public static List<Member> getMembers() {

		List<Member> members = ClientBuilder.newClient().target(serverURI).path("/memberlist")
				.request(MediaType.APPLICATION_XML).get(new GenericType<List<Member>>() {
				});

		return members;
	}

	// get list of all members who have birthday today
	public static List<Member> getTodaysMembersBirthdays() {

		List<Member> members = ClientBuilder.newClient().target(serverURI).path("/getTodaysMembersBirthdays")
				.request(MediaType.APPLICATION_XML).get(new GenericType<List<Member>>() {
				});

		return members;
	}

	// get members by team id from MemberTeams table
	public static List<Member> getMembersByTeamId(int id) {
	    
	  
	    	System.out.println("getMembersByTeamId" + " " + id);
		List<Member> members = ClientBuilder.newClient().target(serverURI).path("/getMembersByTeamId/" + id)
				.request(MediaType.APPLICATION_XML).get(new GenericType<List<Member>>() {
				});
		
		

		return members;
	}

	
	// get member
	public static Member getMember(int id) {

		Member member = ClientBuilder.newClient().target(serverURI).path("/member/" + id)
				.request(MediaType.APPLICATION_XML).get(new GenericType<Member>() {
				});

		return member;
	}

	// get member with highest id
	public static Member getMemberWithHighestId() {

		Member member = ClientBuilder.newClient().target(serverURI).path("/memberWithHighestId/")
				.request(MediaType.APPLICATION_XML).get(new GenericType<Member>() {
				});

		return member;
	}

	//  add new member
	public static Response addMember(Member m) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/addMember/").request(MediaType.APPLICATION_XML)
				.post(Entity.entity(m, MediaType.APPLICATION_XML));
	}

	// assign member to team in MemberTeam table
	public static Response addMemberToTeam(int memberID, int teamID) {

		Member t = new Member();
		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/addMemberToTeam/" + memberID + "/" + teamID)
				.request(MediaType.APPLICATION_XML).put(Entity.entity(t, MediaType.APPLICATION_XML));
	}

	// delete specific member from specific team in MemberTeam table
	public static Response deleteMemberFromTeam(int memberid, int teamid) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteMemberFromTeam/" + memberid + "/" + teamid)
				.request(MediaType.APPLICATION_XML).delete();

	}

	// update member
	public static Response updateMember(int id, Member m) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/updateMember/" + id).request(MediaType.APPLICATION_XML)
				.put(Entity.entity(m, MediaType.APPLICATION_XML));

	}

	// delete member
	public static Response deleteMember(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteMember/" + id).request(MediaType.APPLICATION_XML).delete();

	}

	// delete member from all events in MemberEvents table
	public static Response deleteMemberFromEvents(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteMemberFromEvents/" + id).request(MediaType.APPLICATION_XML)
				.delete();

	}

	// delete member from all teams in MemberTeam table
	public static Response deleteMemberFromTeams(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteMemberFromTeams/" + id).request(MediaType.APPLICATION_XML)
				.delete();

	}

	// delete member from all games in MemberGames table
	public static Response deleteMemberFromGames(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteMemberFromGames/" + id).request(MediaType.APPLICATION_XML)
				.delete();

	}

	// delete member from all roles in MemberRoles table
	public static Response deleteMemberFromRoles(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteMemberFromRoles/" + id).request(MediaType.APPLICATION_XML)
				.delete();

	}

	//delete member from all socials in MemberSocials table
	public static Response deleteMemberFromSocials(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteMemberFromSocials/" + id).request(MediaType.APPLICATION_XML)
				.delete();

	}

}
