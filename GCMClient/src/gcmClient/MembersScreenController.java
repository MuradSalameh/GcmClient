package gcmClient;



import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.cell.PropertyValueFactory;
import fxClasses.MemberFX;
import gcmClasses.Member;
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
import javafx.scene.layout.AnchorPane;


public class MembersScreenController {

	@FXML
	private ObservableList<MemberFX> olMembers = FXCollections.observableArrayList();
	@FXML private AnchorPane membersAnchor;
	@FXML private TableView<MemberFX> membersTableView;
	@FXML private TableColumn<MemberFX,Integer> idColumn;	
	@FXML private TableColumn<MemberFX,String> clanIdColumn;
	@FXML private TableColumn<MemberFX,String> clanNameColumn;
	@FXML private TableColumn<MemberFX,String> realNameColumn;
	@FXML private TableColumn<MemberFX,String> addressColumn;
	@FXML private TableColumn<MemberFX,String> addressPostcodeColumn;
	@FXML private TableColumn<MemberFX,String> addressCityColumn;
	@FXML private TableColumn<MemberFX,String> countryColumn;
	@FXML private TableColumn<MemberFX,String> emailColumn;
	@FXML private TableColumn<MemberFX,String> phoneNumberColumn;
	@FXML private TableColumn<MemberFX,LocalDate> birthdayColumn;

	@FXML
	public Button editDetailsBtn;

	
	@FXML
	private void handleEditDetailsBtn(ActionEvent event) throws IOException {
		FxmlLoader loader = new FxmlLoader();
		DialogPane dialogPane = FXMLLoader.load(getClass().getResource("MembersDetailDialog.fxml"));
		Dialog dialog = new Dialog();
		dialog.setDialogPane(dialogPane);
		dialog.showAndWait();

		/*
		Optional<ButtonType> r = new WeinDetailDialog(MemberFX).showAndWait();
		if(r.isPresent() && r.get().getButtonData() == ButtonData.OK_DONE) {
			// neuer Member wurde gespeichert, daher neue Weinliste vom Server holen
			//leseMemberliste();
			System.out.println("Aktualisiere Member Liste");
		}
		 */	
		System.out.println("MembersDetailsDialog Button klicked");
	}



	public void updateTable() {		
		// load Data
		if(membersTableView != null) {
				membersTableView.getItems().addAll(olMembers);
		}
	}



	public void readMembersList() {
		olMembers.clear();

		List<Member> xmlMembers = new ArrayList<Member>();
		xmlMembers = MemberServiceFunctions.getMembers();			

		for(Member einM : xmlMembers) {
			olMembers.add(new MemberFX(einM));
			System.out.println("CLIENT------------" + "\n" + einM);
		}
	}


	public  void initializeColumns() {
		
		if(idColumn != null) {
			idColumn.setCellValueFactory(new PropertyValueFactory<MemberFX, Integer>("id"));
			clanNameColumn.setCellValueFactory(new PropertyValueFactory<MemberFX, String>("clanName"));
			clanIdColumn.setCellValueFactory(new PropertyValueFactory<MemberFX, String>("clanId"));
			realNameColumn.setCellValueFactory(new PropertyValueFactory<MemberFX, String>("realName"));
			addressColumn.setCellValueFactory(new PropertyValueFactory<MemberFX, String>("address"));
			addressPostcodeColumn.setCellValueFactory(new PropertyValueFactory<MemberFX, String>("addressPostCode"));
			addressCityColumn.setCellValueFactory(new PropertyValueFactory<MemberFX, String>("addressCity"));
			countryColumn.setCellValueFactory(new PropertyValueFactory<MemberFX, String>("country"));
			emailColumn.setCellValueFactory(new PropertyValueFactory<MemberFX, String>("email"));
			phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<MemberFX, String>("phoneNumber"));
			birthdayColumn.setCellValueFactory(new PropertyValueFactory<MemberFX, LocalDate>("birthday"));
		}
	}


		
	public void initialize() {
		readMembersList();
		initializeColumns();		
		updateTable();
	}


}
