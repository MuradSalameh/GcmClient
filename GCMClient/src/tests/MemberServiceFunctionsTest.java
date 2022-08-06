package tests;

import java.util.ArrayList;
import java.util.List;



import fxClasses.MemberFX;
import gcmClasses.Member;
import gcmClient.MemberServiceFunctions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;




public class MemberServiceFunctionsTest {
	 public static  ObservableList<MemberFX> olMembers = FXCollections.observableArrayList();

	public static void main(String[] args) {	
		
		int id = 3;
		int id2 = 5;
		int id3 = 5;

		//--------- addMember() Test -----------//

		//addTestMember();
		//addTestMember();
		//addTestMember();
		//addTestMember();
		
		
		//--------- getMembers() Test to get a List of all members in database-----------//

//		getMemberList();


		
		//--------- deleteMember() Test -----------//

		//deleteMemberTest(id);


		
		//--------- getMember() Test to get one specific member by id -----------//

		getMemberTest(id);



		//--------- updateMember() Test -----------//

		//String s = "BOBO";
		//updateMemberTest(id,s);



		
		
		
		

}	/*
	public static void addTestMember() {
		Member test = new Member(
				"test", 					// clan name
				"ttttt", 					// clan id
				"tttttt 1", 				// real name
				"44", 						// address
				"1160", 					// adress post code
				"Vienna", 					// city
				"Austria", 					// country
				"ulli@email.com", 			// mail
				"+43 677 678 643 44", 		// phone number
				null, 						// role
				null, 						// socials
				null, 						// games
				null,						// events
				LocalDate.of(1981, 4, 11), 	// birthday
				null);						// teams

		MemberServiceFunctions.		
	}
*/
/*
	public static void updateMemberTest(int id, String s) {
		Session session = SessionUtil.getSession();

		//Vorhandenen Member anhand id aus DB holen
		Member m = session.get(Member.class, id);

		// Member m ClanName wert neu setzen
		m.setClanName(s);

		//Member m  in Datenbank updaten
		MemberDAO.updateMember(id, m);

		System.out.println(m);
	}
*/
	/*
	public static void deleteMemberTest(int id) {
		MemberDAO.deleteMember(id);

		Member member = MemberDAO.getMember(id);
		System.out.println(member);

	}
*/
	
	public static void getMemberTest(int id) {
		System.out.println("CLIENT------------" + "\n" +MemberServiceFunctions.getMember(id)); 
	}

	
	public static void getMemberList() {
		 List<Member> xmlMembers = new ArrayList<Member>();
			xmlMembers = MemberServiceFunctions.getMembers();
			
			
			for(Member einM : xmlMembers) {
				olMembers.add(new MemberFX(einM));
				System.out.println("CLIENT------------" + "\n" + einM);
			}
		
	}
	
	
}

