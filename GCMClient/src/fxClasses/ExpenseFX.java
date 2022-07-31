package fxClasses;

import java.time.LocalDate;

import gcmClasses.Expense;
import gcmClasses.ExpenseType;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ExpenseFX {
	private Expense serverExpense;
	
	private SimpleIntegerProperty id;			
	private SimpleStringProperty expenseTitle;
	private SimpleStringProperty expenseDescription;
	private SimpleDoubleProperty amount;
	private ObjectProperty<LocalDate> date;	
	private SimpleStringProperty recipientName;
	private SimpleListProperty<ExpenseType> expenseTypes;
	
	
	public ExpenseFX() {
		super();
	}


	public ExpenseFX(Expense serverExpense) {
		super();
		this.serverExpense = serverExpense;
		id = new SimpleIntegerProperty(serverExpense.getId());
		expenseTitle = new SimpleStringProperty(serverExpense.getExpenseTitle());
		expenseDescription = new SimpleStringProperty(serverExpense.getExpenseDescription());
		amount = new SimpleDoubleProperty(serverExpense.getAmount());
		date = new SimpleObjectProperty<LocalDate>(serverExpense.getDate());
		recipientName = new SimpleStringProperty(serverExpense.getRecipientName());
		this.expenseTypes = expenseTypes;

		ObservableList<ExpenseType> expenseTypesOl = FXCollections.observableArrayList(serverExpense.getExpenseTypes());
		this.expenseTypes = new SimpleListProperty<ExpenseType>(expenseTypesOl);		
	
	}

	//-------------------------------
	
	public Expense getServerExpense() {
		return serverExpense;
	}	
	
	// --------------------------------
	
	public final SimpleIntegerProperty idProperty() {
		return this.id;
	}

	public final int getId() {
		return this.idProperty().get();
	}

	/* Don't allow to alter Id
	public void setId(final int id) {
		this.idProperty().set(id);
	}
	*/
	
	// --------------------------------
	
	public final SimpleStringProperty expenseTitleProperty() {
		return this.expenseTitle;
	}

	public final String getExpenseTitle() {
		return this.expenseTitleProperty().get();
	}


	public final void setExpenseTitle(final String expenseTitle) {
		this.expenseTitleProperty().set(expenseTitle);
	}
	
	// --------------------------------

	public final SimpleStringProperty expenseDescriptionProperty() {
		return this.expenseDescription;
	}

	public final String getExpenseDescription() {
		return expenseDescriptionProperty().get();
	}


	public final void setExpenseDescription(final String expenseDescription) {
		this.expenseDescriptionProperty().set(expenseDescription);
	}
	
	// --------------------------------
	
	public final SimpleDoubleProperty amountProperty() {
		return this.amount;
	}


	public final double getAmount() {
		return amountProperty().get();
	}


	public final void setAmount(final double amount) {
		this.amountProperty().set(amount);
	}
	
	// --------------------------------


	public final ObjectProperty<LocalDate> dateProperty() {
		return this.date;
	}
	
	public final LocalDate getDate() {
		return dateProperty().get();
	}


	public final void setDate(final LocalDate date) {
		this.dateProperty().set(date);
	}
	
	// --------------------------------

	public final SimpleStringProperty recipientNameProperty() {
		return this.recipientName;
	}


	public final String getRecipientName() {
		return recipientNameProperty().get();
	}


	public final void setRecipientName(final String recipientName) {
		this.recipientNameProperty().set(recipientName);
	}
	
	// --------------------------------

	public final SimpleListProperty<ExpenseType> expenseTypesProperty() {
		return this.expenseTypes;
	}

	public final ObservableList<ExpenseType> getExpenseTypes() {
		return expenseTypesProperty().get();
	}


	public final void setExpenseTypes(final ObservableList<ExpenseType> expenseTypes) {
		this.expenseTypesProperty().set(expenseTypes);
	}
	
	

}
