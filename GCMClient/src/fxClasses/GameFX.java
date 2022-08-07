package fxClasses;

import java.time.LocalDate;
import gcmClasses.Game;
import gcmClasses.Genre;
import gcmClasses.Member;
import gcmClasses.Tournament;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GameFX {
	private Game serverGame;
	

	private SimpleIntegerProperty id;
	private SimpleStringProperty gameTitle;
	private ObjectProperty<LocalDate> releaseDate;
	private SimpleListProperty<Genre> genres;
	private SimpleListProperty<Member> members;
	private SimpleListProperty<Tournament> tournaments;
	private SimpleStringProperty gameAdditionalNotes;
	
	
	public GameFX() {
		super();
	}


	public GameFX(Game serverGame) {
		super();
		this.serverGame = serverGame;
		id = new SimpleIntegerProperty(serverGame.getId());
		gameTitle = new SimpleStringProperty(serverGame.getGameTitle());
		releaseDate = new SimpleObjectProperty<LocalDate>(serverGame.getReleaseDate());
		
		ObservableList<Genre> genresOl = FXCollections.observableArrayList(serverGame.getGenres());
		this.genres = new SimpleListProperty<Genre>(genresOl);		

		ObservableList<Member> membersOl = FXCollections.observableArrayList(serverGame.getMembers());
		this.members = new SimpleListProperty<Member>(membersOl);		
	
		ObservableList<Tournament> tournamentsOl = FXCollections.observableArrayList(serverGame.getTournaments());
		this.tournaments = new SimpleListProperty<Tournament>(tournamentsOl);		
		
		gameAdditionalNotes = new SimpleStringProperty(serverGame.getGameAdditionalNotes());
	}


	public Game getServerGame() {
		return serverGame;
	}
	
	//--------------------------------------

	public final SimpleIntegerProperty idProperty() {
		return this.id;
	}

	public final int getId() {
		return this.idProperty().get();
	}

	
	public final void setId(final int id) {
		this.idProperty().set(id);
	}
	
	//--------------------------------------

	public final SimpleStringProperty gameTitleProperty() {
		return this.gameTitle;
	}


	public final String getGameTitle() {
		return gameTitleProperty().get();
	}


	public final void setGameTitle(final String gameTitle) {
		this.gameTitleProperty().set(gameTitle);
	}
	
	//--------------------------------------
	public final ObjectProperty<LocalDate> releaseDateProperty() {
		return this.releaseDate;
	}


	public final LocalDate getReleaseDate() {
		return releaseDateProperty().get();
	}


	public final void setReleaseDate(final LocalDate releaseDate) {
		this.releaseDateProperty().set(releaseDate);
	}
	
	//--------------------------------------

	public final SimpleListProperty<Genre> genresProperty() {
		return this.genres;
	}

	public final ObservableList<Genre> getGenres() {
		return genresProperty().get();
	}


	public final void setGenres(final ObservableList<Genre> genres) {
		this.genresProperty().set(genres);
	}
	
	//--------------------------------------

	public final SimpleListProperty<Member> membersProperty() {
		return this.members;
	}


	public final ObservableList<Member> getMembers() {
		return membersProperty().get();
	}


	public final void setMembers(final ObservableList<Member> members) {
		this.membersProperty().set(members);
	}

	//--------------------------------------

	public final SimpleListProperty<Tournament> tournamentsProperty() {
		return this.tournaments;
	}

	public final ObservableList<Tournament> getTournaments() {
		return tournamentsProperty().get();
	}


	public final void setTournaments(final ObservableList<Tournament> tournaments) {
		this.tournamentsProperty().set(tournaments);
	}
	
	//--------------------------------------

	public final SimpleStringProperty gameAdditionalNotesProperty() {
		return this.gameAdditionalNotes;
	}


	public final String getGameAdditionalNotes() {
		return gameAdditionalNotesProperty().get(); //
	}


	public final void setGameAdditionalNotes(final String gameAdditionalNotes) {
		this.gameAdditionalNotesProperty().set(gameAdditionalNotes);
	}
	
	//--------------------------------------

}
