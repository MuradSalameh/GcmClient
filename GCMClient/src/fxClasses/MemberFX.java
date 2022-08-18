package fxClasses;

import java.time.LocalDate;

import gcmClasses.Event;
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
	private SimpleListProperty<Event> events;

	public MemberFX() {
		super();
	}

	public MemberFX(Member serverMember) {
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

		ObservableList<Role> rolesOl = FXCollections.observableArrayList(serverMember.getRoles());
		this.roles = new SimpleListProperty<Role>(rolesOl);

		ObservableList<Social> socialsOl = FXCollections.observableArrayList(serverMember.getSocials());
		this.socials = new SimpleListProperty<Social>(socialsOl);

		ObservableList<Game> gamesOl = FXCollections.observableArrayList(serverMember.getGames());
		this.games = new SimpleListProperty<Game>(gamesOl);

		birthday = new SimpleObjectProperty<LocalDate>(serverMember.getBirthday());

		ObservableList<Event> eventsOl = FXCollections.observableArrayList(serverMember.getEvents());
		this.events = new SimpleListProperty<Event>(eventsOl);

		ObservableList<Team> teamsOl = FXCollections.observableArrayList(serverMember.getTeams());
		this.teams = new SimpleListProperty<Team>(teamsOl);
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

	public void setId(final int id) {
		this.idProperty().set(id);
	}

	// -----------------------
	public final SimpleStringProperty clanNameProperty() {
		return this.clanName;
	}

	public final String getClanName() {
		return this.clanNameProperty().get();
	}

	public void setClanName(final String clanName) {
		this.clanNameProperty().set(clanName);
	}

	// ----------------------

	public final SimpleStringProperty clanIdProperty() {
		return this.clanId;
	}

	public final String getClanId() {
		return this.clanIdProperty().get();
	}

	public final void setClanId(final String clanId) {
		this.clanIdProperty().set(clanId);
	}

	// -------------------------

	public final SimpleStringProperty realNameProperty() {
		return this.realName;
	}

	public final String getRealName() {
		return this.realNameProperty().get();
	}

	public final void setRealName(final String realName) {
		this.realNameProperty().set(realName);
	}

	// -------------------------

	public final SimpleStringProperty addressProperty() {
		return this.address;
	}

	public final String getAddress() {
		return this.addressProperty().get();
	}

	public final void setAddress(final String address) {
		this.addressProperty().set(address);
	}

	// ------------------------

	public final SimpleStringProperty addressPostCodeProperty() {
		return this.addressPostCode;
	}

	public final String getAddressPostCode() {
		return this.addressPostCodeProperty().get();
	}

	public void setAddressPostCode(final String addressPostCode) {
		this.addressPostCodeProperty().set(addressPostCode);
	}

	// ---------------------------

	public final SimpleStringProperty addressCityProperty() {
		return this.addressCity;
	}

	public final String getAddressCity() {
		return addressCityProperty().get();
	}

	public void setAddressCity(final String addressCity) {
		this.addressCityProperty().set(addressCity);
	}

	// ---------------------------------

	public final SimpleStringProperty countryProperty() {
		return this.country;
	}

	public final String getCountry() {
		return this.countryProperty().get();
	}

	public final void setCountry(final String country) {
		this.countryProperty().set(country);
	}

	// ---------------------------------

	public final SimpleStringProperty emailProperty() {
		return this.email;
	}

	public final String getEmail() {
		return emailProperty().get();
	}

	public void setEmail(final String email) {
		this.emailProperty().set(email);
	}

	// ------------------------

	public final SimpleStringProperty phoneNumberProperty() {
		return this.phoneNumber;
	}

	public final String getPhoneNumber() {
		return phoneNumberProperty().get();
	}

	public final void setPhoneNumber(final String phoneNumber) {
		this.phoneNumberProperty().set(phoneNumber);
	}

	// ------------------------

	public final SimpleListProperty<Role> rolesProperty() {
		return this.roles;
	}

	public final ObservableList<Role> getRoles() {
		return this.rolesProperty().get();
	}

	public final void setRoles(final ObservableList<Role> roles) {
		this.rolesProperty().set(roles);
	}

	// -------------------------------

	public final SimpleListProperty<Social> socialsProperty() {
		return this.socials;
	}

	public final ObservableList<Social> getSocials() {
		return this.socialsProperty().get();
	}

	public void setSocials(final ObservableList<Social> socials) {
		this.socialsProperty().set(socials);
	}

	// ----------------------------------

	public final SimpleListProperty<Game> gamesProperty() {
		return this.games;
	}

	public final ObservableList<Game> getGames() {
		return this.gamesProperty().get();
	}

	public final void setGames(final ObservableList<Game> games) {
		this.gamesProperty().set(games);
	}

	// ----------------------------------

	public final ObjectProperty<LocalDate> birthdayProperty() {
		return this.birthday;
	}

	public final LocalDate getBirthday() {
		return this.birthdayProperty().get();
	}

	public final void setBirthday(final LocalDate birthday) {
		this.birthdayProperty().set(birthday);
	}

	// -----------------------------------

	public final SimpleListProperty<Team> teamsProperty() {
		return this.teams;
	}

	public final ObservableList<Team> getTeams() {
		return teamsProperty().get();
	}

	public final void setTeams(final ObservableList<Team> teams) {
		this.teamsProperty().set(teams);
	}

	// -------------------------------------

	public final SimpleListProperty<Event> eventsProperty() {
		return this.events;
	}

	public final ObservableList<Event> getEvents() {
		return this.eventsProperty().get();
	}

	public final void setEvents(final ObservableList<Event> events) {
		this.eventsProperty().set(events);
	}

}
