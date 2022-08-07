package tests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fxClasses.GameFX;
import gcmClasses.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import serviceFunctions.GameServiceFunctions;




public class GameServiceFunctionsTest {
	public static  ObservableList<GameFX> olGames = FXCollections.observableArrayList();

	public static void main(String[] args) {	

		int id = 1;
		int id2 = 5;
		int id3 = 5;

		//--------- addGame() Test -----------//

//				addGame();
//				addGame();
//				addGame();
//				addGame();

		

		//--------- getGames() Test to get a List of all games in database-----------//

//		getGameList();


		
		//--------- deleteGame() Test -----------//

//		deleteGame(id);



		//--------- getGame() Test to get one specific game by id -----------//

			getGame(id);



		//--------- updateGame() Test -----------//

//				String s = "CLLIIIEEEENNNT UUPPDDAATTEE";
//				updateGameTest(id,s);


		
	}	
	public static void addGame() {
		Game test = new Game(
				"test", 					// title
				LocalDate.of(1981, 4, 11), 	// release date
				null, 						// genres
				null, 						// games
				null, 						// tournaments
				"lorem ipsum");				// notes

		GameServiceFunctions.addGame(test);
	}


	public static void updateGameTest(int id, String s) {	
		//get game from database
		Game m = GameServiceFunctions.getGame(id);

		// set new value for clan name
		m.setGameTitle(s);

		//Game m  in Datenbank updaten
		GameServiceFunctions.updateGame(id, m);

		System.out.println(m);
	}



	public static void deleteGame(int id) {
		GameServiceFunctions.deleteGame(id);

		Game game = GameServiceFunctions.getGame(id);
		System.out.println(game);

	}


	public static void getGame(int id) {
		System.out.println("CLIENT------------" 
				+ "\n" 
				+ GameServiceFunctions.getGame(id)); 
	}


	public static void getGameList() {
		List<Game> xmlGames = new ArrayList<Game>();
		xmlGames = (List<Game>) GameServiceFunctions.getGames();

		for(Game einM : xmlGames) {
			olGames.add(new GameFX(einM));
			System.out.println("CLIENT------------" + "\n" + einM);
		}		
	}



}

