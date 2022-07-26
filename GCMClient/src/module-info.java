module FXMAVEN {
	requires javafx.controls;
	requires javafx.graphics;
	requires jakarta.ws.rs;
	requires jersey.client;
	
	opens gcmClient to javafx.graphics, javafx.fxml;
}
