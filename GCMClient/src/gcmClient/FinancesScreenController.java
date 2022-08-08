package gcmClient;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fxClasses.ExpenseFX;
import fxClasses.RevenueFX;
import gcmClasses.Expense;
import gcmClasses.Revenue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import serviceFunctions.ExpenseServiceFunctions;
import serviceFunctions.RevenueServiceFunctions;

public class FinancesScreenController {

	@FXML private AnchorPane financesAnchor;

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
	public Button editDetailsBtn;


	@FXML
	private void handleEditDetailsBtn(ActionEvent event) throws IOException {
		FxmlLoader loader = new FxmlLoader();
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

		List<Revenue> xmlRevenues = new ArrayList<Revenue>();
		xmlRevenues = RevenueServiceFunctions.getRevenues();			

		for(Revenue einM : xmlRevenues) {
			olRevenues.add(new RevenueFX(einM));
			System.out.println("CLIENT------------" + "\n" + einM);
		}
	}

	// -------- Read Expenses List  ---------------
	
	
	public void readExpensesList() {
		olExpenses.clear();
		
		List<Expense> xmlExpenses = new ArrayList<Expense>();
		xmlExpenses = ExpenseServiceFunctions.getExpenses();			
		
		for(Expense einM : xmlExpenses) {
			olExpenses.add(new ExpenseFX(einM));
			System.out.println("CLIENT------------" + "\n" + einM);
		}
	}
	





	public void initialize() {
		readRevenuesList();
		readExpensesList();
		revInitializeColumns();
		expInitializeColumns();
		revUpdateTable();
		expUpdateTable();
	}



	// -------- Expenses Methods ---------------



}
