package serviceFunctions;

import java.util.List;

import gcmClasses.Role;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


public class RoleServiceFunctions {


	private static final String serverURI = "http://localhost:4712/role";


	//GET - get role list
	public static List<Role> getRoles() {

		List<Role> roles = ClientBuilder.newClient()
				.target(serverURI)
				.path("/rolelist")
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<List<Role>>(){});

		return roles;
	}


	//GET - get one role
	public static Role getRole(int id) {

		Role role = ClientBuilder.newClient()
				.target(serverURI)
				.path("/role/" + id)
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<Role>(){});

		return role;
	}


	//Post - add new role 
	public static Response addRole(Role m) {

		Client client = ClientBuilder.newClient();
		return client
				.target(serverURI)
				.path("/addRole")
				.request(MediaType.APPLICATION_XML)
				.post(Entity.entity(m, MediaType.APPLICATION_XML));
	}


	//PUT - update role 
	public static Response updateRole(int id, Role m) {

		Client client = ClientBuilder.newClient();
		return client
				.target(serverURI)
				.path("/updateRole/" + id)
				.request(MediaType.APPLICATION_XML)
				.put(Entity.entity(m, MediaType.APPLICATION_XML));	

	}


	//Delete - delete role 
	public static Response deleteRole(int id) {

		Client client = ClientBuilder.newClient();
		return client
				.target(serverURI)
				.path("/deleteRole/" + id)
				.request(MediaType.APPLICATION_XML)
				.delete();	

	}

}
