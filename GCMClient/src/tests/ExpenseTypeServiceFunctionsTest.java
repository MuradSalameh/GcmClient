package tests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



import fxClasses.ExpenseTypeFX;
import gcmClasses.ExpenseType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import serviceFunctions.ExpenseTypeServiceFunctions;




public class ExpenseTypeServiceFunctionsTest {
	public static  ObservableList<ExpenseTypeFX> olExpenseTypes = FXCollections.observableArrayList();

	public static void main(String[] args) {	

		int id = 5;
		int id2 = 5;
		int id3 = 5;

		//--------- addExpenseType() Test -----------//

//				addExpenseType();
//				addExpenseType();
//				addExpenseType();
//				addExpenseType();

		

		//--------- getExpenseTypes() Test to get a List of all expenseTypes in database-----------//

				getExpenseTypeList();


		
		//--------- deleteExpenseType() Test -----------//

//		deleteExpenseType(id);



		//--------- getExpenseType() Test to get one specific expenseType by id -----------//

//				getExpenseTypeTest(id);



		//--------- updateExpenseType() Test -----------//

//				String s = "CLLIIIEEEENNNT UUPPDDAATTEE";
//				updateExpenseTypeTest(id,s);


		
	}	
	public static void addExpenseType() {
		ExpenseType test = new ExpenseType(
				"test", 					// title
				"ttttt");					// desc

		ExpenseTypeServiceFunctions.addExpenseType(test);
	}


	public static void updateExpenseTypeTest(int id, String s) {	
		//get expenseType from database
		ExpenseType m = ExpenseTypeServiceFunctions.getExpenseType(id);

		// set new value for clan name
		m.setExpenseTitle(s);

		//ExpenseType m  in Datenbank updaten
		ExpenseTypeServiceFunctions.updateExpenseType(id, m);

		System.out.println(m);
	}



	public static void deleteExpenseType(int id) {
		ExpenseTypeServiceFunctions.deleteExpenseType(id);

		ExpenseType expenseType = ExpenseTypeServiceFunctions.getExpenseType(id);
		System.out.println(expenseType);

	}


	public static void getExpenseTypeTest(int id) {
		System.out.println("CLIENT------------" 
				+ "\n" 
				+ ExpenseTypeServiceFunctions.getExpenseType(id)); 
	}


	public static void getExpenseTypeList() {
		List<ExpenseType> xmlExpenseTypes = new ArrayList<ExpenseType>();
		xmlExpenseTypes = (List<ExpenseType>) ExpenseTypeServiceFunctions.getExpenseTypes();

		for(ExpenseType einM : xmlExpenseTypes) {
			olExpenseTypes.add(new ExpenseTypeFX(einM));
			System.out.println("CLIENT------------" + "\n" + einM);
		}		
	}



}

