package tests;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import fxClasses.TournamentFX;
import gcmClasses.Tournament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import serviceFunctions.TournamentServiceFunctions;

public class TournamentServiceFunctionsTest {
	public static ObservableList<TournamentFX> olTournaments = FXCollections.observableArrayList();

	public static void main(String[] args) {

		int id = 2;
		int id2 = 5;
		int id3 = 5;

		// --------- addTournament() Test -----------//

//				addTournament();
//				addTournament();
//				addTournament();
//				addTournament();

		// --------- getTournaments() Test to get a List of all tournaments in
		// database-----------//

//			getTournamentList();

		// --------- deleteTournament() Test -----------//

//		deleteTournament(id);

		// --------- getTournament() Test to get one specific tournament by id
		// -----------//

//				getTournamentTest(id);

		// --------- updateTournament() Test -----------//

//				String s = "CLLIIIEEEENNNT UUPPDDAATTEE";
//				updateTournamentTest(id,s);

	}

	public static void addTournament() {
		Tournament test = new Tournament("TestTournament", // title
				"Testing", // description
				LocalDate.of(1990, 8, 30), // tournament date
				LocalTime.of(22, 58), // start time
				LocalTime.of(2, 30), // end time
				null, // teams list
				null, // games list
				null); // result string

		TournamentServiceFunctions.addTournament(test);
	}

	public static void updateTournamentTest(int id, String s) {
		// get tournament from database
		Tournament m = TournamentServiceFunctions.getTournament(id);

		// set new value for clan name
		m.setTouramentTitle(s);

		// Tournament m in Datenbank updaten
		TournamentServiceFunctions.updateTournament(id, m);

		System.out.println(m);
	}

	public static void deleteTournament(int id) {
		TournamentServiceFunctions.deleteTournament(id);

		Tournament tournament = TournamentServiceFunctions.getTournament(id);
		System.out.println(tournament);

	}

	public static void getTournamentTest(int id) {
		System.out.println("CLIENT------------" + "\n" + TournamentServiceFunctions.getTournament(id));
	}

	public static void getTournamentList() {
		List<Tournament> xmlTournaments = new ArrayList<Tournament>();
		xmlTournaments = (List<Tournament>) TournamentServiceFunctions.getTournaments();

		for (Tournament einM : xmlTournaments) {
			olTournaments.add(new TournamentFX(einM));
			System.out.println("CLIENT------------" + "\n" + einM);
		}
	}

}
