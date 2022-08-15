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
	private Member member;
	ControllerCommunicator cnm;
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
	public Member newMember() {
		Member neu = new Member(
				"Clan Name", 					// clan name
				"Clan ID",  					// clan id
				"Real Name", 				// real name
				"Address + Nr", 						// address
				"PostvCode", 					// adress post code
				"City", 					// city
				"Country", 					// country
				"Email", 			// mail
				"Phone Number", 		// phone number
				null, 						// role
				null, 						// socials
				null, 						// games
				null,						// events
				LocalDate.of(1981, 4, 11), 	// birthday
				null);	
		//MemberServiceFunctions.addMember(neu);
		return neu;
	}



	// initialize TextFields
	@FXML
	public void initializeTextFields() {
		

	//	member = MemberServiceFunctions.getMemberWithHighestId();

		if(clanNameTf != null) {
		//	idLabel.setText(String.valueOf(member.getId()));
		//		idLabel.setText("-");
			this.clanNameTf.setText(member.getClanName());
			this.clanIdTf.setText(member.getClanId());
			this.realNameTf.setText(member.getRealName());
			this.addressTf.setText(member.getAddress());
			this.postCodeTf.setText(member.getAddressPostCode());
			this.cityTf.setText(member.getAddressCity());
			this.countryTf.setText(member.getCountry());
			this.emailTf.setText(member.getEmail());
			this.phoneNumberTf.setText(member.getPhoneNumber());
			this.dateDp.setValue(member.getBirthday());	

			setNewMember();
			
		}

	}



	@FXML
	public Member setNewMember() {	
		 member = newMember();

		TextField clanNameTf2 = new TextField("");


		if(clanNameTf != null) {
			clanNameTf.textProperty().addListener((observable, oldValue, newValue) -> {

				clanNameTf2.setText(newValue);
				member.setClanName(newValue);

				System.out.println("ClanName changed to " + clanNameTf2.getText());
			});
		}

		
	
		System.out.println("String Test " + member);
	
		return member;
	}





	public ControllerCommunicator getNewMemberId() {
		ControllerCommunicator cnm = new ControllerCommunicator(member.getId());
		return cnm;	
	}

	public void initialize() {
		//newMember();
		initializeTextFields();
	}


	//	public void ready() {
	Member m = setNewMember();
	//MemberServiceFunctions.addMember(m);		
	//	}
	//	
	public void cancel() {

	}
}
