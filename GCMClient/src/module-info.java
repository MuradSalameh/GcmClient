module FXMAVEN {
	requires javafx.controls;
	requires javafx.graphics;
	
	requires GcmClasses;
	requires javafx.fxml;
	requires javafx.base;
	requires jakarta.ws.rs;
	
	
	
	opens gcmClient to javafx.graphics, javafx.fxml, javafx.base;
	opens fxClasses to javafx.graphics, javafx.fxml, javafx.base;
	
	
}
