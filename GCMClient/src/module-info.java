module FXMAVEN {
	requires javafx.controls;
	requires javafx.graphics;
	requires jakarta.ws.rs;
	requires jersey.client;
	requires GcmClasses;
	requires javafx.fxml;
	
	opens gcmClient to javafx.graphics, javafx.fxml;
}
