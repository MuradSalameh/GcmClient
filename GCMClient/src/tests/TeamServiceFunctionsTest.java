package tests;

import java.util.ArrayList;
import java.util.List;

import fxClasses.MemberFX;
import fxClasses.TeamFX;
import gcmClasses.Member;
import gcmClasses.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import serviceFunctions.TeamServiceFunctions;




public class TeamServiceFunctionsTest {
	public static  ObservableList<TeamFX> olTeams = FXCollections.observableArrayList();
	public static  ObservableList<MemberFX> olMembers = FXCollections.observableArrayList();


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
		
		
		
		//--------- getTeamsByMemberIdTest() Test -----------//
		
//		getTeamsByMemberIdTest(2);
		
		
		//--------- getTeamsByMemberIdTest() Test -----------//
		
//		getMembersByTeamIdTest(1);


		
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
	
	public static void getTeamsByMemberIdTest(int id) {
		List<Team> xmlTeams = new ArrayList<Team>();
		xmlTeams = (List<Team>) TeamServiceFunctions.getTeamsByMemberId(id);
		
		for(Team einT : xmlTeams) {
			olTeams.add(new TeamFX(einT));
			System.out.println("CLIENT------------" + "\n" + einT);
		}		
	}

	public static void getMembersByTeamIdTest(int id) {
		List<Member> xmlMembers = new ArrayList<Member>();
		xmlMembers = (List<Member>) TeamServiceFunctions.getMembersByTeamId(id);
		
		for(Member einM : xmlMembers) {
			olMembers.add(new MemberFX(einM));
			System.out.println("CLIENT------------" + "\n" + einM);
		}		
	}
	



}

