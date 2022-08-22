module FXMAVEN {
	requires javafx.controls;
	requires javafx.graphics;
	requires jakarta.ws.rs;
	requires jersey.client;
	requires GcmClasses;
	requires javafx.fxml;
	requires javafx.base;
	
	opens gcmClient to javafx.graphics, javafx.fxml, javafx.base;
	opens fxClasses to javafx.graphics, javafx.fxml, javafx.base;	
}
