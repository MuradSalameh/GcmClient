package tests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



import fxClasses.ExpenseFX;
import gcmClasses.Expense;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import serviceFunctions.ExpenseServiceFunctions;




public class ExpenseServiceFunctionsTest {
	public static  ObservableList<ExpenseFX> olExpenses = FXCollections.observableArrayList();

	public static void main(String[] args) {	

		int id1 = 5;
		int id2 = 2;
		int id3 = 3;
		int id4 = 4;

		//--------- addExpense() Test -----------//

				addExpense();
				addExpense();
				addExpense();
				addExpense();

		

		//--------- getExpenses() Test to get a List of all expenses in database-----------//

//				getExpenseList();


		
		//--------- deleteExpense() Test -----------//

//	deleteExpense(id1);
//	deleteExpense(id2);
//	deleteExpense(id3);
//	deleteExpense(id4);



		//--------- getExpense() Test to get one specific expense by id -----------//

//				getExpenseTest(id4);



		//--------- updateExpense() Test -----------//

//				String s = "CLLIIIEEEENNNT UUPPDDAATTEE";
//				updateExpenseTest(id1,s);


		
	}	
	public static void addExpense() {
		Expense test = new Expense(
				"test", 					// title
				"ttttt", 					// desc
				500.00, 					// amount
				LocalDate.of(1981, 4, 11),	// date
				"heinrich"					//recipient
				);						

		ExpenseServiceFunctions.addExpense(test);
	}


	public static void updateExpenseTest(int id, String s) {	
		//get expense from database
		Expense m = ExpenseServiceFunctions.getExpense(id);

		// set new value for clan name
		m.setExpenseTitle(s);

		//Expense m  in Datenbank updaten
		ExpenseServiceFunctions.updateExpense(id, m);

		System.out.println(m);
	}



	public static void deleteExpense(int id) {
		ExpenseServiceFunctions.deleteExpense(id);

		Expense expense = ExpenseServiceFunctions.getExpense(id);
		System.out.println(expense);

	}


	public static void getExpenseTest(int id) {
		System.out.println("CLIENT------------" 
				+ "\n" 
				+ ExpenseServiceFunctions.getExpense(id)); 
	}


	public static void getExpenseList() {
		List<Expense> xmlExpenses = new ArrayList<Expense>();
		xmlExpenses = (List<Expense>) ExpenseServiceFunctions.getExpenses();

		for(Expense einM : xmlExpenses) {
			olExpenses.add(new ExpenseFX(einM));
			System.out.println("CLIENT------------" + "\n" + einM);
		}		
	}



}

