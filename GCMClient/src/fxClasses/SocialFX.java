package fxClasses;

import gcmClasses.Social;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SocialFX {
	private Social serverSocial;
	private SimpleIntegerProperty id;
	private SimpleStringProperty socialPlatform;
	private SimpleStringProperty socialUsername;
	private SimpleStringProperty socialLink;
	private SimpleStringProperty socialNotes;

	public SocialFX() {
		super();
	}

	public SocialFX(Social serverSocial) {
		super();
		this.serverSocial = serverSocial;
		id = new SimpleIntegerProperty(serverSocial.getId());
		socialPlatform = new SimpleStringProperty(serverSocial.getSocialPlatform());
		socialUsername = new SimpleStringProperty(serverSocial.getSocialUsername());
		socialLink = new SimpleStringProperty(serverSocial.getSocialLink());
		socialNotes = new SimpleStringProperty(serverSocial.getSocialNotes());
	}

	public Social getServerSocial() {
		return serverSocial;
	}

	// -----------------------

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

	public final SimpleStringProperty socialPlatformProperty() {
		return this.socialPlatform;
	}

	public final String getSocialPlatform() {
		return socialPlatformProperty().get();
	}

	public final void setSocialPlatform(final String socialPlatform) {
		this.socialPlatformProperty().set(socialPlatform);
	}

	// -----------------------

	public final SimpleStringProperty socialUsernameProperty() {
		return this.socialUsername;
	}

	public final String getSocialUsername() {
		return socialUsernameProperty().get();
	}

	public final void setSocialUsername(final String socialUsername) {
		this.socialUsernameProperty().set(socialUsername);
	}

	// -----------------------
	public final SimpleStringProperty socialLinkProperty() {
		return this.socialLink;
	}

	public final String getSocialLink() {
		return socialLinkProperty().get();
	}

	public final void setSocialLink(final String socialLink) {
		this.socialLinkProperty().set(socialLink);
	}

	// -----------------------
	public final SimpleStringProperty socialNotesProperty() {
		return this.socialNotes;
	}

	public final String getSocialNotes() {
		return socialNotesProperty().get();
	}

	public final void setSocialNotes(final String socialNotes) {
		this.socialNotesProperty().set(socialNotes);
	}

}
