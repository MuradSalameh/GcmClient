package tests;

import java.util.ArrayList;
import java.util.List;



import fxClasses.TeamFX;
import gcmClasses.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import serviceFunctions.TeamServiceFunctions;




public class TeamServiceFunctionsTest {
	public static  ObservableList<TeamFX> olTeams = FXCollections.observableArrayList();

	public static void main(String[] args) {	

		int id = 2;
		int id2 = 5;
		int id3 = 5;

		//--------- addTeam() Test -----------//

//				addTeam();
//				addTeam();
//				addTeam();
//				addTeam();

		

		//--------- getTeams() Test to get a List of all teams in database-----------//

//				getTeamList();


		
		//--------- deleteTeam() Test -----------//

//		deleteTeam(id);



		//--------- getTeam() Test to get one specific team by id -----------//

//				getTeamTest(id);



		//--------- updateTeam() Test -----------//

//				String s = "CLLIIIEEEENNNT UUPPDDAATTEE";
//				updateTeamTest(id,s);


		
	}	
	public static void addTeam() {
		Team test = new Team(
				"test", 					// team name
				"ttttt", 					// desc
				null);						// members

		TeamServiceFunctions.addTeam(test);
	}


	public static void updateTeamTest(int id, String s) {	
		//get team from database
		Team m = TeamServiceFunctions.getTeam(id);

		// set new value for clan name
		m.setTeamName(s);

		//Team m  in Datenbank updaten
		TeamServiceFunctions.updateTeam(id, m);

		System.out.println(m);
	}



	public static void deleteTeam(int id) {
		TeamServiceFunctions.deleteTeam(id);

		Team team = TeamServiceFunctions.getTeam(id);
		System.out.println(team);

	}


	public static void getTeamTest(int id) {
		System.out.println("CLIENT------------" 
				+ "\n" 
				+ TeamServiceFunctions.getTeam(id)); 
	}


	public static void getTeamList() {
		List<Team> xmlTeams = new ArrayList<Team>();
		xmlTeams = (List<Team>) TeamServiceFunctions.getTeams();

		for(Team einM : xmlTeams) {
			olTeams.add(new TeamFX(einM));
			System.out.println("CLIENT------------" + "\n" + einM);
		}		
	}



}

