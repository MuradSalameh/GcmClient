package gcmClient;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fxClasses.ExpenseFX;
import fxClasses.RevenueFX;
import gcmClasses.Expense;
import gcmClasses.Revenue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import serviceFunctions.ExpenseServiceFunctions;
import serviceFunctions.RevenueServiceFunctions;

public class FinancesScreenController {
	private double expTotal = 0;
	private double revTotal = 0;
	private double total = 0;

	@FXML private AnchorPane financesAnchor;
	@FXML private Label revTotalLabel;
	@FXML private Label expTotalLabel;
	@FXML private Label totalLabel;

	// -------- Revenues Table ---------------

	@FXML private ObservableList<RevenueFX> olRevenues = FXCollections.observableArrayList();
	@FXML private TableView<RevenueFX> revenuesTableView;

	@FXML private TableColumn<RevenueFX,Integer> revIdColumn;	
	@FXML private TableColumn<RevenueFX,String> revenueTitleColumn;
	@FXML private TableColumn<RevenueFX,String> revenueDescriptionColumn;
	@FXML private TableColumn<RevenueFX,Double> revAmountColumn;
	@FXML private TableColumn<RevenueFX,LocalDate> revDateColumn;

	// -------- Revenues Column Initalize ---------------

	public  void revInitializeColumns() {

		if(revIdColumn != null) {
			revIdColumn.setCellValueFactory(new PropertyValueFactory<RevenueFX, Integer>("id"));
			revenueTitleColumn.setCellValueFactory(new PropertyValueFactory<RevenueFX, String>("revenueTitle"));
			revenueDescriptionColumn.setCellValueFactory(new PropertyValueFactory<RevenueFX, String>("revenueDescription"));
			revAmountColumn.setCellValueFactory(new PropertyValueFactory<RevenueFX, Double>("amount"));
			revDateColumn.setCellValueFactory(new PropertyValueFactory<RevenueFX, LocalDate>("date"));	
		}
	}


	// -------- Expenses Table ---------------

	@FXML private ObservableList<ExpenseFX> olExpenses = FXCollections.observableArrayList();
	@FXML private TableView<ExpenseFX> expensesTableView;

	@FXML private TableColumn<ExpenseFX,Integer> expIdColumn;	
	@FXML private TableColumn<ExpenseFX,String> expenseTitleColumn;
	@FXML private TableColumn<ExpenseFX,String> expenseDescriptionColumn;
	@FXML private TableColumn<ExpenseFX,Double> expAmountColumn;
	@FXML private TableColumn<ExpenseFX,LocalDate> expDateColumn;
	@FXML private TableColumn<ExpenseFX,String> recipientNameColumn;

	// -------- Expense Column Initalize ---------------

	public  void expInitializeColumns() {

		if(expIdColumn != null) {
			expIdColumn.setCellValueFactory(new PropertyValueFactory<ExpenseFX, Integer>("id"));
			expenseTitleColumn.setCellValueFactory(new PropertyValueFactory<ExpenseFX, String>("expenseTitle"));
			expenseDescriptionColumn.setCellValueFactory(new PropertyValueFactory<ExpenseFX, String>("expenseDescription"));
			expAmountColumn.setCellValueFactory(new PropertyValueFactory<ExpenseFX, Double>("amount"));
			expDateColumn.setCellValueFactory(new PropertyValueFactory<ExpenseFX, LocalDate>("date"));	
			recipientNameColumn.setCellValueFactory(new PropertyValueFactory<ExpenseFX, String>("recipientName"));

		}
	}



	// -------- Revenues Buttons ---------------

