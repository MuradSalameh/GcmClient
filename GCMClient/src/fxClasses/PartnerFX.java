package fxClasses;

import gcmClasses.Partner;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PartnerFX {
	
	private Partner serverPartner;
	private SimpleIntegerProperty id;		
	private SimpleStringProperty companyName;
	private SimpleStringProperty contactPersonName;
	private SimpleStringProperty contactPersonPhone;
	private SimpleStringProperty contactPersonMail;
	private SimpleStringProperty firstName;
	private SimpleStringProperty lastName;
	private SimpleStringProperty adressStreet;
	private SimpleStringProperty adressNumber;
	private SimpleStringProperty adressPostCode;
	private SimpleStringProperty adressCity;
	private SimpleStringProperty country;	
	private SimpleStringProperty email;	
	private SimpleStringProperty phoneNumber;
	
	public PartnerFX() {
		super();
	}

	public PartnerFX(Partner serverPartner) {
		super();
		this.serverPartner = serverPartner;
		id = new SimpleIntegerProperty(serverPartner.getId());
		companyName = new SimpleStringProperty(serverPartner.getCompanyName());
		contactPersonName = new SimpleStringProperty(serverPartner.getContactPersonName());
		contactPersonPhone = new SimpleStringProperty(serverPartner.getContactPersonPhone());
		contactPersonMail = new SimpleStringProperty(serverPartner.getContactPersonMail());
		firstName = new SimpleStringProperty(serverPartner.getFirstName());
		lastName = new SimpleStringProperty(serverPartner.getLastName());
		adressStreet = new SimpleStringProperty(serverPartner.getAdressStreet());
		adressNumber = new SimpleStringProperty(serverPartner.getAdressNumber());
		adressPostCode = new SimpleStringProperty(serverPartner.getAdressPostCode());
		adressCity = new SimpleStringProperty(serverPartner.getAdressCity());
		country = new SimpleStringProperty(serverPartner.getCountry());
		email = new SimpleStringProperty(serverPartner.getEmail());
		phoneNumber = new SimpleStringProperty(serverPartner.getPhoneNumber());		

	
		
	}

	public Partner getServerPartner() {
		return serverPartner;
	}

	//-----------------------


	public final SimpleIntegerProperty idProperty() {
		return this.id;
	}

	public final int getId() {
		return this.idProperty().get();
	}

	
	public void setId(final int id) {
		this.idProperty().set(id);
	}
	
	//-----------------------
	
	public final SimpleStringProperty companyNameProperty() {
		return this.companyName;
	}

	public final String getCompanyName() {
		return companyNameProperty().get();
	}

	public final void setCompanyName(final String companyName) {
		this.companyNameProperty().set(companyName);
	}

	//-----------------------
	
	public final SimpleStringProperty contactPersonNameProperty() {
		return this.contactPersonName;
	}


	public final String getContactPersonName() {
		return contactPersonNameProperty().get();
	}

	public final void setContactPersonName(final String contactPersonName) {
		this.contactPersonNameProperty().set(contactPersonName);
	}

	//-----------------------
	public final SimpleStringProperty contactPersonPhoneProperty() {
		return this.contactPersonPhone;
	}


	public final String getContactPersonPhone() {
		return contactPersonPhoneProperty().get();
	}

	public final void setContactPersonPhone(final String contactPersonPhone) {
		this.contactPersonPhoneProperty().set(contactPersonPhone);
	}

	//-----------------------
	public final SimpleStringProperty contactPersonMailProperty() {
		return this.contactPersonMail;
	}


	public final String getContactPersonMail() {
		return contactPersonMailProperty().get(); 
	}

	public final void setContactPersonMail(final String contactPersonMail) {
		this.contactPersonMailProperty().set(contactPersonMail);
	}

	//-----------------------
	public final SimpleStringProperty firstNameProperty() {
		return this.firstName;
	}


	public final String getFirstName() {
		return firstNameProperty().get();
	}

	public final void setFirstName(final String firstName) {
		this.firstNameProperty().set(firstName);
	}

	//-----------------------
	public final SimpleStringProperty lastNameProperty() {
		return this.lastName;
	}


	public final String getLastName() {
		return lastNameProperty().get();
	}

	public final void setLastName(final String lastName) {
		this.lastNameProperty().set(lastName);
	}

	//-----------------------
	public final SimpleStringProperty adressStreetProperty() {
		return this.adressStreet;
	}


	public final String getAdressStreet() {
		return adressStreetProperty().get();
	}

	public final void setAdressStreet(final String adressStreet) {
		this.adressStreetProperty().set(adressStreet);
	}

	//-----------------------
	public final SimpleStringProperty adressNumberProperty() {
		return this.adressNumber;
	}


	public final String getAdressNumber() {
		return adressNumberProperty().get();
	}

	public final void setAdressNumber(final String adressNumber) {
		this.adressNumberProperty().set(adressNumber);
	}

	//-----------------------
	public final SimpleStringProperty adressPostCodeProperty() {
		return this.adressPostCode;
	}


	public final String getAdressPostCode() {
		return adressPostCodeProperty().get();
	}

	public final void setAdressPostCode(final String adressPostCode) {
		this.adressPostCodeProperty().set(adressPostCode);
	}

	//-----------------------
	public final SimpleStringProperty adressCityProperty() {
		return this.adressCity;
	}


	public final String getAdressCity() {
		return adressCityProperty().get();
	}

	public final void setAdressCity(final String adressCity) {
		this.adressCityProperty().set(adressCity);
	}

	//-----------------------
	
	public final SimpleStringProperty countryProperty() {
		return this.country;
	}


	public final String getCountry() {
		return countryProperty().get();
	}

	public final void setCountry(final String country) {
		this.countryProperty().set(country);
	}

	//-----------------------
	public final SimpleStringProperty emailProperty() {
		return this.email;
	}


	public final String getEmail() {
		return emailProperty().get();
	}

	public final void setEmail(final String email) {
		this.emailProperty().set(email);
	}

	//-----------------------
	public final SimpleStringProperty phoneNumberProperty() {
		return this.phoneNumber;
	}


	public final String getPhoneNumber() {
		return phoneNumberProperty().get();
	}

	public final void setPhoneNumber(final String phoneNumber) {
		this.phoneNumberProperty().set(phoneNumber);
	}


	
	
	
	
}
