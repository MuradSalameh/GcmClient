package serviceFunctions;

import java.util.List;

import gcmClasses.Expense;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class ExpenseServiceFunctions {

	private static final String serverURI = "http://localhost:4712/expense";

	// GET - get expense list
	public static List<Expense> getExpenses() {

		List<Expense> expenses = ClientBuilder.newClient().target(serverURI).path("/expenselist")
				.request(MediaType.APPLICATION_XML).get(new GenericType<List<Expense>>() {
				});

		return expenses;
	}

	// GET - get one expense
	public static Expense getExpense(int id) {

		Expense expense = ClientBuilder.newClient().target(serverURI).path("/expense/" + id)
				.request(MediaType.APPLICATION_XML).get(new GenericType<Expense>() {
				});

		return expense;
	}

	// Post - add new expense
	public static Response addExpense(Expense m) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/addExpense").request(MediaType.APPLICATION_XML)
				.post(Entity.entity(m, MediaType.APPLICATION_XML));
	}

	// PUT - update expense
	public static Response updateExpense(int id, Expense m) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/updateExpense/" + id).request(MediaType.APPLICATION_XML)
				.put(Entity.entity(m, MediaType.APPLICATION_XML));
	}

	// Delete - delete expense
	public static Response deleteExpense(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteExpense/" + id).request(MediaType.APPLICATION_XML).delete();
	}

}
