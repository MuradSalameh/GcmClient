package tests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



import fxClasses.RevenueTypeFX;
import gcmClasses.RevenueType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import serviceFunctions.RevenueTypeServiceFunctions;




public class RevenueTypeServiceFunctionsTest {
	public static  ObservableList<RevenueTypeFX> olRevenueTypes = FXCollections.observableArrayList();

	public static void main(String[] args) {	

		int id = 1;
		int id2 = 5;
		int id3 = 5;

		//--------- addRevenueType() Test -----------//

//				addRevenueType();
//				addRevenueType();
//				addRevenueType();
//				addRevenueType();

		

		//--------- getRevenueTypes() Test to get a List of all revenueTypes in database-----------//

//				getRevenueTypeList();


		
		//--------- deleteRevenueType() Test -----------//

//		deleteRevenueType(id);



		//--------- getRevenueType() Test to get one specific revenueType by id -----------//

//				getRevenueTypeTest(id);



		//--------- updateRevenueType() Test -----------//

//				String s = "CLLIIIEEEENNNT UUPPDDAATTEE";
//				updateRevenueTypeTest(id,s);


		
	}	
	public static void addRevenueType() {
		RevenueType test = new RevenueType(
				"test", 					// title
				"ttttt", 					// desc
				null);						// revenues

		RevenueTypeServiceFunctions.addRevenueType(test);
	}


	public static void updateRevenueTypeTest(int id, String s) {	
		//get revenueType from database
		RevenueType m = RevenueTypeServiceFunctions.getRevenueType(id);

		// set new value for clan name
		m.setRevenueTypeTitle(s);

		//RevenueType m  in Datenbank updaten
		RevenueTypeServiceFunctions.updateRevenueType(id, m);

		System.out.println(m);
	}



	public static void deleteRevenueType(int id) {
		RevenueTypeServiceFunctions.deleteRevenueType(id);

		RevenueType revenueType = RevenueTypeServiceFunctions.getRevenueType(id);
		System.out.println(revenueType);

	}


	public static void getRevenueTypeTest(int id) {
		System.out.println("CLIENT------------" 
				+ "\n" 
				+ RevenueTypeServiceFunctions.getRevenueType(id)); 
	}


	public static void getRevenueTypeList() {
		List<RevenueType> xmlRevenueTypes = new ArrayList<RevenueType>();
		xmlRevenueTypes = (List<RevenueType>) RevenueTypeServiceFunctions.getRevenueTypes();

		for(RevenueType einM : xmlRevenueTypes) {
			olRevenueTypes.add(new RevenueTypeFX(einM));
			System.out.println("CLIENT------------" + "\n" + einM);
		}		
	}



}

