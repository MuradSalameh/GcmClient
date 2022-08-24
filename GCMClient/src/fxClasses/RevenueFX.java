package fxClasses;

import java.time.LocalDate;

import gcmClasses.Revenue;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class RevenueFX {

	private Revenue serverRevenue;
	private SimpleIntegerProperty id;
	private SimpleStringProperty revenueTitle;
	private SimpleStringProperty revenueDescription;
	private SimpleDoubleProperty amount;
	private ObjectProperty<LocalDate> date;

	public RevenueFX() {
		super();
	}

	public RevenueFX(Revenue serverRevenue) {
		super();
		this.serverRevenue = serverRevenue;
		id = new SimpleIntegerProperty(serverRevenue.getId());
		revenueTitle = new SimpleStringProperty(serverRevenue.getRevenueTitle());
		revenueDescription = new SimpleStringProperty(serverRevenue.getRevenueDescription());
		amount = new SimpleDoubleProperty(serverRevenue.getAmount());
		date = new SimpleObjectProperty<LocalDate>(serverRevenue.getDate());

	}

	public Revenue getServerRevenue() {
		return serverRevenue;
	}

	// -----------------------

	public final SimpleIntegerProperty idProperty() {
		return this.id;
	}

	public final int getId() {
		return this.idProperty().get();
	}

	public void setId(final int id) {
		this.idProperty().set(id);
	}

	// -----------------------
	public final SimpleStringProperty revenueTitleProperty() {
		return this.revenueTitle;
	}

	public final String getRevenueTitle() {
		return revenueTitleProperty().get();
	}

	public final void setRevenueTitle(final String revenueTitle) {
		this.revenueTitleProperty().set(revenueTitle);
	}

	// -----------------------

	public final SimpleStringProperty revenueDescriptionProperty() {
		return this.revenueDescription;
	}

	public final String getRevenueDescription() {
		return revenueDescriptionProperty().get();
	}

	public final void setRevenueDescription(final String revenueDescription) {
		this.revenueDescriptionProperty().set(revenueDescription);
	}

	// -----------------------
	public final SimpleDoubleProperty amountProperty() {
		return this.amount;
	}

	public final Double getAmount() {
		return amountProperty().get();
	}

	public void setAmount(final double amount) {
		this.amountProperty().set(amount);
	}

	// -----------------------

	public final ObjectProperty<LocalDate> dateProperty() {
		return this.date;
	}

	public final LocalDate getDate() {
		return dateProperty().get();
	}

	public final void setDate(final LocalDate date) {
		this.dateProperty().set(date);
	}

	// -----------------------

}
