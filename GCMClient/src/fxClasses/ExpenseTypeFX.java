package fxClasses;

import gcmClasses.ExpenseType;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ExpenseTypeFX {
	private ExpenseType serverExpenseType;
	
	private SimpleIntegerProperty id;	
	private SimpleStringProperty expenseTitle;	
	private SimpleStringProperty expenseDescription;
	
	
	public ExpenseTypeFX() {
		super();
	}


	public ExpenseTypeFX(ExpenseType serverExpenseType) {
		super();
		this.serverExpenseType = serverExpenseType;
		id = new SimpleIntegerProperty(serverExpenseType.getId());
		expenseTitle = new SimpleStringProperty(serverExpenseType.getExpenseTitle());
		expenseDescription = new SimpleStringProperty(serverExpenseType.getExpenseDescription());
	}

	//------------------------------
	
	public ExpenseType getServerExpenseType() {
		return serverExpenseType;
	}


	//------------------------------

	public final SimpleIntegerProperty idProperty() {
		return this.id;
	}

	public final int getId() {
		return this.idProperty().get();
	}

	
	public void setId(final int id) {
		this.idProperty().set(id);
	}

	//------------------------------
	
	public final SimpleStringProperty expenseTitleProperty() {
		return this.expenseTitle;
	}

	public final String getExpenseTitle() {
		return expenseTitleProperty().get();
	}


	public final void setExpenseTitle(final String expenseTitle) {
		this.expenseTitleProperty().set(expenseTitle);
	}

	//------------------------------
	
	public final SimpleStringProperty expenseDescriptionProperty() {
		return this.expenseDescription;
	}

	public final String getExpenseDescription() {
		return expenseDescriptionProperty().get();
	}


	public final void setExpenseDescription(final String expenseDescription) {
		this.expenseDescriptionProperty().set(expenseDescription);
	}		

}
