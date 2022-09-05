package serviceFunctions;

import java.util.List;

import gcmClasses.Revenue;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class RevenueServiceFunctions {

	private static final String serverURI = "http://localhost:4712/revenue";
	// Methods to send and retrieve data from server 


	// get list of all revenues 
	public static List<Revenue> getRevenues() {

		List<Revenue> revenues = ClientBuilder.newClient().target(serverURI).path("/revenuelist")
				.request(MediaType.APPLICATION_XML).get(new GenericType<List<Revenue>>() {
				});

		return revenues;
	}

	// get revenue
	public static Revenue getRevenue(int id) {
	    
	    Client client = ClientBuilder.newClient();
	    WebTarget webTarget = client.target(serverURI).path("/revenue/" + id);
	    
	    Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_XML);
	    Response response = invocationBuilder.get();
	     
	    Revenue revenue = response.readEntity(Revenue.class);

		return revenue;
	}

	// add new revenue
	public static Response addRevenue(Revenue m) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/addRevenue").request(MediaType.APPLICATION_XML)
				.post(Entity.entity(m, MediaType.APPLICATION_XML));
	}

	// update revenue
	public static Response updateRevenue(int id, Revenue m) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/updateRevenue/" + id).request(MediaType.APPLICATION_XML)
				.put(Entity.entity(m, MediaType.APPLICATION_XML));

	}

	// delete revenue
	public static Response deleteRevenue(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteRevenue/" + id).request(MediaType.APPLICATION_XML).delete();

	}

}
