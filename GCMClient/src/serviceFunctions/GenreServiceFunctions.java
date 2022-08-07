package serviceFunctions;

import java.util.List;

import gcmClasses.Genre;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


public class GenreServiceFunctions {


	private static final String serverURI = "http://localhost:4712/genre";


	//GET - get genre list
	public static List<Genre> getGenres() {

		List<Genre> genres = ClientBuilder.newClient()
				.target(serverURI)
				.path("/genrelist")
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<List<Genre>>(){});

		return genres;
	}


	//GET - get one genre
	public static Genre getGenre(int id) {

		Genre genre = ClientBuilder.newClient()
				.target(serverURI)
				.path("/genre/" + id)
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<Genre>(){});

		return genre;
	}


	//Post - add new genre 
	public static Response addGenre(Genre m) {

		Client client = ClientBuilder.newClient();
		return client
				.target(serverURI)
				.path("/addGenre")
				.request(MediaType.APPLICATION_XML)
				.post(Entity.entity(m, MediaType.APPLICATION_XML));
	}


	//PUT - update genre 
	public static Response updateGenre(int id, Genre m) {

		Client client = ClientBuilder.newClient();
		return client
				.target(serverURI)
				.path("/updateGenre/" + id)
				.request(MediaType.APPLICATION_XML)
				.put(Entity.entity(m, MediaType.APPLICATION_XML));	

	}


	//Delete - delete genre 
	public static Response deleteGenre(int id) {

		Client client = ClientBuilder.newClient();
		return client
				.target(serverURI)
				.path("/deleteGenre/" + id)
				.request(MediaType.APPLICATION_XML)
				.delete();	

	}

}
