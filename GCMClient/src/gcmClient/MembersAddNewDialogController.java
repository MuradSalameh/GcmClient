package gcmClient;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import gcmClasses.Member;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class MembersAddNewDialogController extends Dialog<ButtonType> implements Initializable {


	@FXML
	final DialogPane dialogPane = getDialogPane();
	@FXML
	private Dialog dialog;
	@FXML
	private BorderPane memberEditBp;

	@FXML
	ButtonType cancelBtn = new ButtonType("Cancellus", ButtonData.CANCEL_CLOSE);
	@FXML
	ButtonType saveBtn = new ButtonType("Speichii", ButtonData.OK_DONE);

	@FXML
	private Label idLabel;

	// Member TextFields -----

	@FXML
	private TextField clanNameTf;
	@FXML
	private TextField clanIdTf;
	@FXML
	private TextField realNameTf;
	@FXML
	private TextField addressTf;
	@FXML
	private TextField postCodeTf;
	@FXML
	private TextField cityTf;
	@FXML
	private TextField countryTf;
	@FXML
	private TextField emailTf;
	@FXML
	private TextField phoneNumberTf;
	@FXML
	private DatePicker dateDp;

	// get Member from DB -----
	public Member loadMember() {
		Member member = new Member("", // clan name
				"", // clan id
				"", // real name
				"", // address
				"", // adress post code
				"", // city
				"", // country
				"", // mail
				"", // phone number
				null, // role
				null, // socials
				null, // games
				null, // events
				LocalDate.now(), // birthday
				null); // teams

		return member;
	}

	// initialize TextFields -----

	public void initializeTextFields() {
		Member member = loadMember();

		idLabel.setText("null");

		clanNameTf.setPromptText("Enter Clan Name");
		clanIdTf.setPromptText("Enter Clan ID");
		realNameTf.setPromptText("Enter Real Name");
		addressTf.setPromptText("Enter Adress");
		postCodeTf.setPromptText("Enter Post Code");
		cityTf.setPromptText("Enter City");
		countryTf.setPromptText("Enter Country");
		emailTf.setPromptText("Enter Email");
		phoneNumberTf.setPromptText("Enter Phone Number");

	}

	// update Member TextFields -----

	public Member updateMemberDetails() {
		Member member = loadMember();

		member.setClanName(clanNameTf.getText());
		member.setClanId(clanIdTf.getText());
		member.setRealName(realNameTf.getText());
		member.setAddress(addressTf.getText());
		member.setAddressPostCode(postCodeTf.getText());
		member.setAddressCity(cityTf.getText());
		member.setCountry(countryTf.getText());
		member.setEmail(emailTf.getText());
		member.setPhoneNumber(phoneNumberTf.getText());
		member.setBirthday(dateDp.getValue());

		return member;
	}

	//initialize methods when MembersAddNewDialog.fxml is loaded
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadMember();
		initializeTextFields();
	}
}
