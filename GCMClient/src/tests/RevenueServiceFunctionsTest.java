package tests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



import fxClasses.RevenueFX;
import gcmClasses.Revenue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import serviceFunctions.RevenueServiceFunctions;




public class RevenueServiceFunctionsTest {
	public static  ObservableList<RevenueFX> olRevenues = FXCollections.observableArrayList();

	public static void main(String[] args) {	

		int id = 2;
		int id2 = 5;
		int id3 = 5;

		//--------- addRevenue() Test -----------//

//				addRevenue();
//				addRevenue();
//				addRevenue();
//				addRevenue();

		

		//--------- getRevenues() Test to get a List of all revenues in database-----------//

//				getRevenueList();


		
		//--------- deleteRevenue() Test -----------//

//		deleteRevenue(id);



		//--------- getRevenue() Test to get one specific revenue by id -----------//

//				getRevenueTest(id);



		//--------- updateRevenue() Test -----------//

//				String s = "CLLIIIEEEENNNT UUPPDDAATTEE";
//				updateRevenueTest(id,s);


		
	}	
	public static void addRevenue() {
		Revenue test = new Revenue(
				"Revenue", 					// title
				"earnings", 					// desc
				399999.99, 					// amount
				LocalDate.of(1981, 4, 11));	// date

		RevenueServiceFunctions.addRevenue(test);
	}


	public static void updateRevenueTest(int id, String s) {	
		//get revenue from database
		Revenue m = RevenueServiceFunctions.getRevenue(id);

		// set new value for clan name
		m.setRevenueTitle(s);

		//Revenue m  in Datenbank updaten
		RevenueServiceFunctions.updateRevenue(id, m);

		System.out.println(m);
	}



	public static void deleteRevenue(int id) {
		RevenueServiceFunctions.deleteRevenue(id);

		Revenue revenue = RevenueServiceFunctions.getRevenue(id);
		System.out.println(revenue);

	}


	public static void getRevenueTest(int id) {
		System.out.println("CLIENT------------" 
				+ "\n" 
				+ RevenueServiceFunctions.getRevenue(id)); 
	}


	public static void getRevenueList() {
		List<Revenue> xmlRevenues = new ArrayList<Revenue>();
		xmlRevenues = (List<Revenue>) RevenueServiceFunctions.getRevenues();

		for(Revenue einM : xmlRevenues) {
			olRevenues.add(new RevenueFX(einM));
			System.out.println("CLIENT------------" + "\n" + einM);
		}		
	}



}

