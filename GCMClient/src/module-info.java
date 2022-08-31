module FXMAVEN {
	requires javafx.controls;
	requires javafx.graphics;
	

	requires javafx.fxml;
	requires javafx.base;
	requires jakarta.ws.rs;
	requires GcmClasses;

	
	
	
	opens gcmClient to javafx.graphics, javafx.fxml, javafx.base;
	opens fxClasses to javafx.graphics, javafx.fxml, javafx.base;
	
	
}
