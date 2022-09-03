package gcmClient;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import gcmClasses.Expense;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class FinancesAddNewExpenseDialogController extends Dialog<ButtonType> implements Initializable {

    @FXML
    final DialogPane dialogPane = getDialogPane();
    @FXML
    private Dialog dialog;
    @FXML
    private BorderPane expenseEditBp;
    @FXML
    private Label idLabel;
    @FXML
    private TextField expenseTitleTF;
    @FXML
    private TextField expenseDescriptionTF;
    @FXML
    private TextField amountTF;
    @FXML
    private DatePicker dateDp;
    @FXML
    private TextField recipientNameTF;
    @FXML
    public Button editDetailsBtn;
    @FXML
    public Button addNewBtn;

    @FXML
    ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
    @FXML
    ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);


    // Create empty Expense 
    public Expense loadExpense() {

	Expense newExpense = new Expense("test", 	// title
		"ttttt", 			// desc
		00.00, 				// amount
		LocalDate.now(), 		// date
		"" 				// recipient
		);
	return newExpense;
    }



    // initialize TextFields
    public void initializeTextFields() {
	Expense expense = loadExpense();

	// Expense TextFields
	expenseTitleTF.setText(expense.getExpenseTitle());
	expenseDescriptionTF.setText(expense.getExpenseDescription());

	// Converting Double to String
	String amountToString = String.valueOf(expense.getAmount());
	amountTF.setText(amountToString);

	dateDp.setValue(expense.getDate());
	recipientNameTF.setText(expense.getRecipientName());

	expenseTitleTF.setPromptText("Enter Expense Title");
	expenseDescriptionTF.setPromptText("Enter Description");
	amountTF.setPromptText("Enter Amount");

	recipientNameTF.setPromptText("Enter Recipient Name");

    }

    // Update Expense
    public Expense updateExpense() {
	Expense expense = loadExpense();

	expense.setExpenseTitle(expenseTitleTF.getText());
	expense.setExpenseDescription(expenseDescriptionTF.getText());

	// Converting String to Double
	double StringToAmount = Double.parseDouble(amountTF.getText());
	expense.setAmount(StringToAmount);

	expense.setDate(dateDp.getValue());
	expense.setRecipientName(recipientNameTF.getText());

	return expense;
    }

    // initilaize methods when FinancesAddNewExpenseDialog.fxml is opened
    @Override
    public void initialize(URL location, ResourceBundle resources) {
	loadExpense();
	initializeTextFields();
    }
}
