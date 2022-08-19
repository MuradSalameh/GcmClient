package gcmClient;

import java.net.URL;
import java.util.ResourceBundle;

import gcmClasses.Partner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import serviceFunctions.PartnerServiceFunctions;

public class PartnerDetailDialogController extends Dialog<ButtonType> implements Initializable {

	private int ccId = ControllerCommunicator.getId();

	@FXML
	final DialogPane dialogPane = getDialogPane();
	@FXML
	private Dialog dialog;
	@FXML
	private BorderPane partnerEditBp;
	@FXML
	private Label idLabel;
	@FXML
	private TextField companyNameTF;
	@FXML
	private TextField contactPersonNameTF;
	@FXML
	private TextField contactPersonPhoneTF;
	@FXML
	private TextField contactPersonMailTF;
	@FXML
	private TextField firstNameTF;
	@FXML
	private TextField lastNameTF;
	@FXML
	private TextField adressStreetTF;
	@FXML
	private TextField adressNumberTF;
	@FXML
	private TextField adressPostCodeTF;
	@FXML
	private TextField adressCityTF;
	@FXML
	private TextField countryTF;
	@FXML
	private TextField emailTF;
	@FXML
	private TextField phoneNumberTF;

	@FXML
	ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	@FXML
	ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

	public Partner loadPartner() {

		Partner partner = PartnerServiceFunctions.getPartner(ccId);
		return partner;
	}

	public Partner getSelectedPartner() {

		if (loadPartner() != null) {
			Partner partner = loadPartner();
			return partner;
		} else {
			Partner newPartner = new Partner("", // companyName
					"", // Contact name
					"", // contact phone
					"", // contact mail
					"", // first name
					"", // last name
					"", // adress street
					"", // street number
					"", // post code
					"", // city
					"", // country
					"", // email
					"");// phone number

			return newPartner;
		}
	}

	public void initializeTextFields() {
		Partner partner = loadPartner();

		idLabel.setText(String.valueOf(ccId));

		// Partner TextFields
		companyNameTF.setText(partner.getCompanyName());
		contactPersonNameTF.setText(partner.getContactPersonName());
		contactPersonPhoneTF.setText(partner.getContactPersonPhone());
		contactPersonMailTF.setText(partner.getContactPersonMail());
		firstNameTF.setText(partner.getFirstName());
		lastNameTF.setText(partner.getLastName());
		adressStreetTF.setText(partner.getAdressStreet());
		adressNumberTF.setText(partner.getAdressNumber());
		adressPostCodeTF.setText(partner.getAdressPostCode());
		adressCityTF.setText(partner.getAdressCity());
		countryTF.setText(partner.getCountry());
		emailTF.setText(partner.getEmail());
		phoneNumberTF.setText(partner.getPhoneNumber());

		companyNameTF.setPromptText("Enter Company Name");
		contactPersonNameTF.setPromptText("Enter Contct Person Name");
		contactPersonPhoneTF.setPromptText("Enter Contact Phone Number");
		contactPersonMailTF.setPromptText("Enter Contact email");
		firstNameTF.setPromptText("Enter First Name");
		lastNameTF.setPromptText("Enter Last Name");
		adressStreetTF.setPromptText("Enter Adress Street");
		adressNumberTF.setPromptText("Enter Adress Number");
		adressPostCodeTF.setPromptText("Enter Post Code");
		adressCityTF.setPromptText("Enter City");
		countryTF.setPromptText("Enter Country");
		emailTF.setPromptText("Enter Email");
		phoneNumberTF.setPromptText("Enter Phone Number");
	}

	public Partner updatePartner() {
		Partner partner = loadPartner();

		partner.setCompanyName(companyNameTF.getText());
		partner.setContactPersonName(contactPersonNameTF.getText());
		partner.setContactPersonPhone(contactPersonPhoneTF.getText());
		partner.setContactPersonMail(contactPersonMailTF.getText());
		partner.setFirstName(firstNameTF.getText());
		partner.setLastName(lastNameTF.getText());
		partner.setAdressStreet(adressStreetTF.getText());
		partner.setAdressNumber(adressNumberTF.getText());
		partner.setAdressPostCode(adressPostCodeTF.getText());
		partner.setAdressCity(adressCityTF.getText());
		partner.setCountry(countryTF.getText());
		partner.setEmail(emailTF.getText());
		partner.setPhoneNumber(phoneNumberTF.getText());

		return partner;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadPartner();
		initializeTextFields();
	}
}
