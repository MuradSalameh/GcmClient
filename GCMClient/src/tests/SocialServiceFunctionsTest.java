package tests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



import fxClasses.SocialFX;
import gcmClasses.Social;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import serviceFunctions.SocialServiceFunctions;




public class SocialServiceFunctionsTest {
	public static  ObservableList<SocialFX> olSocials = FXCollections.observableArrayList();

	public static void main(String[] args) {	

		int id = 3;
		int id2 = 5;
		int id3 = 5;

		//--------- addSocial() Test -----------//

//				addSocial();
//				addSocial();
//				addSocial();
//				addSocial();

		

		//--------- getSocials() Test to get a List of all socials in database-----------//

//				getSocialList();


		
		//--------- deleteSocial() Test -----------//

//		deleteSocial(id);



		//--------- getSocial() Test to get one specific social by id -----------//

//				getSocialTest(id);



		//--------- updateSocial() Test -----------//

//				String s = "CLLIIIEEEENNNT UUPPDDAATTEE";
//				updateSocialTest(id,s);


		
	}	
	public static void addSocial() {
		Social test = new Social(
				"test", 					// platform
				"ttttt", 					// username
				"tttttt 1", 				// link
				"44");						// notes

		SocialServiceFunctions.addSocial(test);
	}


	public static void updateSocialTest(int id, String s) {	
		//get social from database
		Social m = SocialServiceFunctions.getSocial(id);

		// set new value for clan name
		m.setSocialPlatform(s);

		//Social m  in Datenbank updaten
		SocialServiceFunctions.updateSocial(id, m);

		System.out.println(m);
	}



	public static void deleteSocial(int id) {
		SocialServiceFunctions.deleteSocial(id);

		Social social = SocialServiceFunctions.getSocial(id);
		System.out.println(social);

	}


	public static void getSocialTest(int id) {
		System.out.println("CLIENT------------" 
				+ "\n" 
				+ SocialServiceFunctions.getSocial(id)); 
	}


	public static void getSocialList() {
		List<Social> xmlSocials = new ArrayList<Social>();
		xmlSocials = (List<Social>) SocialServiceFunctions.getSocials();

		for(Social einM : xmlSocials) {
			olSocials.add(new SocialFX(einM));
			System.out.println("CLIENT------------" + "\n" + einM);
		}		
	}



}

