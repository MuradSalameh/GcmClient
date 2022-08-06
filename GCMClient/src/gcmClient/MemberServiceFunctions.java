package gcmClient;

import java.util.List;

import org.glassfish.jersey.client.ClientResponse;

import gcmClasses.Member;
import gcmClasses.MemberList;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;


public class MemberServiceFunctions {


	private static final String serverURI = "http://localhost:4712/member";
	
	
	
	public static List<Member> getMembers() {
	
		List<Member> members = ClientBuilder.newClient()
    .target(serverURI)
    .path("/memberlist")
    .request(MediaType.APPLICATION_XML)
    .get(new GenericType<List<Member>>(){});
		
	
		
		return members;
	}
	
	
	
	

	/*
	public static ServiceFunctionsReturnData<MemberList> getMembers() {
		
		

			Client client = ClientBuilder.newClient();
			//Basis URI festlegen
			WebTarget basetarget = client.target(serverURI);
			// URI erweitern f�r die GET Anfrage
			WebTarget resourcetarget = basetarget.path("/memberlist");
			// Daten werden als XML Datenstrom transportiert
			
			Invocation.Builder request = resourcetarget.request(MediaType.APPLICATION_XML);
			// Anfrage an den Server absenden und auf ANtwort warten
			Response response = request.get();
			int status = response.getStatus();
			ServiceFunctionsReturnData<MemberList> sfr = new ServiceFunctionsReturnData<>();
			if(status == Status.OK.getStatusCode()) {
				// aus der Response die MemberList aus XML deserialisieren
				sfr.setData(response.readEntity(MemberList.class));
				sfr.setRc(true);
			}
			else {
				// SQLException aus der response deserialisieren
				sfr.setMeldung(response.readEntity(String.class));
			}
			client.close();
			return sfr;
	

		//return response.readEntity(new GenericType<List<Member>>() {});
	}
	*/
	
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
