package model;

public class Owner {

	private String firstName;
	private String surName;
	private String ownerID;
	private String role;
	private String oemail;
	private String ocompanyID;

	public Owner() {
		
	}

	public Owner(String firstName, String surName, String ownerID, String role, String oemail, String ocompanyID) {
		this.setFirstName(firstName);
		this.setSurName(surName);
		this.setOwnerID(ownerID);
		this.setRole(role);
		this.setOemail(oemail);
		this.setOcompanyID(ocompanyID);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getOemail() {
		return oemail;
	}

	public void setOemail(String oemail) {
		this.oemail = oemail;
	}

	public String getOcompanyID() {
		return ocompanyID;
	}

	public void setOcompanyID(String ocompanyID) {
		this.ocompanyID = ocompanyID;
	}
	
}
