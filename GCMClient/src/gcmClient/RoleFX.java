package gcmClient;

import gcmClasses.Role;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RoleFX {
	
	private Role serverRole;
	private SimpleIntegerProperty id;	
	private SimpleStringProperty roleName;	
	private SimpleStringProperty roleDescription;
	
	
	public RoleFX() {
		super();
	}


	public RoleFX(Role serverRole, SimpleIntegerProperty id, SimpleStringProperty roleName,
			SimpleStringProperty roleDescription) {
		super();
		this.serverRole = serverRole;
		id = new SimpleIntegerProperty(serverRole.getId());
		roleName = new SimpleStringProperty(serverRole.getRoleName());
		roleDescription = new SimpleStringProperty(serverRole.getRoleDescription());
	}


	public Role getServerRole() {
		return serverRole;
	}

	//-------------------------

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

	//-------------------------
	
	public final SimpleStringProperty roleNameProperty() {
		return this.roleName;
	}


	public final String getRoleName() {
		return roleNameProperty().get();
	}


	public final void setRoleName(final String roleName) {
		this.roleNameProperty().set(roleName);
	}

	//-------------------------
	
	public final SimpleStringProperty roleDescriptionProperty() {
		return this.roleDescription;
	}


	public final String  getRoleDescription() {
		return roleDescriptionProperty().get();
	}


	public final void setRoleDescription(final String roleDescription) {
		this.roleDescriptionProperty().set(roleDescription);
	}	
	
	
	
	
    

}
