package serviceFunctions;

import java.util.List;

import gcmClasses.Partner;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class PartnerServiceFunctions {
	// Methods to send and retrieve data from server 

	private static final String serverURI = "http://localhost:4712/partner";

	// get list of all partners
	public static List<Partner> getPartners() {

		List<Partner> partners = ClientBuilder.newClient().target(serverURI).path("/partnerlist")
				.request(MediaType.APPLICATION_XML).get(new GenericType<List<Partner>>() {
				});

		return partners;
	}

	// get partner
	public static Partner getPartner(int id) {

	    Client client = ClientBuilder.newClient();
	    WebTarget webTarget = client.target(serverURI).path("/partner/" + id);
	    
	    Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_XML);
	    Response response = invocationBuilder.get();
	     
	    Partner partner = response.readEntity(Partner.class);

		return partner;
	}

	// add new partner
	public static Response addPartner(Partner m) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/addPartner").request(MediaType.APPLICATION_XML)
				.post(Entity.entity(m, MediaType.APPLICATION_XML));
	}

	// update partner
	public static Response updatePartner(int id, Partner m) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/updatePartner/" + id).request(MediaType.APPLICATION_XML)
				.put(Entity.entity(m, MediaType.APPLICATION_XML));

	}

	// delete partner
	public static Response deletePartner(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deletePartner/" + id).request(MediaType.APPLICATION_XML).delete();

	}

}
