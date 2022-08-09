package gcmClient;

public class ControllerCommunicator {
	// ControllerCommunictor Class for storing and 
	//passing Values between Controllers.
	
	private static int id;

	public ControllerCommunicator(int recievedId) {
		super();
		id = recievedId;
	}

	public static int getId() {
		return id;
	}

	public static void setId(int recievedId) {
		id = recievedId;
	}
	

}
