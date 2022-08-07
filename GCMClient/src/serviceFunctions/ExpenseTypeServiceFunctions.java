package serviceFunctions;

import java.util.List;

import gcmClasses.ExpenseType;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


public class ExpenseTypeServiceFunctions {


	private static final String serverURI = "http://localhost:4712/expenseType";


	//GET - get expenseType list
	public static List<ExpenseType> getExpenseTypes() {

		List<ExpenseType> expenseTypes = ClientBuilder.newClient()
				.target(serverURI)
				.path("/expenseTypelist")
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<List<ExpenseType>>(){});

		return expenseTypes;
	}


	//GET - get one expenseType
	public static ExpenseType getExpenseType(int id) {

		ExpenseType expenseType = ClientBuilder.newClient()
				.target(serverURI)
				.path("/expenseType/" + id)
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<ExpenseType>(){});

		return expenseType;
	}


	//Post - add new expenseType 
	public static Response addExpenseType(ExpenseType m) {

		Client client = ClientBuilder.newClient();
		return client
				.target(serverURI)
				.path("/addExpenseType")
				.request(MediaType.APPLICATION_XML)
				.post(Entity.entity(m, MediaType.APPLICATION_XML));
	}


	//PUT - update expenseType 
	public static Response updateExpenseType(int id, ExpenseType m) {

		Client client = ClientBuilder.newClient();
		return client
				.target(serverURI)
				.path("/updateExpenseType/" + id)
				.request(MediaType.APPLICATION_XML)
				.put(Entity.entity(m, MediaType.APPLICATION_XML));	

	}


	//Delete - delete expenseType 
	public static Response deleteExpenseType(int id) {

		Client client = ClientBuilder.newClient();
		return client
				.target(serverURI)
				.path("/deleteExpenseType/" + id)
				.request(MediaType.APPLICATION_XML)
				.delete();	

	}

}
