package fxClasses;

import java.time.LocalDate;
import java.time.LocalTime;
import gcmClasses.Game;
import gcmClasses.Team;
import gcmClasses.Tournament;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TournamentFX {
	private Tournament serverTournament;
	private SimpleIntegerProperty id;
	private SimpleStringProperty tournamentTitle;
	private SimpleStringProperty tournamentDescription;
	private ObjectProperty<LocalDate> tournamentDate;
	private ObjectProperty<LocalTime> tournamentTimeBeginn;
	private ObjectProperty<LocalTime> tournamentTimeEnd;
	private SimpleListProperty<Team> teams;
    private  ObjectProperty<Game> game;
	private SimpleStringProperty tournamentResult;
	
	
	public TournamentFX() {
		super();
	}


	public TournamentFX(Tournament serverTournament) {
		super();
		this.serverTournament = serverTournament;
		id = new SimpleIntegerProperty(serverTournament.getId());
		tournamentTitle = new SimpleStringProperty(serverTournament.getTouramentTitle());
		tournamentDescription = new SimpleStringProperty(serverTournament.getTournamentDescription());
		tournamentDate = new SimpleObjectProperty<LocalDate>(serverTournament.getTournamentDate());
		tournamentTimeBeginn = new SimpleObjectProperty<LocalTime>(serverTournament.getTournamentTimeBeginn());
		tournamentTimeEnd = new SimpleObjectProperty<LocalTime>(serverTournament.getTournamentTimeEnd());
		
		ObservableList<Team> teamsOl = FXCollections.observableArrayList(serverTournament.getTeams());
		this.teams = new SimpleListProperty<Team>(teamsOl);
		
		game = new SimpleObjectProperty<Game>(serverTournament.getGame());
		
		tournamentResult = new SimpleStringProperty(serverTournament.getTournamentResult());
		
	}


	public Tournament getServerTournament() {
		return serverTournament;
	}


	public void setServerTournament(Tournament serverTournament) {
		this.serverTournament = serverTournament;
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
	public final SimpleStringProperty tournamentTitleProperty() {
		return this.tournamentTitle;
	}

	
	public final String getTournamentTitle() {
		return tournamentTitleProperty().get();
	}


	public final void setTournamentTitle(final String tournamentTitle) {
		this.tournamentTitleProperty().set(tournamentTitle);
	}

	//-----------------------

	public final SimpleStringProperty tournamentDescriptionProperty() {
		return this.tournamentDescription;
	}
	

	public final String getTournamentDescription() {
		return tournamentDescriptionProperty().get();
	}


	public final void setTournamentDescription(final String tournamentDescription) {
		this.tournamentDescriptionProperty().set(tournamentDescription);
	}

	//-----------------------
	public final ObjectProperty<LocalDate> tournamentDateProperty() {
		return this.tournamentDate;
	}

	

	public final LocalDate getTournamentDate() {
		return tournamentDateProperty().get();
	}


	public final void setTournamentDate(final LocalDate tournamentDate) {
		this.tournamentDateProperty().set(tournamentDate);
	}


	//-----------------------
	public final ObjectProperty<LocalTime> tournamentTimeBeginnProperty() {
		return this.tournamentTimeBeginn;
	}

	
	public final LocalTime getTournamentTimeBeginn() {
		return tournamentTimeBeginnProperty().get();
	}


	public final void setTournamentTimeBeginn(final LocalTime tournamentTimeBeginn) {
		this.tournamentTimeBeginnProperty().set(tournamentTimeBeginn);
	}


	//-----------------------
	public final ObjectProperty<LocalTime> tournamentTimeEndProperty() {
		return this.tournamentTimeEnd;
	}

	
	public final LocalTime getTournamentTimeEnd() {
		return tournamentTimeEndProperty().get();
	}

	
	public final void setTournamentTimeEnd(final LocalTime tournamentTimeEnd) {
		this.tournamentTimeEndProperty().set(tournamentTimeEnd);
	}

	//-----------------------

	public final SimpleListProperty<Team> teamsProperty() {
		return this.teams;
	}
	

	public final ObservableList<Team> getTeams() {
		return teamsProperty().get();
	}

	public final void setTeams(final ObservableList<Team> teams) {
		this.teamsProperty().set(teams);
	}


	//-----------------------
	public final ObjectProperty<Game> gameProperty() {
		return this.game;
	}
	
	
	public final Game getGame() {
		return gameProperty().get();
	}


	public final void setGame(Game game) {
		this.gameProperty().set(game);
	}

	//-----------------------
	public final SimpleStringProperty tournamentResultProperty() {
		return this.tournamentResult;
	}


	public final String getTournamentResult() {
		return tournamentResultProperty().get();
	}


	public final void setTournamentResult(final String tournamentResult) {
		this.tournamentResultProperty().set(tournamentResult);
	}
	
	
	
	
	
	
	
	
}
