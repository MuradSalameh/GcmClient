package serviceFunctions;

import java.util.List;

import gcmClasses.Member;
import gcmClasses.Role;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class RoleServiceFunctions {
	// Methods to send and retrieve data from server 


	private static final String serverURI = "http://localhost:4712/role";

	// get list of all roles
	public static List<Role> getRoles() {

		List<Role> roles = ClientBuilder.newClient().target(serverURI).path("/rolelist")
				.request(MediaType.APPLICATION_XML).get(new GenericType<List<Role>>() {
				});

		return roles;
	}

	// get roles by member id from MemberRoles table
	public static List<Role> getRolesByMemberId(int id) {

		try {
			List<Role> roles = ClientBuilder.newClient().target(serverURI).path("/rolesByMember/" + id)
					.request(MediaType.APPLICATION_XML).get(new GenericType<List<Role>>() {
					});
			return roles;
		} catch (Exception e) {
			return null;
		}
	}

	// get role
	public static Role getRole(int id) {

		Role role = ClientBuilder.newClient().target(serverURI).path("/role/" + id).request(MediaType.APPLICATION_XML)
				.get(new GenericType<Role>() {
				});

		return role;
	}

	//get role with highest id
	public static Role getRoleWithHighestId() {

		Role role = ClientBuilder.newClient().target(serverURI).path("/roleWithHighestId/")
				.request(MediaType.APPLICATION_XML).get(new GenericType<Role>() {
				});

		return role;
	}

	// add new role
	public static Response addRole(Role m) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/addRole").request(MediaType.APPLICATION_XML)
				.post(Entity.entity(m, MediaType.APPLICATION_XML));
	}

	// update role
	public static Response updateRole(int id, Role m) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/updateRole/" + id).request(MediaType.APPLICATION_XML)
				.put(Entity.entity(m, MediaType.APPLICATION_XML));

	}

	// assign role to member in MemberRoles table
	public static Response addRoleToMember(int memberID, int roleID) {
		Member m = new Member();
		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/addRoleToMember/" + memberID + "/" + roleID)
				.request(MediaType.APPLICATION_XML).put(Entity.entity(m, MediaType.APPLICATION_XML));

	}

	// delete role
	public static Response deleteRole(int id) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteRole/" + id).request(MediaType.APPLICATION_XML).delete();

	}

	// delete specific role from  specific member in MemberRoles table
	public static Response deleteRoleFromMember(int roleid, int memberid) {

		Client client = ClientBuilder.newClient();
		return client.target(serverURI).path("/deleteRoleFromMember/" + roleid + "/" + memberid)
				.request(MediaType.APPLICATION_XML).delete();

	}

}
