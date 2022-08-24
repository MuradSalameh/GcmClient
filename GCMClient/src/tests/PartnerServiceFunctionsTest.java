package tests;

import java.util.ArrayList;
import java.util.List;



import fxClasses.PartnerFX;
import gcmClasses.Partner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import serviceFunctions.PartnerServiceFunctions;




public class PartnerServiceFunctionsTest {
	public static  ObservableList<PartnerFX> olPartners = FXCollections.observableArrayList();

	public static void main(String[] args) {	

		int id = 2;
		int id2 = 5;
		int id3 = 5;

		//--------- addPartner() Test -----------//

//				addPartner();
//				addPartner();
//				addPartner();
//				addPartner();

		

		//--------- getPartners() Test to get a List of all partners in database-----------//

//				getPartnerList();


		
		//--------- deletePartner() Test -----------//

//		deletePartner(id);



		//--------- getPartner() Test to get one specific partner by id -----------//

//				getPartnerTest(id);



		//--------- updatePartner() Test -----------//

//				String s = "CLLIIIEEEENNNT UUPPDDAATTEE";
//				updatePartnerTest(id,s);


		
	}	
	public static void addPartner() {
		Partner test = new Partner(
				"COMPANY", 					// company name
				"ttttt", 					// contact person name
				"tttttt 1", 				// contact person phone
				"contact@mail.com", 			// contact person mail
				"dfvdfv", 					// first name
				"rororor", 					// last name
				"StreetCompany", 			// adress street 
				"99", 						// addess number 
				"2345", 					// post code
				"Dublin", 					// city
				"Ireland", 					// country
				"company@company.com", 		// email
				"+43 677 678 643 44");		// phone

		PartnerServiceFunctions.addPartner(test);
	}


	public static void updatePartnerTest(int id, String s) {	
		//get partner from database
		Partner m = PartnerServiceFunctions.getPartner(id);

		// set new value for clan name
		m.setCompanyName(s);

		//Partner m  in Datenbank updaten
		PartnerServiceFunctions.updatePartner(id, m);

		System.out.println(m);
	}



	public static void deletePartner(int id) {
		PartnerServiceFunctions.deletePartner(id);

		Partner partner = PartnerServiceFunctions.getPartner(id);
		System.out.println(partner);

	}


	public static void getPartnerTest(int id) {
		System.out.println("CLIENT------------" 
				+ "\n" 
				+ PartnerServiceFunctions.getPartner(id)); 
	}


	public static void getPartnerList() {
		List<Partner> xmlPartners = new ArrayList<Partner>();
		xmlPartners = (List<Partner>) PartnerServiceFunctions.getPartners();

		for(Partner einM : xmlPartners) {
			olPartners.add(new PartnerFX(einM));
			System.out.println("CLIENT------------" + "\n" + einM);
		}		
	}



}

