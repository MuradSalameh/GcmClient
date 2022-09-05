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
import javafx.scene.control.ButtonBar.ButtonData;
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

    @FXML
    private AnchorPane financesAnchor;
    @FXML
    private Label revTotalLabel;
    @FXML
    private Label expTotalLabel;
    @FXML
    private Label totalLabel;

    // -------- Revenues Table ---------------

    @FXML
    private ObservableList<RevenueFX> olRevenues = FXCollections.observableArrayList();
    @FXML
    private TableView<RevenueFX> revenuesTableView;
    @FXML
    private TableColumn<RevenueFX, Integer> revIdColumn;
    @FXML
    private TableColumn<RevenueFX, String> revenueTitleColumn;
    @FXML
    private TableColumn<RevenueFX, String> revenueDescriptionColumn;
    @FXML
    private TableColumn<RevenueFX, Double> revAmountColumn;
    @FXML
    private TableColumn<RevenueFX, LocalDate> revDateColumn;

    // -------- Revenues Columns Initalize ---------------

    public void revInitializeColumns() {

	if (revIdColumn != null) {
	    revIdColumn.setCellValueFactory(new PropertyValueFactory<RevenueFX, Integer>("id"));
	    revenueTitleColumn.setCellValueFactory(new PropertyValueFactory<RevenueFX, String>("revenueTitle"));
	    revenueDescriptionColumn
		    .setCellValueFactory(new PropertyValueFactory<RevenueFX, String>("revenueDescription"));
	    revAmountColumn.setCellValueFactory(new PropertyValueFactory<RevenueFX, Double>("amount"));
	    revDateColumn.setCellValueFactory(new PropertyValueFactory<RevenueFX, LocalDate>("date"));
	}
    }

    // -------- Expenses Table ---------------

    @FXML
    private ObservableList<ExpenseFX> olExpenses = FXCollections.observableArrayList();
    @FXML
    private TableView<ExpenseFX> expensesTableView;
    @FXML
    private TableColumn<ExpenseFX, Integer> expIdColumn;
    @FXML
    private TableColumn<ExpenseFX, String> expenseTitleColumn;
    @FXML
    private TableColumn<ExpenseFX, String> expenseDescriptionColumn;
    @FXML
    private TableColumn<ExpenseFX, Double> expAmountColumn;
    @FXML
    private TableColumn<ExpenseFX, LocalDate> expDateColumn;
    @FXML
    private TableColumn<ExpenseFX, String> recipientNameColumn;

    // -------- Expense Columns Initalize ---------------

    public void expInitializeColumns() {

	if (expIdColumn != null) {
	    expIdColumn.setCellValueFactory(new PropertyValueFactory<ExpenseFX, Integer>("id"));
	    expenseTitleColumn.setCellValueFactory(new PropertyValueFactory<ExpenseFX, String>("expenseTitle"));
	    expenseDescriptionColumn
		    .setCellValueFactory(new PropertyValueFactory<ExpenseFX, String>("expenseDescription"));
	    expAmountColumn.setCellValueFactory(new PropertyValueFactory<ExpenseFX, Double>("amount"));
	    expDateColumn.setCellValueFactory(new PropertyValueFactory<ExpenseFX, LocalDate>("date"));
	    recipientNameColumn.setCellValueFactory(new PropertyValueFactory<ExpenseFX, String>("recipientName"));

	}
    }

    // -------- Revenues Buttons ---------------

    // Delete revenue button
    @FXML
    private void handleDeleteRevBtn() {
	Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setTitle("WARNING - DELETING REVENUE");
	alert.setHeaderText("THIS CAN NOT BE UNDONE");
	alert.setContentText("DO YOU REALLY WANT TO DELETE THIS REVENUE?");

	RevenueFX revenue = revenuesTableView.getSelectionModel().getSelectedItem();

	if (revenue == null) {
	    return;
	}

	Optional<ButtonType> result = alert.showAndWait();
	if (result.get() == ButtonType.OK) {

	    // get ID from item in table view

	    int id = revenue.getId();
	    // delete from database
	    RevenueServiceFunctions.deleteRevenue(id);

	    // remove from Tableview
	    revenuesTableView.getItems().removeAll(revenuesTableView.getSelectionModel().getSelectedItem());

	    readRevenuesList();
	    revenuesTableView.refresh();
	    calculateTotals();

	}
    }

    @FXML
    public Button revNewBtn;

    // add new revenue button
    @FXML
    private void handleRevAddNewBtn(ActionEvent event) throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("FinancesAddNewRevenueDialog.fxml"));
	DialogPane dialogPane = loader.load();

	Dialog dialog = new Dialog();
	dialog.setDialogPane(dialogPane);
	dialog.setResizable(true);

	FinancesAddNewRevenueDialogController edand = loader.getController();

	ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

	dialog.getDialogPane().getButtonTypes().set(0, saveBtn);
	dialog.getDialogPane().getButtonTypes().set(1, cancelBtn);

	Optional<ButtonType> result = dialog.showAndWait();

	if (!result.isPresent()) {

	    // alert is exited, no button has been pressed.

	} else if (result.get() == saveBtn) {

	    Revenue m = edand.updateRevenue();
	    int idRevenue = m.getId();
	    RevenueServiceFunctions.addRevenue(m);

	    revenuesTableView.getItems().clear();
	    readRevenuesList();
	    revUpdateTable();
	    revenuesTableView.refresh();
	    calculateTotals();

	} else if (result.get() == cancelBtn) {
	    System.out.println("Cancel Button Pressed");
	}
    }

    @FXML
    public Button revDetailsBtn;

    // edit revenue details button
    @FXML
    private void handleRevEditDetailsBtn(ActionEvent event) throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("FinancesEditRevenueDialog.fxml"));

	RevenueFX getRevenue = revenuesTableView.getSelectionModel().getSelectedItem();

	if (getRevenue == null) {
	    return;
	}

	int id = getRevenue.getId();
	ControllerCommunicator cc = new ControllerCommunicator(id);

	DialogPane dialogPane = loader.load();

	Dialog dialog = new Dialog();
	dialog.setDialogPane(dialogPane);
	dialog.setResizable(true);

	FinancesEditRevenueDialogController eddc = loader.getController();

	ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

	dialog.getDialogPane().getButtonTypes().set(0, saveBtn);
	dialog.getDialogPane().getButtonTypes().set(1, cancelBtn);

	Optional<ButtonType> result = dialog.showAndWait();

	if (!result.isPresent()) {

	    // alert is exited, no button has been pressed.

	} else if (result.get() == saveBtn) {

	    Revenue m = eddc.updateRevenue();
	    int idRevenue = m.getId();
	    RevenueServiceFunctions.updateRevenue(idRevenue, m);

	    revenuesTableView.getItems().clear();
	    readRevenuesList();
	    revUpdateTable();
	    revenuesTableView.refresh();
	    calculateTotals();
	} else if (result.get() == cancelBtn) {
	    System.out.println("Cancel Button Pressed");
	}
    }

    // -------- Expenses Buttons ---------------
    @FXML
    public Button expNewBtn;

    // add new expense button
    @FXML
    private void handleExpAddNewBtn(ActionEvent event) throws IOException {

	FXMLLoader loader = new FXMLLoader(getClass().getResource("FinancesAddNewExpenseDialog.fxml"));
	DialogPane dialogPane = loader.load();

	Dialog dialog = new Dialog();
	dialog.setDialogPane(dialogPane);
	dialog.setResizable(true);

	FinancesAddNewExpenseDialogController edand = loader.getController();

	ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

	dialog.getDialogPane().getButtonTypes().set(0, saveBtn);
	dialog.getDialogPane().getButtonTypes().set(1, cancelBtn);

	Optional<ButtonType> result = dialog.showAndWait();

	if (!result.isPresent()) {

	    // alert is exited, no button has been pressed.

	} else if (result.get() == saveBtn) {

	    Expense m = edand.updateExpense();
	    int idExpense = m.getId();
	    ExpenseServiceFunctions.addExpense(m);

	    expensesTableView.getItems().clear();
	    readExpensesList();
	    expUpdateTable();
	    expensesTableView.refresh();
	    calculateTotals();

	} else if (result.get() == cancelBtn) {
	    System.out.println("Cancel Button Pressed");
	}
    }

    @FXML
    public Button expDetailsBtn;

    // edit expense details button
    @FXML
    private void handleExpEditDetailsBtn(ActionEvent event) throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("FinancesEditExpenseDialog.fxml"));

	ExpenseFX getExpense = expensesTableView.getSelectionModel().getSelectedItem();

	if (getExpense == null) {
	    return;
	}

	int id = getExpense.getId();
	ControllerCommunicator cc = new ControllerCommunicator(id);

	DialogPane dialogPane = loader.load();

	Dialog dialog = new Dialog();
	dialog.setDialogPane(dialogPane);
	dialog.setResizable(true);

	FinancesEditExpenseDialogController eddc = loader.getController();

	ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

	dialog.getDialogPane().getButtonTypes().set(0, saveBtn);
	dialog.getDialogPane().getButtonTypes().set(1, cancelBtn);

	Optional<ButtonType> result = dialog.showAndWait();

	if (!result.isPresent()) {

	    // alert is exited, no button has been pressed.

	} else if (result.get() == saveBtn) {

	    Expense m = eddc.updateExpense();
	    int idExpense = m.getId();
	    ExpenseServiceFunctions.updateExpense(idExpense, m);

	    expensesTableView.getItems().clear();
	    readExpensesList();
	    expUpdateTable();
	    expensesTableView.refresh();
	    calculateTotals();
	} else if (result.get() == cancelBtn) {
	    System.out.println("Cancel Button Pressed");
	}
    }

    // delete expense button
    @FXML
    private void handleDeleteExpBtn() {
	Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setTitle("WARNING - DELETING EXPENSE");
	alert.setHeaderText("THIS CAN NOT BE UNDONE");
	alert.setContentText("DO YOU REALLY WANT TO DELETE THIS EXPENSE?");

	// get ID from item in table view
	ExpenseFX expense = expensesTableView.getSelectionModel().getSelectedItem();

	if (expense == null) {
	    return;
	}

	Optional<ButtonType> result = alert.showAndWait();
	if (result.get() == ButtonType.OK) {

	    int id = expense.getId();
	    // delete from database
	    ExpenseServiceFunctions.deleteExpense(id);

	    // remove from Tableview
	    expensesTableView.getItems().removeAll(expensesTableView.getSelectionModel().getSelectedItem());
	    readExpensesList();
	    expensesTableView.refresh();
	    calculateTotals();
	}
    }

    // -------- Revenues Table Update ---------------

    public void revUpdateTable() {
	// load Data
	if (revenuesTableView != null) {
	    revenuesTableView.getItems().addAll(olRevenues);
	}
    }

    // -------- Expense Table Update ---------------

    public void expUpdateTable() {
	// load Data
	if (expensesTableView != null) {
	    expensesTableView.getItems().addAll(olExpenses);

	}
    }

    // -------- Read Revenues List ---------------

    public void readRevenuesList() {
	olRevenues.clear();
	revTotal = 0;
	List<Revenue> xmlRevenues = new ArrayList<Revenue>();
	xmlRevenues = RevenueServiceFunctions.getRevenues();

	for (Revenue r : xmlRevenues) {

	    olRevenues.add(new RevenueFX(r));
	    revTotal += r.getAmount();
	}
    }

    // -------- Read Expenses List ---------------

    public void readExpensesList() {
	olExpenses.clear();
	expTotal = 0;

	List<Expense> xmlExpenses = new ArrayList<Expense>();
	xmlExpenses = ExpenseServiceFunctions.getExpenses();

	for (Expense e : xmlExpenses) {

	    olExpenses.add(new ExpenseFX(e));
	    expTotal += e.getAmount();
	}
    }

    // calculate total amount of money and set result to total.label
    public void calculateTotals() {
	expTotalLabel.setText("Total: " + expTotal + " €");
	revTotalLabel.setText("Total: " + revTotal + " €");

	total = revTotal - expTotal;
	totalLabel.setText(total + " €");

    }

    // initialize methods when FinancesScreen.fxml is loaded
    public void initialize() {
	readRevenuesList();
	readExpensesList();
	revInitializeColumns();
	expInitializeColumns();
	revUpdateTable();
	expUpdateTable();
	if (this.totalLabel != null) {
	    calculateTotals();
	}
    }
}
