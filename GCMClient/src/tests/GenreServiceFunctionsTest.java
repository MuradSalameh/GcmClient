package tests;


import java.util.ArrayList;
import java.util.List;



import fxClasses.GenreFX;
import gcmClasses.Genre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import serviceFunctions.GenreServiceFunctions;




public class GenreServiceFunctionsTest {
	public static  ObservableList<GenreFX> olGenres = FXCollections.observableArrayList();

	public static void main(String[] args) {	

		int id = 1;
		int id2 = 5;
		int id3 = 5;

		//--------- addGenre() Test -----------//

//				addGenre();
//				addGenre();
//				addGenre();
//				addGenre();

		

		//--------- getGenres() Test to get a List of all genres in database-----------//

//				getGenreList();


		
		//--------- deleteGenre() Test -----------//

//		deleteGenre(id);



		//--------- getGenre() Test to get one specific genre by id -----------//

				getGenreTest(id);



		//--------- updateGenre() Test -----------//

//				String s = "CLLIIIEEEENNNT UUPPDDAATTEE";
//				updateGenreTest(id,s);


		
	}	
	public static void addGenre() {
		Genre test = new Genre(
				"test", 					// title
				null);						// games
		GenreServiceFunctions.addGenre(test);
	}


	public static void updateGenreTest(int id, String s) {	
		//get genre from database
		Genre m = GenreServiceFunctions.getGenre(id);

		// set new value for clan name
		m.setGenreTitle(s);

		//Genre m  in Datenbank updaten
		GenreServiceFunctions.updateGenre(id, m);

		System.out.println(m);
	}



	public static void deleteGenre(int id) {
		GenreServiceFunctions.deleteGenre(id);

		Genre genre = GenreServiceFunctions.getGenre(id);
		System.out.println(genre);

	}


	public static void getGenreTest(int id) {
		System.out.println("CLIENT------------" 
				+ "\n" 
				+ GenreServiceFunctions.getGenre(id)); 
	}


	public static void getGenreList() {
		List<Genre> xmlGenres = new ArrayList<Genre>();
		xmlGenres = (List<Genre>) GenreServiceFunctions.getGenres();

		for(Genre einM : xmlGenres) {
			olGenres.add(new GenreFX(einM));
			System.out.println("CLIENT------------" + "\n" + einM);
		}		
	}



}

