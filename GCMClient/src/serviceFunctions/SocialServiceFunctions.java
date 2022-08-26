package serviceFunctions;

import java.util.List;

import gcmClasses.Member;
import gcmClasses.Social;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class SocialServiceFunctions {
	// Methods to send and retrieve data from server 


	private static final String serverURI = "http://localhost:4712/social";

	// get list of all socials
	public static List<Social> getSocials() {

		List<Social> socials = ClientBuilder.newClient().target(serverURI).path("/sociallist")
				.request(MediaType.APPLICATION_XML).get(new GenericType<List<Social>>() {
				});

		return socials;
	}

	//get socials by member id in MemberSocials table
	public static List<Social> getSocialsByMemberId(int id) {

		List<Social> socials = ClientBuilder.newClient().target(serverURI).path("/socialsByMember/" + id)
				.request(MediaType.APPLICATION_XML).get(new GenericType<List<Social>>() {
				});

		return socials;
	}

	// get social
	public static Social getSocial(int id) {

		Social social = ClientBuilder.newClient().target(serverURI).path("/social/" + id)
				.request(MediaType.APPLICATION_XML).get(new GenericType<Social>() {
				});

		return social;
	}

	// get social with highest id
	public static Social getSocialWithHighestId() {

		Social social = ClientBuilder.newClient().target(serverURI).path("/socialWithHighestId/")
				.request(MediaType.APPLICATION_XML).get(new GenericType<Social>() {
				});

		return social;
	}

	// add new social
	public static Response addSocial(Social m) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/addSocial").request(MediaType.APPLICATION_XML)
				.post(Entity.entity(m, MediaType.APPLICATION_XML));
	}

	// update social
	public static Response updateSocial(int id, Social m) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/updateSocial/" + id).request(MediaType.APPLICATION_XML)
				.put(Entity.entity(m, MediaType.APPLICATION_XML));

	}

	// assign social to member in MemberSocials table
	public static Response addSocialToMember(int memberID, int socialID) {

		Member m = new Member();
		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/addSocialToMember/" + memberID + "/" + socialID)
				.request(MediaType.APPLICATION_XML).put(Entity.entity(m, MediaType.APPLICATION_XML));

	}

	// delete social
	public static Response deleteSocial(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteSocial/" + id).request(MediaType.APPLICATION_XML).delete();

	}

}
