package gcmClient;

import gcmClasses.RevenueType;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RevenueTypeFX {
	
	private RevenueType serverRevenueType;
	private SimpleIntegerProperty id;
	private SimpleStringProperty revenueTypeTitle;
	private SimpleStringProperty revenueTypeDescription;
	
	
	public RevenueTypeFX() {
		super();
	}


	public RevenueTypeFX(RevenueType serverRevenueType, SimpleIntegerProperty id, SimpleStringProperty revenueTypeTitle,
			SimpleStringProperty revenueTypeDescription) {
		super();
		this.serverRevenueType = serverRevenueType;
		id = id;
		revenueTypeTitle = revenueTypeTitle;
		revenueTypeDescription = revenueTypeDescription;
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

	/* Don't allow to alter Id
	public void setId(final int id) {
		this.idProperty().set(id);
	}
	*/

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

	
	

}