	@FXML 
	private void handleDeleteRevBtn()  {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("WARNING - DELETING REVENUE");
		alert.setHeaderText("THIS CAN NOT BE UNDONE");
		alert.setContentText("DO YOU REALLY WANT TO DELETE THIS REVENUE?");
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			
			// get ID from item in table view
			RevenueFX revenue = revenuesTableView.getSelectionModel().getSelectedItem();
			int id = revenue.getId(); 
			// delete from database
			RevenueServiceFunctions.deleteRevenue(id);
			
			//remove from Tableview
			revenuesTableView.getItems().removeAll(
					revenuesTableView.getSelectionModel().getSelectedItem()
	        );
			
			readRevenuesList();
			revenuesTableView.refresh();
			calculateTotals();

		}	
	}
	
	

	@FXML
	public Button editDetailsBtn;


	@FXML
	private void handleEditDetailsBtn(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		DialogPane dialogPane = FXMLLoader.load(getClass().getResource("RevenuesDetailDialog.fxml"));
		Dialog dialog = new Dialog();
		dialog.setDialogPane(dialogPane);
		dialog.showAndWait();

		//		Optional<ButtonType> r = new WeinDetailDialog(RevenueFX).showAndWait();
		//		if(r.isPresent() && r.get().getButtonData() == ButtonData.OK_DONE) {
		//			// neuer Revenue wurde gespeichert, daher neue Weinliste vom Server holen
		//			//leseRevenueliste();
		//			System.out.println("Aktualisiere Revenue Liste");
		//		}
	}


	// -------- Expenses Buttons ---------------

	
	
	@FXML 
	private void handleDeleteExpBtn()  {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("WARNING - DELETING EXPENSE");
		alert.setHeaderText("THIS CAN NOT BE UNDONE");
		alert.setContentText("DO YOU REALLY WANT TO DELETE THIS EXPENSE?");
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			
			// get ID from item in table view
			ExpenseFX expense = expensesTableView.getSelectionModel().getSelectedItem();
			int id = expense.getId(); 
			// delete from database
			ExpenseServiceFunctions.deleteExpense(id);
			
			//remove from Tableview
			expensesTableView.getItems().removeAll(
					expensesTableView.getSelectionModel().getSelectedItem()
	        );
			readExpensesList();
			expensesTableView.refresh();
			
			calculateTotals();

		}	
	}
	
	
	


	// -------- Revenues Table Update ---------------

	public void revUpdateTable() {		
		// load Data
		if(revenuesTableView != null) {
			revenuesTableView.getItems().addAll(olRevenues);
		}
	}

	// -------- Expense Table Update ---------------

	public void expUpdateTable() {		
		// load Data
		if(expensesTableView != null) {
			expensesTableView.getItems().addAll(olExpenses);
			
		}
	}

	// -------- Read Revenues List  ---------------


	public void readRevenuesList() {
		olRevenues.clear();
		revTotal = 0;
		List<Revenue> xmlRevenues = new ArrayList<Revenue>();
		xmlRevenues = RevenueServiceFunctions.getRevenues();			

		for(Revenue r : xmlRevenues) {
			
			olRevenues.add(new RevenueFX(r));
			revTotal += r.getAmount();

			System.out.println("CLIENT------------" + "\n" + r);
		}
	}

	// -------- Read Expenses List  ---------------


	public void readExpensesList() {
		olExpenses.clear();
		expTotal = 0;

		List<Expense> xmlExpenses = new ArrayList<Expense>();
		xmlExpenses = ExpenseServiceFunctions.getExpenses();			

		for(Expense e : xmlExpenses) {
			
			olExpenses.add(new ExpenseFX(e));
			expTotal += e.getAmount();
			System.out.println("CLIENT------------" + "\n" + e);
		}		
	}




	public void calculateTotals() {
		expTotalLabel.setText("Total: " + expTotal + " €");
		revTotalLabel.setText("Total: " + revTotal + " €");

		total = revTotal - expTotal;
		totalLabel.setText(total + " €");

	}

	public void initialize() {
		readRevenuesList();
		readExpensesList();
		revInitializeColumns();
		expInitializeColumns();
		revUpdateTable();
		expUpdateTable();
		if(this.totalLabel != null) {
			calculateTotals();
		}
		
	}



	// -------- Expenses Methods ---------------



}
