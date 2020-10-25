package model;

public class Company {

	private String companyID;
	private String ABN_Number;
	private String companyName;
	private String companyURL;
	private String companyAddress;
	
	public Company() {
		
	}

	public Company(String companyID, String ABN_Number, String companyName, String companyURL, String companyAddress) {
		this.setCompanyID(companyID);
		this.setABN_Number(ABN_Number);
		this.setCompanyName(companyName);
		this.setCompanyURL(companyURL);
		this.setCompanyAddress(companyAddress);
	}

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public String getABN_Number() {
		return ABN_Number;
	}

	public void setABN_Number(String ABN_Number) {
		this.ABN_Number = ABN_Number;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyURL() {
		return companyURL;
	}

	public void setCompanyURL(String companyURL) {
		this.companyURL = companyURL;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

}
