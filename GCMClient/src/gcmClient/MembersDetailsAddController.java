package gcmClient;



import java.time.LocalDate;
import fxClasses.MemberFX;
import gcmClasses.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import serviceFunctions.MemberServiceFunctions;


public class MembersDetailsAddController{	
	
	@FXML
	private ObservableList<MemberFX> olMembers = FXCollections.observableArrayList();
	
	@FXML private DialogPane memberDetailsAddDialog;	
	@FXML private Label idLabel;		
	@FXML private TextField clanNameTf;
	@FXML private TextField clanIdTf;
	@FXML private TextField realNameTf;
	@FXML private TextField addressTf;
	@FXML private TextField postCodeTf;
	@FXML private TextField cityTf;
	@FXML private TextField countryTf;
	@FXML private TextField emailTf;
	@FXML private TextField phoneNumberTf;
	@FXML private DatePicker dateDp;

	@FXML
	public TextField getClanNameTf() {
		//this.clanNameTf.setText("");
		
		System.out.println("Return Clanname = " + this.clanNameTf);

		return this.clanNameTf;

	}





	// initialize TextFields
	@FXML
	public void initializeTextFields() {

	
		if(clanNameTf == null) {
			//	idLabel.setText(String.valueOf(ccId));
			this.clanNameTf.setText("Klausiii");
			this.clanIdTf.setText("");
			this.realNameTf.setText("");
			this.addressTf.setText("");
			this.postCodeTf.setText("");
			this.cityTf.setText("");
			this.countryTf.setText("");
			this.emailTf.setText("");
			this.phoneNumberTf.setText("");
			this.dateDp.setValue(LocalDate.of(1980, 1, 1));						
		}

	}
	
	
	
	@FXML
	public Member setNewMember() {			
		//	String clanName = "";
		clanNameTf.textProperty().addListener((observable, oldValue, newValue) -> {
		    System.out.println("textfield changed from " + oldValue + " to " + newValue);
		});

		Member neu = new Member(
				"", 					// clan name
				"",  					// clan id
				"ooooooOoO 1", 				// real name
				"11", 						// address
				"44444", 					// adress post code
				"OOOOOO", 					// city
				"OOOOOO", 					// country
				"OOOOO@email.com", 			// mail
				"+431111111111", 		// phone number
				null, 						// role
				null, 						// socials
				null, 						// games
				null,						// events
				LocalDate.of(1981, 4, 11), 	// birthday
				null);	

		return neu;
	}

	
	public void ready() {
		Member m = setNewMember();
		MemberServiceFunctions.addMember(m);		
	}
}
