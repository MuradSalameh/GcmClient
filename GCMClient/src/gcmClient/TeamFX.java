package gcmClient;

import gcmClasses.Member;
import gcmClasses.Team;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TeamFX {
	
	private Team serverTeam;
	private SimpleIntegerProperty id;
	private SimpleStringProperty teamName;
	private SimpleStringProperty teamDescription;	
	private SimpleListProperty<Member> members;
	
	
	public TeamFX() {
		super();
	}

	public TeamFX(Team serverTeam, SimpleIntegerProperty id, SimpleStringProperty teamName,
			SimpleStringProperty teamDescription, SimpleListProperty<Member> members) {
		super();
		this.serverTeam = serverTeam;
		id = new SimpleIntegerProperty(serverTeam.getId());
		teamName = new SimpleStringProperty(serverTeam.getTeamName());
		teamDescription = new SimpleStringProperty(serverTeam.getTeamDescription());
		
		ObservableList<Member> membersOl = FXCollections.observableArrayList(serverTeam.getMembers());
		this.members = new SimpleListProperty<Member>(membersOl);
		
	}

	public Team getServerTeam() {
		return serverTeam;
	}

	//-----------------------

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

	//----------------------

	public final SimpleStringProperty teamNameProperty() {
		return this.teamName;
	}

	public final String getTeamName() {
		return teamNameProperty().get();
	}

	public final void setTeamName(final String teamName) {
		this.teamNameProperty().set(teamName);
	}

	//-----------------------
	public final SimpleStringProperty teamDescriptionProperty() {
		return this.teamDescription;
	}

	public final String getTeamDescription() {
		return teamDescriptionProperty().get();
	}

	public final void setTeamDescription(final String teamDescription) {
		this.teamDescriptionProperty().set(teamDescription);
	}

	//-----------------------
	public final SimpleListProperty<Member> membersProperty() {
		return this.members;
	}

	public final ObservableList<Member> getMembers() {
		return membersProperty().get();
	}

	public final void setMembers(final ObservableList<Member> members) {
		this.membersProperty().set(members);
	}
	
	
	
	
	
	
	
	
	
	
	

}
