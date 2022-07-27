module GCMClient {
	requires javafx.controls;
	requires javafx.graphics;
	requires jakarta.ws.rs;
	requires jersey.client;
	requires GcmClasses;
	
	opens gcmClient to javafx.graphics, javafx.fxml;
}
