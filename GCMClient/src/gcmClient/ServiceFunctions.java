package gcmClient;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public class ServiceFunctions {

	
	private static final String basisURI = "http://localhost:4712/vinothek";

	public static ServiceFunctionsReturnData<MemberList> getMember() {
		Client client = ClientBuilder.newClient();
		//Basis URI festlegen
		WebTarget basetarget = client.target(basisURI);
		// URI erweitern f�r die GET Anfrage
		WebTarget resourcetarget = basetarget.path("wein");
		// Daten werden als XML Datenstrom transportiert
		Invocation.Builder request = resourcetarget.request(MediaType.APPLICATION_XML);
		// Anfrage an den Server absenden und auf ANtwort warten
		Response response = request.get();
		int status = response.getStatus();
		ServiceFunctionsReturnData<MemberList> sfr = new ServiceFunctionsReturnData<>();
		if(status == Status.OK.getStatusCode()) {
			// aus der Response die WeinList aus XML deserialisieren
			sfr.setData(response.readEntity(MemberList.class));
			sfr.setRc(true);
		}
		else {
			// SQLException aus der response deserialisieren
			sfr.setMeldung(response.readEntity(String.class));
		}
		client.close();
		return sfr;
	}
	
	public static ServiceFunctionsReturn postWein(Member m) {
		ServiceFunctionsReturn sfr = new ServiceFunctionsReturn();
		Client client = ClientBuilder.newClient();
		//Basis URI festlegen
		WebTarget basetarget = client.target(basisURI);
		// URI erweitern f�r die GET Anfrage
		WebTarget resourcetarget = basetarget.path("wein");
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
	
	public static ServiceFunctionsReturn putWein(Member m) {
		ServiceFunctionsReturn sfr = new ServiceFunctionsReturn();
		return sfr;
	}
	
	public static ServiceFunctionsReturn deleteWein(int memberId) {
		ServiceFunctionsReturn sfr = new ServiceFunctionsReturn();
		return sfr;
	}

}
