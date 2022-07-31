package gcmClient;

import java.time.LocalDate;

import gcmClasses.Event;
import gcmClasses.Partner;
import gcmClasses.Revenue;
import gcmClasses.RevenueType;
import gcmClasses.Team;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RevenueFX {
	
	private Revenue serverRevenue;
	private SimpleIntegerProperty id;
	private SimpleStringProperty revenueTitle;
	private SimpleStringProperty revenueDescription;
	private SimpleDoubleProperty amount;
	private ObjectProperty<LocalDate> date;
	private ObjectProperty<Partner> partner;
	private SimpleListProperty<RevenueType> revenueTypes;
	
	public RevenueFX() {
		super();
	}

	public RevenueFX(Revenue serverRevenue, SimpleIntegerProperty id, SimpleStringProperty revenueTitle,
			SimpleStringProperty revenueDescription, SimpleDoubleProperty amount, ObjectProperty<LocalDate> date,
			ObjectProperty<Partner> partner, SimpleListProperty<RevenueType> revenueTypes) {
		super();
		this.serverRevenue = serverRevenue;
		id = new SimpleIntegerProperty(serverRevenue.getId());
		revenueTitle = new SimpleStringProperty(serverRevenue.getRevenueTitle());
		revenueDescription = new SimpleStringProperty(serverRevenue.getRevenueDescription());
		amount = new SimpleDoubleProperty(serverRevenue.getAmount());
		date = new SimpleObjectProperty<LocalDate>(serverRevenue.getDate());
		partner = new SimpleObjectProperty<Partner>(serverRevenue.getPartner());
		
		ObservableList<RevenueType> revenueTypesOl = FXCollections.observableArrayList(serverRevenue.getRevenueTypes());
		this.revenueTypes = new SimpleListProperty<RevenueType>(revenueTypesOl);
	}

	public Revenue getServerRevenue() {
		return serverRevenue;
	}

	
	
	//-----------------------
	

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
	
	//-----------------------
	public final SimpleStringProperty revenueTitleProperty() {
		return this.revenueTitle;
	}


	public final String getRevenueTitle() {
		return revenueTitleProperty().get();
	}

	public final void setRevenueTitle(final String revenueTitle) {
		this.revenueTitleProperty().set(revenueTitle);
	}
	
	//-----------------------
	
	public final SimpleStringProperty revenueDescriptionProperty() {
		return this.revenueDescription;
	}


	public final String  getRevenueDescription() {
		return revenueDescriptionProperty().get();
	}

	public final void setRevenueDescription(final String revenueDescription) {
		this.revenueDescriptionProperty().set(revenueDescription);
	}
	
	//-----------------------
	public final SimpleDoubleProperty amountProperty() {
		return this.amount;
	}

	public final Double getAmount() {
		return amountProperty().get();
	}

	public void setAmount(final double amount) {
		this.amountProperty().set(amount);
	}
	
	//-----------------------

	public final ObjectProperty<LocalDate> dateProperty() {
		return this.date;
	}

	public final LocalDate getDate() {
		return dateProperty().get();
	}

	public final void setDate(final LocalDate date) {
		this.dateProperty().set(date);
	}

	//-----------------------
	public final ObjectProperty<Partner> partnerProperty() {
		return this.partner;
	}

	
	public final Partner getPartner() {
		return partnerProperty().get();
	}

	public void setPartner(final Partner partner) {
		this.partnerProperty().set(partner);
	}
	
	//-----------------------
	
	public final SimpleListProperty<RevenueType> revenueTypesProperty() {
		return this.revenueTypes;
	}

	public final ObservableList<RevenueType> getRevenueTypes() {
		return revenueTypesProperty().get();
	}

	public final void setRevenueTypes(final ObservableList<RevenueType> revenueTypes) {
		this.revenueTypesProperty().set(revenueTypes);
	}
	
	

	
}
