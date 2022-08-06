package fxClasses;

import gcmClasses.Revenue;
import gcmClasses.RevenueType;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RevenueTypeFX {

	private RevenueType serverRevenueType;
	private SimpleIntegerProperty id;
	private SimpleStringProperty revenueTypeTitle;
	private SimpleStringProperty revenueTypeDescription;
	private SimpleListProperty<Revenue> revenues;


	public RevenueTypeFX() {
		super();
	}


	public RevenueTypeFX(RevenueType serverRevenueType, SimpleIntegerProperty id, SimpleStringProperty revenueTypeTitle,
			SimpleStringProperty revenueTypeDescription) {
		super();
		this.serverRevenueType = serverRevenueType;
		id = new SimpleIntegerProperty(serverRevenueType.getId());
		revenueTypeTitle = new SimpleStringProperty(serverRevenueType.getRevenueTypeTitle());
		revenueTypeDescription = new SimpleStringProperty(serverRevenueType.getRevenueTypeDescription());

		ObservableList<Revenue> revenuesOl = FXCollections.observableArrayList(serverRevenueType.getRevenues());
		this.revenues = new SimpleListProperty<Revenue>(revenuesOl);
	}


	public RevenueType getServerRevenueType() {
		return serverRevenueType;
	}

	//-----------------------
	
	public final SimpleIntegerProperty idProperty() {
		return this.id;
	}

	public final int getId() {
		return this.idProperty().get();
	}

	
	public void setId(final int id) {
		this.idProperty().set(id);
	}

	//-----------------------
	public final SimpleStringProperty revenueTypeTitleProperty() {
		return this.revenueTypeTitle;
	}


	public final String  getRevenueTypeTitle() {
		return revenueTypeTitleProperty().get();
	}


	public final void setRevenueTypeTitle(final String revenueTypeTitle) {
		this.revenueTypeTitleProperty().set(revenueTypeTitle);
	}

	//-----------------------
	public final SimpleStringProperty revenueTypeDescriptionProperty() {
		return this.revenueTypeDescription;
	}

	public final String getRevenueTypeDescription() {
		return revenueTypeDescriptionProperty().get();
	}


	public final void setRevenueTypeDescription(final String revenueTypeDescription) {
		this.revenueTypeDescriptionProperty().set(revenueTypeDescription);
	}

	//-----------------------

	public final SimpleListProperty<Revenue> revenuesProperty() {
		return this.revenues;
	}

	public final ObservableList<Revenue> getRevenues() {
		return this.revenuesProperty().get();
	}

	public void setRevenues(final ObservableList<Revenue> revenues) {
		this.revenuesProperty().set(revenues);
	}





}
