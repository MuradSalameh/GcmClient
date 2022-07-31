package fxClasses;

import gcmClasses.Game;
import gcmClasses.Genre;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GenreFX {

	private Genre serverGenre;
	private SimpleIntegerProperty id;	
	private SimpleStringProperty genreTitle;	
    private SimpleListProperty<Game> games;
    
    
	public GenreFX() {
		super();
	}


	public GenreFX(Genre serverGenre, SimpleIntegerProperty id, SimpleStringProperty genreTitle,
			SimpleListProperty<Game> games) {
		super();
		this.serverGenre = serverGenre;
		id = new SimpleIntegerProperty(serverGenre.getId());
		genreTitle = new SimpleStringProperty(serverGenre.getGenreTitle());
		
		ObservableList<Game> gamesOl = FXCollections.observableArrayList(serverGenre.getGames());
		this.games = new SimpleListProperty<Game>(gamesOl);
		
	
	}


	public Genre getServerGenre() {
		return serverGenre;
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
	
	//-----------------------

	public final SimpleStringProperty genreTitleProperty() {
		return this.genreTitle;
	}

	public final String getGenreTitle() {
		return genreTitleProperty().get();
	}


	public final void setGenreTitle(final String genreTitle) {
		this.genreTitleProperty().set(genreTitle);
	}

	//-----------------------
	
	public final SimpleListProperty<Game> gamesProperty() {
		return this.games;
	}

	public final ObservableList<Game> getGames() {
		return gamesProperty().get();
	}


	public final void setGames(final ObservableList<Game> games) {
		this.gamesProperty().set(games);
	}
	
}
