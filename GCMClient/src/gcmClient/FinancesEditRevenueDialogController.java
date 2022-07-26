package gcmClient;

import java.net.URL;
import java.util.ResourceBundle;

import gcmClasses.Revenue;
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
import serviceFunctions.RevenueServiceFunctions;

public class FinancesEditRevenueDialogController extends Dialog<ButtonType> implements Initializable {

    private int ccId = ControllerCommunicator.getId();

    @FXML
    final DialogPane dialogPane = getDialogPane();
    @FXML
    private Dialog dialog;
    @FXML
    private BorderPane revenueEditBp;
    @FXML
    private Label idLabel;
    @FXML
    private TextField revenueTitleTF;
    @FXML
    private TextField revenueDescriptionTF;
    @FXML
    private TextField amountTF;
    @FXML
    private DatePicker dateDp;
    @FXML
    public Button editDetailsBtn;
    @FXML
    public Button addNewBtn;

    @FXML
    ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
    @FXML
    ButtonType saveBtn = new ButtonType("Save", ButtonData.OK_DONE);

    // Load selected object from database
    public Revenue loadRevenue() {

	Revenue revenue = RevenueServiceFunctions.getRevenue(ccId);
	return revenue;
    }

    // initialize text fields
    public void initializeTextFields() {
	Revenue revenue = loadRevenue();

	idLabel.setText(String.valueOf(ccId));

	// Revenue TextFields
	revenueTitleTF.setText(revenue.getRevenueTitle());
	revenueDescriptionTF.setText(revenue.getRevenueDescription());

	// Converting Double to String
	String amountToString = String.valueOf(revenue.getAmount());
	amountTF.setText(amountToString);

	dateDp.setValue(revenue.getDate());

	revenueTitleTF.setPromptText("Enter Revenue Title");
	revenueDescriptionTF.setPromptText("Enter Description");
	amountTF.setPromptText("Enter Amount");

    }

    // update object
    public Revenue updateRevenue() {
	Revenue revenue = loadRevenue();

	revenue.setRevenueTitle(revenueTitleTF.getText());
	revenue.setRevenueDescription(revenueDescriptionTF.getText());

	// Converting String to Double
	double StringToAmount = Double.parseDouble(amountTF.getText());
	revenue.setAmount(StringToAmount);

	revenue.setDate(dateDp.getValue());

	return revenue;
    }

    // initialize methods when FinancesEditRevenueDialog.fxml is loading
    @Override
    public void initialize(URL location, ResourceBundle resources) {
	loadRevenue();
	initializeTextFields();
    }
}
