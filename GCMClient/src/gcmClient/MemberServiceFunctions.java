package gcmClient;

import java.util.List;

import org.glassfish.jersey.client.ClientResponse;

import gcmClasses.Member;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;


public class MemberServiceFunctions {


	private static final String serverURI = "http://localhost:4712/member";


	//GET - get member list
	public static List<Member> getMembers() {

		List<Member> members = ClientBuilder.newClient()
				.target(serverURI)
				.path("/memberlist")
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<List<Member>>(){});

		return members;
	}

	//GET - get one member
	public static Member getMember(int id) {

		Member member = ClientBuilder.newClient()
				.target(serverURI)
				.path("/member/" + id)
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<Member>(){});

		return member;
	}

	//Post - add new member 
	public static Response addMember(Member m) {
		
		Client client = ClientBuilder.newClient();
	    return client
	      .target(serverURI)
	      .path("/addMember")
	      .request(MediaType.APPLICATION_XML)
	      .post(Entity.entity(m, MediaType.APPLICATION_XML));
	}


	//PUT - update member 
	//	@Path("/updateMember/{id}")
	public static Response updateMember(int id, Member m) {
		
		Client client = ClientBuilder.newClient();
	    return client
	      .target(serverURI)
	      .path("/updateMember/" + id)
	      .request(MediaType.APPLICATION_XML)
	      .put(Entity.entity(m, MediaType.APPLICATION_XML));	
		
	}


	//Delete - delete member 
//	@Path("/deleteMember/{id}")
	public static Response deleteMember(int id) {
		
		Client client = ClientBuilder.newClient();
	    return client
	      .target(serverURI)
	      .path("/deleteMember/" + id)
	      .request(MediaType.APPLICATION_XML)
	      .delete();	
		
	

	}



	/*
	public static ServiceFunctionsReturn postMember(Member m) {
		ServiceFunctionsReturn sfr = new ServiceFunctionsReturn();
		Client client = ClientBuilder.newClient();
		//Basis URI festlegen
		WebTarget basetarget = client.target(basisURI);
		// URI erweitern f�r die GET Anfrage
		WebTarget resourcetarget = basetarget.path("member");
		// Daten werden als XML Datenstrom transportiert
		Invocation.Builder request = resourcetarget.request(MediaType.APPLICATION_XML);
		// Wein Objekt in die Anfrage einsetzen (wird automatisch auf XML serialisiert)
		// Anfrage an den Server absenden und auf ANtwort warten
		Response response = request.post(Entity.xml(m));
		int status = response.getStatus();
		if(status == Status.CREATED.getStatusCode()) {
			sfr.setRc(true);
		}
		else {
			// SQLException aus der response deserialisieren
			sfr.setMeldung(response.readEntity(String.class));
		}
		client.close();
		return sfr;
	}

	public static ServiceFunctionsReturn putMember(Member m) {
		ServiceFunctionsReturn sfr = new ServiceFunctionsReturn();
		return sfr;
	}

	public static ServiceFunctionsReturn deleteMember(int memberId) {
		ServiceFunctionsReturn sfr = new ServiceFunctionsReturn();
		return sfr;
	}
	 */
}
