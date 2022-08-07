package serviceFunctions;

import java.util.List;

import gcmClasses.RevenueType;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


public class RevenueTypeServiceFunctions {


	private static final String serverURI = "http://localhost:4712/revenueType";


	//GET - get revenueType list
	public static List<RevenueType> getRevenueTypes() {

		List<RevenueType> revenueTypes = ClientBuilder.newClient()
				.target(serverURI)
				.path("/revenueTypelist")
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<List<RevenueType>>(){});

		return revenueTypes;
	}


	//GET - get one revenueType
	public static RevenueType getRevenueType(int id) {

		RevenueType revenueType = ClientBuilder.newClient()
				.target(serverURI)
				.path("/revenueType/" + id)
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<RevenueType>(){});

		return revenueType;
	}


	//Post - add new revenueType 
	public static Response addRevenueType(RevenueType m) {

		Client client = ClientBuilder.newClient();
		return client
				.target(serverURI)
				.path("/addRevenueType")
				.request(MediaType.APPLICATION_XML)
				.post(Entity.entity(m, MediaType.APPLICATION_XML));
	}


	//PUT - update revenueType 
	public static Response updateRevenueType(int id, RevenueType m) {

		Client client = ClientBuilder.newClient();
		return client
				.target(serverURI)
				.path("/updateRevenueType/" + id)
				.request(MediaType.APPLICATION_XML)
				.put(Entity.entity(m, MediaType.APPLICATION_XML));	

	}


	//Delete - delete revenueType 
	public static Response deleteRevenueType(int id) {

		Client client = ClientBuilder.newClient();
		return client
				.target(serverURI)
				.path("/deleteRevenueType/" + id)
				.request(MediaType.APPLICATION_XML)
				.delete();	

	}

}
