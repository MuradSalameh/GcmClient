package tests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



import fxClasses.RoleFX;
import fxClasses.SocialFX;
import gcmClasses.Role;
import gcmClasses.Social;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import serviceFunctions.RoleServiceFunctions;
import serviceFunctions.SocialServiceFunctions;




public class RoleServiceFunctionsTest {
	public static  ObservableList<RoleFX> olRoles = FXCollections.observableArrayList();
	public static  ObservableList<RoleFX> olsbi = FXCollections.observableArrayList();

	public static void main(String[] args) {	

		int id = 1;
		int id2 = 5;
		int id3 = 5;

		//--------- addRole() Test -----------//

//				addRole();
//				addRole();
//				addRole();
//				addRole();

		

		//--------- getRoles() Test to get a List of all roles in database-----------//

//				getRoleList();


		
		//--------- deleteRole() Test -----------//

//		deleteRole(id);



		//--------- getRole() Test to get one specific role by id -----------//

//				getRoleTest(id);



		//--------- updateRole() Test -----------//

//				String s = "CLLIIIEEEENNNT UUPPDDAATTEE";
//				updateRoleTest(id,s);

		
		//--------- getRoleListByMemberId() Test -----------//

		getRoleListByMemberId(1);
		
	}	
	public static void addRole() {
		Role test = new Role(
				"test", 					// role name
				"ttttt");					// desc


		RoleServiceFunctions.addRole(test);
	}


	public static void updateRoleTest(int id, String s) {	
		//get role from database
		Role m = RoleServiceFunctions.getRole(id);

		// set new value for clan name
		m.setRoleName(s);

		//Role m  in Datenbank updaten
		RoleServiceFunctions.updateRole(id, m);

		System.out.println(m);
	}



	public static void deleteRole(int id) {
		RoleServiceFunctions.deleteRole(id);

		Role role = RoleServiceFunctions.getRole(id);
		System.out.println(role);

	}


	public static void getRoleTest(int id) {
		System.out.println("CLIENT------------" 
				+ "\n" 
				+ RoleServiceFunctions.getRole(id)); 
	}


	public static void getRoleList() {
		List<Role> xmlRoles = new ArrayList<Role>();
		xmlRoles = (List<Role>) RoleServiceFunctions.getRoles();

		for(Role einM : xmlRoles) {
			olRoles.add(new RoleFX(einM));
			System.out.println("CLIENT------------" + "\n" + einM);
		}		
	}

	public static void getRoleListByMemberId(int id) {
		List<Role> xmlRoles = new ArrayList<Role>();
		xmlRoles = (List<Role>) RoleServiceFunctions.getRolesByMemberId(id);
		
		for(Role einM : xmlRoles) {
			olsbi.add(new RoleFX(einM));
			System.out.println("CLIENT------------" + "\n" + einM);
		}		
	}


}

