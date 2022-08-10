package serviceFunctions;

import java.util.List;

import gcmClasses.Social;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


public class SocialServiceFunctions {


	private static final String serverURI = "http://localhost:4712/social";


	//GET - get social list
	public static List<Social> getSocials() {

		List<Social> socials = ClientBuilder.newClient()
				.target(serverURI)
				.path("/sociallist")
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<List<Social>>(){});

		return socials;
	}

	//GET - get social list by memberId
	public static List<Social> getSocialsByMemberId(int id) {

		List<Social> socials = ClientBuilder.newClient()
				.target(serverURI)
				.path("/socialsByMember/" +id)
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<List<Social>>(){});

		return socials;
	}


	//GET - get one social
	public static Social getSocial(int id) {

		Social social = ClientBuilder.newClient()
				.target(serverURI)
				.path("/social/" + id)
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<Social>(){});

		return social;
	}


	//Post - add new social 
	public static Response addSocial(Social m) {

		Client client = ClientBuilder.newClient();
		return client
				.target(serverURI)
				.path("/addSocial")
				.request(MediaType.APPLICATION_XML)
				.post(Entity.entity(m, MediaType.APPLICATION_XML));
	}


	//PUT - update social 
	public static Response updateSocial(int id, Social m) {

		Client client = ClientBuilder.newClient();
		return client
				.target(serverURI)
				.path("/updateSocial/" + id)
				.request(MediaType.APPLICATION_XML)
				.put(Entity.entity(m, MediaType.APPLICATION_XML));	

	}


	//Delete - delete social 
	public static Response deleteSocial(int id) {

		Client client = ClientBuilder.newClient();
		return client
				.target(serverURI)
				.path("/deleteSocial/" + id)
				.request(MediaType.APPLICATION_XML)
				.delete();	

	}

}
