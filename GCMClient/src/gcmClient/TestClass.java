package gcmClient;

import java.util.ArrayList;
import java.util.List;

import fxClasses.MemberFX;
import gcmClasses.Member;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;



public class TestClass {
	 public static  ObservableList<MemberFX> olMembers = FXCollections.observableArrayList();

	public static void main(String[] args) {	
		
		
		// Get Memberlist Test
		 List<Member> xmlMembers = new ArrayList<Member>();
		xmlMembers = MemberServiceFunctions.getMembers();
		
		
		for(Member einM : xmlMembers) {
			olMembers.add(new MemberFX(einM));
			System.out.println("CLIENT------------" + "\n" + einM);
		}
		
		
		

}
	/*
	private static void readMembersList() {
		olMembers.clear();
		ServiceFunctionsReturnData<MemberList> sfr = MemberServiceFunctions.getTest();
		if(sfr.isRc()) {
			MemberList ml = sfr.getData();
			if(ml.getMembers() != null) {
				for(Member einM : ml.getMembers()) {
					olMembers.add(new MemberFX(einM));
				}
			}
		}
		else {
			new Alert(AlertType.ERROR, sfr.getMeldung()).showAndWait();
		}


	}
*/
}

