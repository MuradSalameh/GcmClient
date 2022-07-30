package gcmClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import gcmClasses.Game;
import gcmClasses.Member;
import gcmClasses.Role;
import gcmClasses.Social;
import gcmClasses.Team;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MemberFX {
	private Member serverMember;	
	private SimpleIntegerProperty id;			
	private SimpleStringProperty clanName;	
	private SimpleStringProperty clanId;		
	private SimpleStringProperty realName;	
	private SimpleStringProperty address;
	private SimpleStringProperty addressPostCode;
	private SimpleStringProperty addressCity;	
	private SimpleStringProperty country;	
	private SimpleStringProperty email;	
	private SimpleStringProperty phoneNumber;		
	private SimpleListProperty<Role> roles;	
	private SimpleListProperty<Social> socials;	
	private SimpleListProperty<Game> games;		
	private ObjectProperty<LocalDate> birthday;	
	private SimpleListProperty<Team> teams;
	
	public MemberFX() {
		super();
	}

	public MemberFX(Member serverMember) {
		super();
		this.serverMember = serverMember;
		id = new SimpleIntegerProperty(serverMember.getId());
		clanName = new SimpleStringProperty(serverMember.getClanName());
		clanId = new SimpleStringProperty(serverMember.getClanId());
		realName = new SimpleStringProperty(serverMember.getRealName());
		address = new SimpleStringProperty(serverMember.getAddress());
		addressPostCode = new SimpleStringProperty(serverMember.getAddressPostCode());
		addressCity = new SimpleStringProperty(serverMember.getAddressCity());
		country = new SimpleStringProperty(serverMember.getCountry());
		email = new SimpleStringProperty(serverMember.getEmail());
		phoneNumber = new SimpleStringProperty(serverMember.getPhoneNumber());
		
		//Prüfen ob Korekt
		ObservableList<Role> rolesOl = FXCollections.observableArrayList(serverMember.getRoles());
		roles = new SimpleListProperty<Role>(rolesOl);

		ObservableList<Social> socialsOl = FXCollections.observableArrayList(serverMember.getSocials());
		socials = new SimpleListProperty<Social>(socialsOl);
		
		ObservableList<Game> gamesOl = FXCollections.observableArrayList(serverMember.getGames());
		games = new SimpleListProperty<Game>(gamesOl);
		
		birthday = new SimpleObjectProperty<LocalDate>(serverMember.getBirthday());
		
		ObservableList<Team> teamsOl = FXCollections.observableArrayList(serverMember.getTeams());
		teams = new SimpleListProperty<Team>(teamsOl);		
	}

	public Member getServerMember() {
		return serverMember;
	}

	public final SimpleIntegerProperty idProperty() {
		return this.id;
	}

	public final int getId() {
		return this.idProperty().get();
	}

	/* Don't allow to alter Id
	public void setId(final int id) {
		this.idProperty().set(id);
	}
	*/
	
	//-----------------------
	public final SimpleStringProperty clanNameProperty() {
		return this.clanName;
	}

	public final String getClanName() {
		return this.clanNameProperty().get();
	}

	public void setClanName(final String clanName) {
		this.clanNameProperty().set(clanName);
	}
	
	//----------------------

	public final SimpleStringProperty clanIdProperty() {
		return this.clanId;
	}
	
	public final String getClanId() {
		return this.clanIdProperty().get();
	}

	public final void setClanId(final String clanId) {
		this.clanIdProperty().set(clanId);
	}
	
	//-------------------------

	public final SimpleStringProperty realNameProperty() {
		return this.realName;
	}
	public final String getRealName() {
		return this.realNameProperty().get();
	}

	public final void setRealName(final String realName) {
		this.realNameProperty().set(realName);
	}
	
	//-------------------------
	
	public final SimpleStringProperty addressProperty() {
		return this.address;
	}

	public final String getAddress() {
		return this.addressProperty().get();
	}

	public final void setAddress(final String address) {
		this.addressProperty().set(address);
	}
	
	//------------------------

	public final SimpleStringProperty addressPostCodeProperty() {
		return this.addressPostCode;
	}
	
	public final String getAddressPostCode() {
		return this.addressPostCodeProperty().get();
	}

	public void setAddressPostCode(final String addressPostCode) {
		this.addressPostCodeProperty().set(addressPostCode);
	}
	
	//---------------------------
	public final SimpleStringProperty addressCityProperty() {
		return this.addressCity;
	}

	public final String getAddressCity() {
		return addressCityProperty().get();
	}

	public void setAddressCity(final String addressCity) {
		this.addressCityProperty().set(addressCity);
	}
	
	//---------------------------------
	
	public final SimpleStringProperty countryProperty() {
		return this.country;
	}

	public final String getCountry() {
		return this.countryProperty().get();
	}

	public final void setCountry(final String country) {
		this.countryProperty().set(country);
	}
	
	//---------------------------------
	
	public final SimpleStringProperty emailProperty() {
		return this.email;
	}

	public final String getEmail() {
		return emailProperty().get();
	}

	public void setEmail(final String email) {
		this.emailProperty().set(email);
	}
	
	//------------------------
	
	public final SimpleStringProperty phoneNumberProperty() {
		return this.phoneNumber;
	}

	public final String getPhoneNumber() {
		return phoneNumberProperty().get();
	}

	public final void setPhoneNumber(final String phoneNumber) {
		this.phoneNumberProperty().set(phoneNumber);
	}
	
	//------------------------
	
	public final SimpleListProperty rolesProperty() {
		return this.roles;
	}

	public final ObservableList<Role> getRoles() {
		return this.rolesProperty().get();
	}

	public void setRoles(SimpleListProperty<Role> roles) {
		this.roles = roles;
	}

	public SimpleListProperty<Social> getSocials() {
		return socials;
	}

	public void setSocials(SimpleListProperty<Social> socials) {
		this.socials = socials;
	}

	public SimpleListProperty<Game> getGames() {
		return games;
	}

	public void setGames(SimpleListProperty<Game> games) {
		this.games = games;
	}

	public ObjectProperty<LocalDate> getBirthday() {
		return birthday;
	}

	public void setBirthday(ObjectProperty<LocalDate> birthday) {
		this.birthday = birthday;
	}

	public SimpleListProperty<Team> getTeams() {
		return teams;
	}

	public void setTeams(SimpleListProperty<Team> teams) {
		this.teams = teams;
	}
	
	
	
	
	


}
