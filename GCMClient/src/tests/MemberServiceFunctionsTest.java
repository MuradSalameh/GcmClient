package tests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



import fxClasses.MemberFX;
import gcmClasses.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import serviceFunctions.MemberServiceFunctions;




public class MemberServiceFunctionsTest {
	public static  ObservableList<MemberFX> olMembers = FXCollections.observableArrayList();

	public static void main(String[] args) {	

		int id = 5;
		int id2 = 5;
		int id3 = 5;

		//--------- addMember() Test -----------//

				addMember();
				addMember();
				addMember();
				addMember();

		

		//--------- getMembers() Test to get a List of all members in database-----------//

			//	getMemberList();


		
		//--------- deleteMember() Test -----------//

//		deleteMember(id);



		//--------- getMember() Test to get one specific member by id -----------//

//				getMemberTest(id);



		//--------- updateMember() Test -----------//

//				String s = "CLLIIIEEEENNNT UUPPDDAATTEE";
//				updateMemberTest(id,s);


		
	}	
	public static void addMember() {
		Member test = new Member(
				"OOOOO", 					// clan name
				"oooooo", 					// clan id
				"ooooooOoO 1", 				// real name
				"11", 						// address
				"44444", 					// adress post code
				"OOOOOO", 					// city
				"OOOOOO", 					// country
				"OOOOO@email.com", 			// mail
				"+431111111111", 		// phone number
				null, 						// role
				null, 						// socials
				null, 						// games
				null,						// events
				LocalDate.of(1981, 4, 11), 	// birthday
				null);						// teams

		MemberServiceFunctions.addMember(test);
	}


	public static void updateMemberTest(int id, String s) {	
		//get member from database
		Member m = MemberServiceFunctions.getMember(id);

		// set new value for clan name
		m.setClanName(s);

		//Member m  in Datenbank updaten
		MemberServiceFunctions.updateMember(id, m);

		System.out.println(m);
	}



	public static void deleteMember(int id) {
		MemberServiceFunctions.deleteMember(id);

		Member member = MemberServiceFunctions.getMember(id);
		System.out.println(member);

	}


	public static void getMemberTest(int id) {
		System.out.println("CLIENT------------" 
				+ "\n" 
				+ MemberServiceFunctions.getMember(id)); 
	}


	public static void getMemberList() {
		List<Member> xmlMembers = new ArrayList<Member>();
		xmlMembers = (List<Member>) MemberServiceFunctions.getMembers();

		for(Member einM : xmlMembers) {
			olMembers.add(new MemberFX(einM));
			System.out.println("CLIENT------------" + "\n" + einM);
		}		
	}



}

